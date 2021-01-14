package com.ecommerce_db.services;

import com.ecommerce_db.model.Product;
import com.ecommerce_db.model.Uom;
import com.ecommerce_db.repository.UomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UomService {

    private final UomRepository uomRepository;
    private final ProductService productService;

    public UomService(UomRepository uomRepository, ProductService productService) {
        this.uomRepository = uomRepository;
        this.productService = productService;
    }

    public Uom create(Uom uom) throws Exception {

        Optional<Uom> foundedUom = uomRepository.findByName(uom.getName());
        if (foundedUom.isPresent()) throw new Exception("This Unit Of Measure Already Exists.");

        return uomRepository.save(uom);

    }

    public void update(Uom uom) throws Exception {

        Uom foundedUom = uomRepository.findByName(uom.getName()).orElseThrow(() -> new Exception("There Is No Such Unit Of Measure."));

        uom.setId(foundedUom.getId());
        uomRepository.save(uom);

    }

    public List<Uom> readAll(){
        return uomRepository.findAll(Sort.by("name"));
    }

    public Uom readById(Integer id) throws Exception {
        return uomRepository.findById(id).orElse(null);
    }

    public Uom readByProduct(Product product){
        return uomRepository.findByProductId(product.getId()).orElse(null);
    }

    public void deleteById(Integer id) throws Exception {

        Uom foundedUom = uomRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Unit Of Measure."));
        List<Product> products = productService.readAllByUom(foundedUom);

        if (products.size() > 0) throw new Exception("This Unit Of Measure Is Related With A Product(s) So It Can Not Be Deleted.");

        foundedUom.setName(foundedUom.getName() + "-" + foundedUom.getId());
        foundedUom.setIsDeleted(true);

        uomRepository.save(foundedUom);

    }

}
