package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Role;
import com.teamVoid.healthRecord.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "get/roles")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping(path = "add/role")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @DeleteMapping(path = "delete/{username}/{roleName}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteRole(@PathVariable("username") String username, @PathVariable("roleName") String roleName) {
        return roleService.deleteRole(username, roleName);
    }
}
