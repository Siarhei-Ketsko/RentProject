package com.htp.service;

import com.htp.dao.RoleRepository;
import com.htp.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }

    @Override
    public Role findOne(Long id) {
        return null;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void hardDelete(Long roleId) {

    }
}
