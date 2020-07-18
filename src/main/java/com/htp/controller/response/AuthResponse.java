package com.htp.controller.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Authentication response with userId nad JwtToken")
public class AuthResponse {

    private String login;

    private String jwtToken;

}
