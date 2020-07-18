package com.htp.service;

import com.htp.dao.ContractRepository;
import com.htp.domain.Contract;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Optional<Contract> findById(Long contractId) {
        return contractRepository.findById(contractId);
    }

    @Override
    public Contract findOne(Long contractId) {
        return contractRepository.findById(contractId).get();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract update(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void hardDelete(Long contractId) {
        contractRepository.deleteById(contractId);
    }
}
