package com.springboot.music.service.impl;

import com.springboot.music.entity.Category;
import com.springboot.music.exception.ResourceNotFoundException;
import com.springboot.music.payload.CategoryDTO;
import com.springboot.music.repository.CategoryRepository;
import com.springboot.music.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private CategoryRepository categoryRepository;


    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())){
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());

        categoryRepository.save(category);

        return category;

    }

    @Override
    public Category updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getId()).get();
        existingCategory.setName(category.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }


}
