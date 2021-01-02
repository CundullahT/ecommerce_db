package com.ecommerce_db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attribute_values")
public class AttributeValue extends BaseEntity {

    private String name;

    @Column(columnDefinition = "text")
    private String description;

}