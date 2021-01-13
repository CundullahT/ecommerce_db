package com.ecommerce_db.repository;

import com.ecommerce_db.enums.ProductStatus;
import com.ecommerce_db.model.Product;
import com.ecommerce_db.model.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByUom(Uom uom);
    List<Product> findAllByProductStatus(ProductStatus productStatus);

}
