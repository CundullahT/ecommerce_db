package com.ecommerce_db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "sub_categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "category_id"})})
public class SubCategory extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
