package com.ecommerce_db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table(name = "currencies")
@Table(name = "currencies",uniqueConstraints = {@UniqueConstraint(columnNames = {"name","symbol"})})
public class Currency extends BaseEntity {

//    @Column(unique = true)
    private String name;

//    @Column(unique = true)
    private String symbol;

    @OneToMany(mappedBy = "currency")
    private List<Product> product;

}
