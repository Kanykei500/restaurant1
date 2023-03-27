package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.subCategory.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.subCategory.SubCategoryResponse;


import java.util.List;
import java.util.Map;

@Service
public interface SubCategoryService {
    SimpleResponse saveSubCategory(SubCategoryRequest subCategoryRequest);
    List<SubCategoryResponse> getAllSubCategories();
    SubCategoryResponse getSubCategoryById(Long subCategoryId);
    SimpleResponse updateSubCategory(Long subCategoryId, SubCategoryRequest subCategory);
    SimpleResponse deleteSubCategory(Long subCategoryId);
    List<SubCategoryResponse>getAllSubCategoriesByCategoryId(Long categoryId, String ascOrDesc);
    Map<Long,List<SubCategoryResponse>>groupByCategory();
}
