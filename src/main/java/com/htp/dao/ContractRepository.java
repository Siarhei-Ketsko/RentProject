package com.htp.dao;

import com.htp.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractRepository extends CrudRepository<Contract, Long>, JpaRepository<Contract, Long>, PagingAndSortingRepository<Contract, Long> {

}
