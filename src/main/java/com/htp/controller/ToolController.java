package com.htp.controller;

import com.htp.controller.request.ToolCreateRequest;
import com.htp.controller.request.ToolUpdateRequest;
import com.htp.domain.Tool;
import com.htp.service.ToolService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {

    private ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }
    @ApiOperation(value = "Finding all tools")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading tools"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<Tool>> findAll() {
        return new ResponseEntity<>(toolService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all available tools")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading tools"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/available")
    public ResponseEntity<List<Tool>> findAllAvailable() {
        return new ResponseEntity<>(toolService.findAllAvailable(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding tool by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading tool"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Tool database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{id}")
    public Tool findById(@PathVariable("id") Long toolsId) {
        return toolService.findOne(toolsId);
    }

//    @ApiOperation(value = "Search tools by Brand")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful loading tool"),
//            @ApiResponse(code = 500, message = "Server error, something wrong")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "query", value = "Search query - free text", example = "Rems", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
//    })
//    @GetMapping("/search")
//    public List<Tool> searchTool(@RequestParam("query") String query) {
//        return toolService.search(query);
//    }

    @ApiOperation(value = "Create tool")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation tool"),
            @ApiResponse(code = 422, message = "Failed tool creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public Tool create(@Valid @RequestBody ToolCreateRequest toolCreateRequest) {
        Tool tool = new Tool();
        tool.setBrand(toolCreateRequest.getBrand());
        tool.setModel(toolCreateRequest.getModel());
        tool.setPersonalNumber(toolCreateRequest.getPersonalNumber());
        tool.setPrice(toolCreateRequest.getPrice());
        tool.setAvailability(toolCreateRequest.isAvailability());
        return toolService.save(tool);
    }

    @ApiOperation(value = "Update tool")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful update tool"),
            @ApiResponse(code = 422, message = "Failed tool update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping(path="/{id}")
    public Tool update(@PathVariable("id") Long toolsId, @Valid  @RequestBody ToolUpdateRequest toolUpdateRequest) {
        Tool tool = toolService.findOne(toolsId);
        tool.setModel(toolUpdateRequest.getModel());
        tool.setBrand(toolUpdateRequest.getBrand());
        tool.setPersonalNumber(toolUpdateRequest.getPersonalNumber());
        tool.setPrice(toolUpdateRequest.getPrice());
        tool.setAvailability(toolUpdateRequest.isAvailability());

        return toolService.update(tool);
    }

    @ApiOperation(value = "Delete tool by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete tool"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Tool database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long toolsId) {
        toolService.hardDelete(toolsId);
        return ResponseEntity.noContent().build();
    }


}
