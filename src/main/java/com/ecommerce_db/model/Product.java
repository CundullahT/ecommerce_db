package com.ecommerce_db.model;


import com.ecommerce_db.enums.Condition;
import com.ecommerce_db.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

//    @Positive
    @DecimalMin("0.00")
    private BigDecimal price;

    @Positive
    private Integer quantity;

    private BigDecimal volume;

    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "uom_id")
    private Uom uom;

    @ManyToMany
    @JoinTable(name = "product_sub_category_rel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_category_id"))
    private List<SubCategory> subCategories;

    @ManyToMany
    @JoinTable(name = "product_attribute_values_rel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id"))
    private List<AttributeValue> attributeValues;

}
