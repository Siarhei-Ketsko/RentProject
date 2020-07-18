package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ServiceCreateRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "Service name", example = "Restore Masters")
    private String serviceName;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
    @ApiModelProperty(required = true, dataType = "string", notes = "Service address", example = "Minsk, Platonova str 11-5")
    private String serviceAddress;

}
