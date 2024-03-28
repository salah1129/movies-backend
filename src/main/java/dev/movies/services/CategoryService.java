package dev.movies.services;

import dev.movies.DTO.CategoryDTO;
import dev.movies.entities.Category;
import dev.movies.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.movies.entities.Movie; // Ensure this import is present

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getCategoryName(),
                        category.getImage(),
                        category.getMovies().stream()
                                .map(Movie::getMovieName)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public Optional<Category> findCategoryById(long id) {
        return categoryRepository.findById(id);
    }


    public String createCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory.isPresent()){
            return "category already exist";
        }
        categoryRepository.save(category);
        return "Category created successfully";
    }

    public String updateCategory(long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            category.setId(id);
            categoryRepository.save(category);
            return "Category updated successfully";
        }
        return "Category not found";
    }

    public String deleteCategory(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category deleted successfully";
        }
        return "Category not found";
    }
}
