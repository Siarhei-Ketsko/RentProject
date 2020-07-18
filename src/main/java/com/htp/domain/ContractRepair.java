package com.htp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_contract_repair")
public class ContractRepair {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "tools_id")
    private Long toolsId;

    @Column(name = "total_cost_repair")
    private Double totalCostRepair;

}
