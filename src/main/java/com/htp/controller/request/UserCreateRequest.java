package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserCreateRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User first name", example = "TestName")
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User surname", example = "TestSurname")
    private String surname;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User patronymic", example = "TestPatronymic")
    private String patronymic;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 20)
    @ApiModelProperty(required = true, dataType = "string", notes = "User first name", example = "+375296144555")
    private String phone;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 2)
    @ApiModelProperty(required = true, dataType = "string", notes = "User first name", example = "MP")
    private String seriesPassport;

    @NotNull
    @ApiModelProperty(required = true, dataType = "long", notes = "User first name", example = "7834513")
    private Long numberPassport;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User address", example = "TestAddress 25-34")
    private String address;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 6, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User login", example = "TestLogin")
    private String login;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 6, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "User password", example = "TestPassword")
    private String password;

    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true, dataType = "string", notes = "User email", example = "TestEmail@gmail.com")
    private String email;


}
