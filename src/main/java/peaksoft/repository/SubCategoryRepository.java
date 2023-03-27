package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.subCategory.SubCategoryResponse;
import peaksoft.entity.SubCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select new peaksoft.dto.response.subCategory.SubCategoryResponse(s.id,s.name,s.category.id)from SubCategory s ")
    List<SubCategoryResponse>getAll();
    Boolean existsSubCategoryByName(String name);
    @Query("select new peaksoft.dto.response.subCategory.SubCategoryResponse(s.id,s.name,s.category.id)from SubCategory s where s.id=:subCategoryId")
    Optional<SubCategoryResponse>getSubCategoriesById(Long subCategoryId);
    @Query("select new peaksoft.dto.response.subCategory.SubCategoryResponse(s.id,s.name,s.category.id)from SubCategory s where s.category.id=:categoryId order by s.name asc ")
    List<SubCategoryResponse>getAllSubCategoriesByCategoryIdAndSortAsc(Long categoryId);
    @Query("select new peaksoft.dto.response.subCategory.SubCategoryResponse(s.id,s.name,s.category.id)from SubCategory s where s.category.id=:categoryId order by s.name desc  ")
    List<SubCategoryResponse>getAllSubCategoriesByCategoryIdAndSortDesc(Long categoryId);

}
