package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.category.CategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.category.CategoryResponse;

import java.util.List;

@Service
public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long categoryId);
    SimpleResponse updateCategory(Long categoryId,CategoryRequest categoryRequest);
    SimpleResponse deleteCategory(Long categoryId);
}
