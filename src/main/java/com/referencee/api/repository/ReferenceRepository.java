package com.referencee.api.repository;

import com.referencee.api.model.Reference;
import com.referencee.api.model.User;
import com.referencee.api.model.enumeration.Clothing;
import com.referencee.api.model.enumeration.Gender;
import com.referencee.api.model.enumeration.Pose;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReferenceRepository extends PagingAndSortingRepository<Reference, Integer> {
    List<Reference> findAllByApproved(Boolean approved);
    List<Reference> findAllByUser(User user);
    List<Reference> findAllByGenderAndApprovedTrue(Gender gender);
    List<Reference> findAllByClothingAndApprovedTrue(Clothing clothing);
    List<Reference> findAllByPoseAndApprovedTrue(Pose pose);
    Reference findOneById(Integer id);
}
