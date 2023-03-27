package peaksoft.controller;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.category.CategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.category.CategoryResponse;
import peaksoft.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryCategory {
    private final CategoryService categoryService;

    public CategoryCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveCategory(@Valid  @RequestBody CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<CategoryResponse>getAll(){
        return categoryService.getAllCategories();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{categoryId}")
    public CategoryResponse getById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public SimpleResponse updateCategory(@PathVariable Long categoryId,@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(categoryId, categoryRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public SimpleResponse deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
