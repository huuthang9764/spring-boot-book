package com.springboot.music.service;

import com.springboot.music.entity.Category;
import com.springboot.music.payload.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
    List<Category> getAll();
    Optional<Category> getCategoryById(long id);
}
