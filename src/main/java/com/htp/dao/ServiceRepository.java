package com.htp.dao;

import com.htp.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long>, JpaRepository<Service, Long>, PagingAndSortingRepository<Service, Long> {

    List<Service> findAllByDeletedFalse();

    @Modifying
    @Query("update Service s set s.deleted = true where s.id = :serviceId")
    int softDelete(Long serviceId);

}
