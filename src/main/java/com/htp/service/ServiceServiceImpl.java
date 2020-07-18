package com.htp.service;

import com.htp.dao.ServiceRepository;
import com.htp.domain.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public List<Service> findAll() {
        return serviceRepository.findAllByDeletedFalse();
    }

//    @Override
//    public List<Service> search(String searchParam) {
//        return serviceRepository.search(searchParam);
//    }

    @Override
    public Optional<Service> findById(Long serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @Override
    public Service findOne(Long serviceId) {
        return serviceRepository.findById(serviceId).get();
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service update(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void hardDelete(Long serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    @Transactional
    public void softDelete(Long serviceId) {
        serviceRepository.softDelete(serviceId);
    }

}
