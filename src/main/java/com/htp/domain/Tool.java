package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "contracts"
})
@ToString(exclude = {
        "contracts"
})
@Entity
@Table(name = "m_tools")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Tool implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "price")
    private Double price;

    @Column(name = "availability")
    private boolean availability;

    @JsonBackReference
    @JsonIgnoreProperties("contracts")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "l_contract_tools",
            joinColumns = {@JoinColumn(name = "tools_id")},
            inverseJoinColumns = {@JoinColumn(name = "contract_id")}
    )
    private List<Contract> contracts = Collections.emptyList();
}
