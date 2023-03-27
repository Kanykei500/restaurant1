package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.subCategory.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.subCategory.SubCategoryResponse;
import peaksoft.service.SubCategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @Autowired

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveSubCategories(@RequestBody @Valid SubCategoryRequest subCategoryRequest){
        return subCategoryService.saveSubCategory(subCategoryRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<SubCategoryResponse> getAllSubCategories(){
       return subCategoryService.getAllSubCategories();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{subCategoryId}")
    public SubCategoryResponse getById(@PathVariable Long subCategoryId){
        return subCategoryService.getSubCategoryById(subCategoryId);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{subCategoryId}")
    public SimpleResponse update(@PathVariable Long subCategoryId, @RequestBody @Valid SubCategoryRequest subCategoryRequest){
        return subCategoryService.updateSubCategory(subCategoryId,subCategoryRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{subCategoryId}")
    public SimpleResponse deleteSubCategory(@PathVariable Long subCategoryId){
        return subCategoryService.deleteSubCategory(subCategoryId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/sort/{categoryId}")
    public List<SubCategoryResponse>getAll(@PathVariable Long categoryId,@RequestParam String ascOrDesc){
        return subCategoryService.getAllSubCategoriesByCategoryId(categoryId, ascOrDesc);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/groupBy")
    public Map<Long,List<SubCategoryResponse>>groupBy(){
     return subCategoryService.groupByCategory();
    }

}
