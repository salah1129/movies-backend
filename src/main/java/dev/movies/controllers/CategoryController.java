package dev.movies.controllers;

import dev.movies.DTO.CategoryDTO;
import dev.movies.entities.Category;
import dev.movies.repositories.CategoryRepository;
import dev.movies.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public String createCategory(@RequestBody  Category category){
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory.isPresent()){
            return "category already exist";
        }
        categoryService.createCategory(category);
        return "category created successfully";
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }


    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable long id){
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
}
