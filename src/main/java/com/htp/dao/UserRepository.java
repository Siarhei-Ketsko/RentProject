package com.htp.dao;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

   Optional<User> findByLogin(String username);

}
