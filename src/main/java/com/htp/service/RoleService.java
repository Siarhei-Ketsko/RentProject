package com.htp.service;

import com.htp.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<Role> findByUserId(Long userId);

    Role findOne(Long id);

    Role save(Role role);

    Role update(Role role);

    void hardDelete(Long roleId);

}
