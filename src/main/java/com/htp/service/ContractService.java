package com.htp.service;

import com.htp.domain.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {

    List<Contract> findAll();

//    List<Contract> search(String searchParam);

    Optional<Contract> findById(Long contractId);

    Contract findOne(Long contractId);

    Contract save(Contract contract);

    Contract update(Contract contract);

    void hardDelete(Long contractId);

}
