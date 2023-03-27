package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.category.CategoryResponse;
import peaksoft.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new peaksoft.dto.response.category.CategoryResponse(c.id,c.name)from Category c")
    List<CategoryResponse> getAllCategories();
    Boolean existsCategoryByName(String name);
    @Query("select new peaksoft.dto.response.category.CategoryResponse(c.id,c.name)from Category  c where c.id =?1")
    Optional<CategoryResponse> getCategoryById(Long userId);
}