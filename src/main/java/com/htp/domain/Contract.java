package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "user", "tools"
})
@ToString(exclude = {
        "user", "tools"
})
@Entity
@Table(name = "m_contract")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Contract {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "contract_date")
    private Date contractDate;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "rent_price")
    private Double rentPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @JsonIgnoreProperties("tools")
    @ManyToMany(mappedBy = "contracts")
    private List<Tool> tools = Collections.emptyList();

}
