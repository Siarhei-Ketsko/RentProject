package com.htp.controller;

import com.htp.controller.request.ContractCreateRequest;
import com.htp.controller.request.ToolCreateRequest;
import com.htp.controller.request.ToolUpdateRequest;
import com.htp.domain.Contract;
import com.htp.domain.Tool;
import com.htp.service.ContractService;
import com.htp.service.ToolService;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contracts")
public class ContractController {

   private ContractService contractService;
   private UserService userService;
   private ToolService toolService;

    public ContractController(ContractService contractService, UserService userService, ToolService toolService) {
        this.contractService = contractService;
        this.userService = userService;
        this.toolService = toolService;
    }

    @ApiOperation(value = "Finding all contracts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading contracts"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<Contract>> findAll() {
        return new ResponseEntity<>(contractService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding contract by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading contract"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Contract database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{id}")
    public Contract findById(@PathVariable("id") Long contractId) {
        return contractService.findOne(contractId);
    }

    @ApiOperation(value = "Create contract")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation contract"),
            @ApiResponse(code = 422, message = "Failed contract creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public Contract create(@Valid @RequestBody ContractCreateRequest contractCreateRequest) {
        Contract contract = new Contract();
        contract.setContractDate(contractCreateRequest.getContractDate());
        contract.setIssueDate(contractCreateRequest.getIssueDate());
        contract.setReturnDate(contractCreateRequest.getReturnDate());
        contract.setRentPrice(contractCreateRequest.getRentPrice());
        contract.setTools(toolService.findAll());
        contract.setUser(userService.findOne(1L));
        return contractService.save(contract);
    }

    @ApiOperation(value = "Update contract")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful update contract"),
            @ApiResponse(code = 422, message = "Failed contract update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "contract database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping(path="/{id}")
    public Contract update(@PathVariable("id") Long contractId, @Valid  @RequestBody ContractCreateRequest contractCreateRequest) {
        Contract contract = contractService.findOne(contractId);
        contract.setContractDate(contractCreateRequest.getContractDate());
        contract.setIssueDate(contractCreateRequest.getIssueDate());
        contract.setReturnDate(contractCreateRequest.getReturnDate());
        contract.setRentPrice(contractCreateRequest.getRentPrice());
        contract.setTools(toolService.findAll());
        contract.setUser(userService.findOne(1L));

        return contractService.save(contract);
    }

}
