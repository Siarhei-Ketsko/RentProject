package com.htp.controller;

import com.htp.controller.request.ServiceCreateRequest;
import com.htp.controller.request.ServiceUpdateRequest;
import com.htp.domain.Service;
import com.htp.service.ServiceService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @ApiOperation(value = "Finding all services")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading services"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<Service>> findAll() {

        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding service by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading service"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Service database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{id}")
    public Service findById(@PathVariable("id") Long serviceId) {
        return serviceService.findOne(serviceId);
    }

//    @ApiOperation(value = "Search service by id")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful loading service"),
//            @ApiResponse(code = 500, message = "Server error, something wrong")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "query", value = "Search query - free text", example = "1", required = true, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
//    })
//    @GetMapping("/search")
//    public List<Service> searchService(@RequestParam("query") String query) {
//        return serviceService.search(query);
//    }

    @ApiOperation(value = "Create service")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation service"),
            @ApiResponse(code = 422, message = "Failed service creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public Service create(@RequestBody ServiceCreateRequest serviceCreateRequest) {
        Service service = new Service();
        service.setServiceName(serviceCreateRequest.getServiceName());
        service.setServiceAddress(serviceCreateRequest.getServiceAddress());
        return serviceService.save(service);
    }

    @ApiOperation(value = "Update service")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful update service"),
            @ApiResponse(code = 422, message = "Failed service update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Service database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping(path="/{id}")
    public Service update(@PathVariable("id") Long serviceId, @RequestBody ServiceUpdateRequest serviceUpdateRequest) {
        Service service = serviceService.findOne(serviceId);
        service.setServiceName(serviceUpdateRequest.getServiceName());
        service.setServiceAddress(serviceUpdateRequest.getServiceAddress());

        return serviceService.update(service);
    }

//    @ApiOperation(value = "Hard delete service by id")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful delete service"),
//            @ApiResponse(code = 500, message = "Server error, something wrong")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "Service database id", example = "1", required = true, dataType = "long", paramType = "path"),
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
//    })
//    @DeleteMapping(path="/{id}")
//    public ResponseEntity<Void> hardDelete(@PathVariable("id") Long serviceId) {
//        serviceService.hardDelete(serviceId);
//        return ResponseEntity.noContent().build();
//    }

    @ApiOperation(value = "Soft delete service by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete service"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Service database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") Long serviceId) {
        serviceService.softDelete(serviceId);
        return ResponseEntity.noContent().build();
    }

//
//    @GetMapping("/new")
//    public String getServiceAdd() {
//        return "service/serviceAdd";
//    }
//
//    @PostMapping("/new")
//    public String addService(@ModelAttribute Service service) {
//        serviceService.save(service);
//        return "redirect:/services";
//    }
//
//    @GetMapping("/update")
//    public String getUpdateService() {
//        return "service/serviceUpdate";
//    }
//
//    @PostMapping("/update")
//    public String UpdateService(@ModelAttribute Service service) {
//        serviceService.update(service);
//        return "redirect:/services";
//    }

}



