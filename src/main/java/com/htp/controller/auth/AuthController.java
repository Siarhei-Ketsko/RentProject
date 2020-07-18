package com.htp.controller.auth;

import com.htp.controller.request.AuthRequest;
import com.htp.controller.request.UserCreateRequest;
import com.htp.controller.response.AuthResponse;
import com.htp.domain.Role;
import com.htp.domain.Roles;
import com.htp.domain.User;
import com.htp.security.util.TokenUtils;
import com.htp.service.RoleService;
import com.htp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping
public class AuthController {

    private TokenUtils tokenUtils;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private UserService userService;

    private RoleService roleService;

    public AuthController(TokenUtils tokenUtils, AuthenticationManager authenticationManager,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserService userService, RoleService roleService) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @ApiOperation(value = "Login user by username and password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful login user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>(
                AuthResponse
                        .builder()
                        .login(authRequest.getUsername())
                        .jwtToken(tokenUtils.generateToken(userDetailsService
                                .loadUserByUsername(authRequest.getUsername())))
                        .build(), HttpStatus.OK);

    }

    @ApiOperation("Create user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful, user created"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/registration")
    public User create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
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

        User savedUser = userService.save(user);
        Role role = new Role();
        role.setRoleName(Roles.ROLE_USER.name());
        role.setId(savedUser.getId());

        Role savedRole = roleService.save(role);

        savedUser.setRole(savedRole);

        return savedUser;
    }

}
