package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.htp.domain.Tool;
import com.htp.domain.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContractCreateRequest {

    private Date contractDate;

    private Date issueDate;

    private Date returnDate;

    private Double rentPrice;

//    private User user;
//
//    private List<Tool> tools = Collections.emptyList();

}
