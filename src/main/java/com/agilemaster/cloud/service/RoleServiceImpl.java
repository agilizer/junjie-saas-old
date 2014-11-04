package com.agilemaster.cloud.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.agilemaster.cloud.entity.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.cloud.entity.Role;
import com.agilemaster.cloud.repository.RoleRepository;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ResourceService resourceService;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
    	roleRepository.delete(roleId);;
    }

    @Override
    public Role findOne(Long roleId) {
        return roleRepository.findOne(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(List<Role> roles) {
        Set<String> permissions = new HashSet<String>();
        for(Role role : roles) {
            for(Resource resource:role.getResources()) {
            	permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }
}
