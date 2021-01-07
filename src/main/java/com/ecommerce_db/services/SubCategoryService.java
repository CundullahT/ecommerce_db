package com.ecommerce_db.services;

import com.ecommerce_db.model.Category;
import com.ecommerce_db.model.SubCategory;
import com.ecommerce_db.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {
    
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public SubCategory create(SubCategory subCategory) throws Exception {
        Optional<SubCategory> foundedSubCategory = subCategoryRepository.findByNameAndCategoryId(subCategory.getName(), subCategory.getCategory().getId());

        if(foundedSubCategory.isPresent()) throw new Exception("Sub Category Already Exists.");

        return subCategoryRepository.save(subCategory);

    }

    public void update(SubCategory subCategory) throws Exception {
        SubCategory foundedSubCategory = subCategoryRepository.findByNameAndCategoryId(subCategory.getName(), subCategory.getCategory().getId())
                .orElseThrow(() -> new Exception("This SubCategory Does Not Exist."));

        subCategory.setId(foundedSubCategory.getId());
        subCategoryRepository.save(subCategory);

    }

    public List<SubCategory> readAll(){
        return subCategoryRepository.findAll();
    }

    public SubCategory readById(Integer id){
        return subCategoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) throws Exception {

        SubCategory foundedSubCategory = subCategoryRepository.findById(id).orElseThrow(() -> new Exception("This SubCategory Doesn't Exists."));
        foundedSubCategory.setName(foundedSubCategory.getName() + "-" + foundedSubCategory.getId());
        foundedSubCategory.setIsDeleted(true);
        subCategoryRepository.save(foundedSubCategory);

    }

    public List<SubCategory> readAllByCategory(Category category){
        return subCategoryRepository.findAllByCategory(category);
    }

}
