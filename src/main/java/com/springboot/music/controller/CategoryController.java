package com.springboot.music.controller;

import com.springboot.music.entity.Category;
import com.springboot.music.payload.CategoryDTO;
import com.springboot.music.payload.SignUpDto;
import com.springboot.music.service.impl.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = this.categoryService.getAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createUser(@RequestBody CategoryDTO categoryDTO){
        Category savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }


}
