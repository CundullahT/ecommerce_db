package com.ecommerce_db.services;

import com.ecommerce_db.model.Category;
import com.ecommerce_db.model.SubCategory;
import com.ecommerce_db.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryService subCategoryService;

    public CategoryService(CategoryRepository categoryRepository, SubCategoryService subCategoryService) {
        this.categoryRepository = categoryRepository;
        this.subCategoryService = subCategoryService;
    }

    public Category create(Category category) throws Exception {

        Optional<Category> foundedCategory = categoryRepository.findByName(category.getName());
        if(foundedCategory.isPresent()) throw new Exception("This Category Already Exists.");

        return categoryRepository.save(category);

    }

    public void update(Category category) throws Exception {

        Optional<Category> foundedCategory = categoryRepository.findByName(category.getName());
        if (foundedCategory.isEmpty()) throw new Exception("There Is No Such Category");

        category.setId(foundedCategory.get().getId());
        categoryRepository.save(category);

    }

    public List<Category> readAll(){
        return categoryRepository.findAll(Sort.by("name"));
    }

    public Category readById(Integer id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) throws Exception {

        Category foundedCategory = categoryRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such Category"));
        List<SubCategory> subCategories = subCategoryService.readAllByCategory(foundedCategory);

        if(subCategories.size() > 0) throw new Exception("This Category Has SubCategories So It Can Not Be Deleted.");

        foundedCategory.setName(foundedCategory.getName() + "-" + foundedCategory.getId());
        foundedCategory.setIsDeleted(true);

        categoryRepository.save(foundedCategory);

    }

}
