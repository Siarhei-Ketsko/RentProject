package com.htp.dao;

import com.htp.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ToolRepository extends CrudRepository<Tool, Long>, JpaRepository<Tool, Long>, PagingAndSortingRepository<Tool, Long> {

    List<Tool> findAllByAvailabilityTrue();
}
