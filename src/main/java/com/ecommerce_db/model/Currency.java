package com.ecommerce_db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "currencies")
public class Currency extends BaseEntity {

    private String name;
    private String symbol;

    @OneToMany(mappedBy = "currency")
    private List<Product> product;

}
