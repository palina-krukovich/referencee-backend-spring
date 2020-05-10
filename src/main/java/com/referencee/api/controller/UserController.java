package com.referencee.api.controller;

import com.referencee.api.model.Duration;
import com.referencee.api.model.Reference;
import com.referencee.api.model.User;
import com.referencee.api.service.DurationService;
import com.referencee.api.service.ReferenceService;
import com.referencee.api.service.UserService;
import com.referencee.api.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private Logger logger = LogManager.getLogger(getClass());
    @Autowired private UserService userService;
    @Autowired private ReferenceService referenceService;
    @Autowired private DurationService durationService;

    @PostMapping(path = "/user/save")
    public void saveUser(@RequestParam String email) {
        if (userService.findOneByEmail(email) == null) {
            userService.save(email, false);
        }
    }

    @GetMapping(path = "/user/isAdmin")
    public boolean findUser(@RequestParam String email) {
        return userService.findOneByEmail(email).getAdmin();
    }

    @PostMapping(path = "/reference/save")
    public void saveReference(@RequestBody MultipartFile image,
                              @RequestParam String email,
                              @RequestParam String gender,
                              @RequestParam String clothing,
                              @RequestParam String pose) {
        User user = userService.findOneByEmail(email);
        if (user != null) {
            try {
                referenceService.save(image, user, gender, clothing, pose);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @GetMapping(path = "/reference/find/all/byUser")
    public List<Reference> findAllReferenceByUser(@RequestParam String email) {
        User user = userService.findOneByEmail(email);
        return user == null ? null : referenceService.findAllByUser(user);
    }

    @GetMapping(path = "/reference/find/limit/byQuery")
    public List<Reference> findLimitReferenceByQuery(@RequestParam String gender,
                                                     @RequestParam String clothing,
                                                     @RequestParam String pose,
                                                     @RequestParam Integer count) {
        return referenceService.findLimitByQuery(gender, clothing, pose, count);
    }

    @GetMapping(path = "/reference/find/one/byQuery")
    public Reference findOneReferenceByQuery(@RequestParam String gender,
                                             @RequestParam String clothing,
                                             @RequestParam String pose) {
        return referenceService.findOneByQuery(gender, clothing, pose);
    }

    @GetMapping(path = "/duration/find/all")
    public List<Duration> findAllDuration() {
        return durationService.findAll();
    }

}
