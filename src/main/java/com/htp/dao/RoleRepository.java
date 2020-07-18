package com.htp.dao;

import com.htp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {

   List<Role> findRoleByUserId(Long id);

}
