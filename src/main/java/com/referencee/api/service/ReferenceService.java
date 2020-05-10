package com.referencee.api.service;

import com.referencee.api.model.Reference;
import com.referencee.api.model.User;
import com.referencee.api.model.enumeration.Clothing;
import com.referencee.api.model.enumeration.Gender;
import com.referencee.api.model.enumeration.Pose;
import com.referencee.api.repository.ReferenceRepository;
import com.referencee.api.service.exception.ServiceException;
import com.referencee.api.util.StorageUtil;
import com.referencee.api.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@Service
public class ReferenceService {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private ReferenceRepository repository;

    public void save(MultipartFile image, User user, String genderStr, String clothingStr, String poseStr)
            throws ServiceException {
        StorageUtil.FilePath path;
        try {
            path = StorageUtil.getInstance().uploadFile(image);
        } catch (UtilException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        Reference reference = new Reference();
        reference.setUser(user);
        reference.setApproved(user.getAdmin());
        reference.setUrl(path.getUrl());
        reference.setGcsPath(path.getGcsPath());
        reference.setGender(Gender.valueOfLabel(genderStr));
        reference.setClothing(Clothing.valueOfLabel(clothingStr));
        reference.setPose(Pose.valueOfLabel(poseStr));

        repository.save(reference);
    }

    public List<Reference> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public List<Reference> findLimitByQuery(String genderStr, String clothingStr, String poseStr, Integer count) {
        List<Reference> references = findAllByQueryShuffled(genderStr, clothingStr, poseStr);
        return references.size() > count ? references.subList(0, count) : references;
    }

    public Reference findOneByQuery(String genderStr, String clothingStr, String poseStr) {
        List<Reference> references = findAllByQueryShuffled(genderStr, clothingStr, poseStr);
        return references.size() > 0 ? references.get(0) : null;
    }

    public List<Reference> findAllByApprovedFalse() {
        return repository.findAllByApproved(false);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void updateApprovedTrueById(Integer id) {
        Reference reference = repository.findOneById(id);
        repository.deleteById(id);
        reference.setApproved(true);
        repository.save(reference);
    }

    private List<Reference> findAllByQueryShuffled(String genderStr, String clothingStr, String poseStr) {
        Gender gender = Gender.valueOfLabel(genderStr);
        Pose pose = Pose.valueOfLabel(poseStr);
        Clothing clothing = Clothing.valueOfLabel(clothingStr);
        Set<Reference> referenceSet = new HashSet<>(repository.findAllByApproved(true));
        if (gender != Gender.ANY) {
            referenceSet.retainAll(repository.findAllByGenderAndApprovedTrue(gender));
        }
        if (pose != Pose.ANY) {
            referenceSet.retainAll(repository.findAllByPoseAndApprovedTrue(pose));
        }
        if (clothing != Clothing.ANY) {
            referenceSet.retainAll(repository.findAllByClothingAndApprovedTrue(clothing));
        }

        List<Reference> references = new ArrayList<>(referenceSet);
        Collections.shuffle(references);

        return references;
    }
}
