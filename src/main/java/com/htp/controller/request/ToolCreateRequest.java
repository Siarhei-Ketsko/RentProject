package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Tool creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ToolCreateRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 200)
//    @Pattern(
//            regexp = "",
//            message = ""
//    )
    @ApiModelProperty(required = true, dataType = "string", notes = "Brand name", example = "Rems")
    private String brand;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "Tool model", example = "eco press")
    private String model;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "personal tool number", example = "41/16.01")
    private String personalNumber;

    @NotNull
    @ApiModelProperty(required = true, dataType = "string", notes = "Tool rental cost", example = "20.00")
    private Double price;


    @ApiModelProperty(required = true, dataType = "string", notes = "Tool availability", example = "true")
    private boolean availability;


}
