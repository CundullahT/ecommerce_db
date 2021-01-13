package com.ecommerce_db.repository;

import com.ecommerce_db.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findByNameAndSymbol(String name, String symbol);
    Optional<Currency> findByProductId(Integer id);

}
