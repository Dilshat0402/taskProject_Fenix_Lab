package com.dilshat.blog_project.services.impl;

import com.dilshat.blog_project.models.Role;
import com.dilshat.blog_project.repositories.RoleRepository;
import com.dilshat.blog_project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
