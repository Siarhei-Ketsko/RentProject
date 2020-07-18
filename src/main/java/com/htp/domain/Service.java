package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_service")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Service {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_address")
    private String serviceAddress;

    @Column(name = "is_deleted")
    @JsonIgnore
    private boolean deleted;

}
