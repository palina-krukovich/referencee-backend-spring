package com.referencee.api.controller;

import com.referencee.api.model.Reference;
import com.referencee.api.model.User;
import com.referencee.api.service.ReferenceService;
import com.referencee.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    ReferenceService referenceService;

    @PostMapping(path = "/user/save/admin")
    public void saveAdminUser(@RequestParam String email) {
        User user = userService.findOneByEmail(email);
        if (user != null) {
            userService.deleteUser(user);
        }
        userService.save(email, true);
    }

    @DeleteMapping(path = "/user/delete")
    public void deleteUser(@RequestParam String email) {
        User user = userService.findOneByEmail(email);
        if (user != null) {
            userService.deleteUser(user);
        }
    }

    @GetMapping(path = "/user/find/all")
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @GetMapping(path = "user/find/all/isAdmin")
    public List<User> findAllUserIsAdmin() {
        return userService.findAllIsAdmin();
    }

    @GetMapping(path = "reference/find/all/byApprovedFalse")
    public List<Reference> findAllReferenceByApprovedFalse() {
        return referenceService.findAllByApprovedFalse();
    }

    @PostMapping(path = "reference/update/approvedTrue")
    public void updateReferenceApprovedTrue(@RequestParam Integer id) {
        referenceService.updateApprovedTrueById(id);
    }

    @DeleteMapping(path = "reference/delete")
    public void deleteReference(@RequestParam Integer id) {
        referenceService.deleteById(id);
    }


}
