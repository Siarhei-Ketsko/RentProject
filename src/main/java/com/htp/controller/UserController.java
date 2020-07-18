package com.htp.controller;


import com.htp.controller.request.UserCreateRequest;
import com.htp.controller.request.UserUpdateRequest;
import com.htp.dao.UserRepository;
import com.htp.domain.User;
import com.htp.security.util.PrincipalUtil;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Find all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")

    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @ApiOperation("Search user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long userId) {

        return new ResponseEntity<>(userService.findById(userId).get(), HttpStatus.OK);
    }

    //    @ApiOperation("Search user by login")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful loading user"),
//            @ApiResponse(code = 500, message = "Server error, something wrong")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
//            @ApiImplicitParam(name = "query", value = "User database login", example = "siarheiadmin", required = true, dataType = "string", paramType = "query")
//    })
//    @GetMapping("/search")
//    public List<User> searchUser(@RequestParam("query") String query) {
//
//        return userRepository;
//    }
    @ApiOperation("Create user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful, user created"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateRequest userCreateRequest, Principal principal) {
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        user.setSurname(userCreateRequest.getSurname());
        user.setPatronymic(userCreateRequest.getPatronymic());
        user.setPhone(userCreateRequest.getPhone());
        user.setSeriesPassport(userCreateRequest.getSeriesPassport());
        user.setNumberPassport(userCreateRequest.getNumberPassport());
        user.setAddress(userCreateRequest.getAddress());
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());
        user.setEmail(userCreateRequest.getEmail());
        userService.save(user);
        String username = PrincipalUtil.getUsername(principal);

        log.info("User with login {} perform saving new entity", username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation("Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful, user updated"),
            @ApiResponse(code = 422, message = "Failed user update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping(path = "/{id}")
    public User update(@PathVariable("id") Long userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.findOne(userId);
        user.setUsername(userUpdateRequest.getUsername());
        user.setSurname(userUpdateRequest.getSurname());
        user.setPatronymic(userUpdateRequest.getPatronymic());
        user.setPhone(userUpdateRequest.getPhone());
        user.setSeriesPassport(userUpdateRequest.getSeriesPassport());
        user.setNumberPassport(userUpdateRequest.getNumberPassport());
        user.setAddress(userUpdateRequest.getAddress());
        user.setLogin(userUpdateRequest.getLogin());
        user.setPassword(userUpdateRequest.getPassword());
        user.setEmail(userUpdateRequest.getEmail());
        user.setVerified(userUpdateRequest.isVerified());

        return userService.save(user);
    }

    @ApiOperation(value = "Delete user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long userId) {
        userService.hardDelete(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
