package com.ecommerce_db.services;

import com.ecommerce_db.model.Currency;
import com.ecommerce_db.model.Product;
import com.ecommerce_db.repository.CurrencyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private ProductService productService;

    public CurrencyService(CurrencyRepository currencyRepository, ProductService productService) {
        this.currencyRepository = currencyRepository;
        this.productService = productService;
    }

    public Currency create(Currency currency) throws Exception {

        Optional<Currency> foundedCurrency = currencyRepository.findByNameAndSymbol(currency.getName(), currency.getSymbol());
        if (foundedCurrency.isPresent()) throw new Exception("This Currency Already Exists.");

        return currencyRepository.save(currency);

    }

    public void update(Currency currency) throws Exception {

        Currency foundedCurrency = currencyRepository.findByNameAndSymbol(currency.getName(), currency.getSymbol()).orElseThrow(() -> new Exception("There Is No Such Currency."));

        currency.setId(foundedCurrency.getId());
        currencyRepository.save(currency);

    }

    public List<Currency> readAll(){
        return currencyRepository.findAll(Sort.by("name"));
    }

    public Currency readById(Integer id){
        return currencyRepository.findById(id).orElse(null);
    }

    public Currency readByProduct(Product product){
        return currencyRepository.findByProductId(product.getId()).orElse(null);
    }

    public void deleteById(Integer id) throws Exception {

        Currency foundedCurrency = currencyRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Currency."));
        List<Product> products = productService.readAllByCurrency(foundedCurrency);

        if(products.size() > 0) throw new Exception("This Currency Is Related With A Product(s) So It Can Not Be Deleted.");

        foundedCurrency.setName(foundedCurrency.getName() + "-" + foundedCurrency.getId());
        foundedCurrency.setSymbol(foundedCurrency.getSymbol() + "-" + foundedCurrency.getId());
        foundedCurrency.setIsDeleted(true);

        currencyRepository.save(foundedCurrency);

    }

}
