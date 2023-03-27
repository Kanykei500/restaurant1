package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.category.CategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.category.CategoryResponse;
import peaksoft.entity.Category;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.nio.channels.NotYetBoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsCategoryByName(categoryRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("Category with name :%s already exists",
                            categoryRequest.name())).build();
        }
        Category category=new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).message(String.format("Category with name: %s "+categoryRequest.name()+" successfully saved",category.getName()
        )).build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        return categoryRepository.getCategoryById(categoryId).
                orElseThrow(()->new NotFoundException("Category with id"+categoryId+" doesn't exists"));
    }
    @Transactional
    @Override

    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        if (categoryRepository.existsCategoryByName(categoryRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("Category with name :%s already exists",
                            categoryRequest.name())).build();
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with id" + categoryId + " doesn't exists"));
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully updated").build();
    }



    @Override
    public SimpleResponse deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message("Successfully deleted").build();
    }
}
