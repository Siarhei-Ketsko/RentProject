package com.htp.dao;

import com.htp.domain.ContractRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractRepairRepository extends CrudRepository<ContractRepair, Long>, JpaRepository<ContractRepair, Long>, PagingAndSortingRepository<ContractRepair, Long> {

}
