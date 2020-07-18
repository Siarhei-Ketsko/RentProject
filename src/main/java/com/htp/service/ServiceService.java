package com.htp.service;

import com.htp.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    List<Service> findAll();

//    List<Service> search(String searchParam);

    Optional<Service> findById(Long serviceId);

    Service findOne(Long serviceId);

    Service save(Service service);

    Service update(Service service);

    void hardDelete(Long serviceId);

    void softDelete(Long serviceId);

}
