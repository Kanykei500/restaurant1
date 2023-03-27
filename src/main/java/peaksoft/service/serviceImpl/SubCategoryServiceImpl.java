package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.subCategory.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.subCategory.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubCategoryRepository;
import peaksoft.service.SubCategoryService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse saveSubCategory(SubCategoryRequest subCategoryRequest) {
        Category category = categoryRepository.findById(subCategoryRequest.categoryId())
                .orElseThrow(() -> new NotFoundException("Category with id: "+subCategoryRequest.categoryId()+"doesn't exists"));
        if (subCategoryRepository.existsSubCategoryByName(subCategoryRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("SubCategory with name :%s already exists",
                            subCategoryRequest.name())).build();
        }
        SubCategory subCategory=new SubCategory();
        subCategory.setName(subCategoryRequest.name());
        subCategory.setCategory(category);
        category.addSubCategories(subCategory);
        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully saved").build();
    }

    @Override
    public List<SubCategoryResponse> getAllSubCategories() {
        return subCategoryRepository.getAll();
    }


    @Override
    public SubCategoryResponse getSubCategoryById(Long subCategoryId) {
       return subCategoryRepository.getSubCategoriesById(subCategoryId).orElseThrow(()->new NotFoundException(String.format("SubCategory with id"+subCategoryId+"doesn't exists")));

    }
    @Transactional
    @Override
    public SimpleResponse updateSubCategory(Long subCategoryId, SubCategoryRequest subCategoryRequest) {
        if (subCategoryRepository.existsSubCategoryByName(subCategoryRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("SubCategory with name :%s already exists",
                            subCategoryRequest.name())).build();
        }
        SubCategory subCategory1 = subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new NotFoundException(String.format("SubCategory with id" + subCategoryId + "doesn't exists")));
        subCategory1.setName(subCategoryRequest.name());
        subCategoryRepository.save(subCategory1);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully updated").build();
    }
    @Override
    public SimpleResponse deleteSubCategory(Long subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();
    }

    @Override
    public List<SubCategoryResponse> getAllSubCategoriesByCategoryId(Long categoryId,
                                                                     String ascOrDesc) {
        if (ascOrDesc.equals("asc")){
            return subCategoryRepository.getAllSubCategoriesByCategoryIdAndSortAsc(categoryId);
        }

        return subCategoryRepository.getAllSubCategoriesByCategoryIdAndSortDesc(categoryId);
    }

    @Override
    public Map<Long, List<SubCategoryResponse>> groupByCategory() {
        return subCategoryRepository.getAll().stream().collect(Collectors.groupingBy(SubCategoryResponse::getCategoryId));
    }


}



