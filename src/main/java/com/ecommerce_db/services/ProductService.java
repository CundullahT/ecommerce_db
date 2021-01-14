package com.ecommerce_db.services;

import com.ecommerce_db.enums.ProductStatus;
import com.ecommerce_db.model.Currency;
import com.ecommerce_db.model.Product;
import com.ecommerce_db.model.Uom;
import com.ecommerce_db.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) throws Exception {

        Optional<Product> foundedProduct = productRepository.findById(product.getId());
        if (foundedProduct.isPresent()) throw new Exception("This Product Already Exists");

        return productRepository.save(product);

    }

    public void update(Product product) throws Exception {

        Product foundedProduct = productRepository.findById(product.getId()).orElseThrow(() -> new Exception("There Is No Such Product"));

        product.setId(foundedProduct.getId());
        productRepository.save(product);

    }

    public List<Product> readAll(){
        return productRepository.findAll(Sort.by("name"));
    }

    public Product readById(Integer id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> readAllActive(){
        return productRepository.findAllByProductStatus(ProductStatus.ACTIVE);
    }

    public List<Product> readAllByUom(Uom uom){
        return productRepository.findAllByUom(uom);
    }

    public List<Product> readAllByCurrency(Currency currency){
        return productRepository.findAllByCurrency(currency);
    }

    public void deleteById(Integer id) throws Exception {

        Product foundedProduct = productRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Product."));
        foundedProduct.setIsDeleted(true);
        productRepository.save(foundedProduct);

    }

}
