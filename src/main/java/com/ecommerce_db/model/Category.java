package com.ecommerce_db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

}
