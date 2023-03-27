package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.menuItem.MenuItemResponse;
import peaksoft.dto.response.menuItem.MenuItemSearchResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.dto.response.menuItem.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem  m ")
    List<MenuItemResponse>getAllMenuItems();
    List<MenuItem>getAllByStopListNull();
    @Query("select new peaksoft.dto.response.menuItem.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem  m where m.id=:menuItemId")
    Optional<MenuItemResponse>getMenuItemById(Long menuItemId);
    @Query("select new peaksoft.dto.response.menuItem.MenuItemSearchResponse(m.name,c.name,s.name)from MenuItem m  join m.subCategory s join s.category c where (m.name ilike %:word or c.name ilike %:word or s.name ilike %:word)")
    List<MenuItemSearchResponse>searchMenuItem(String word);
    @Query("select new peaksoft.dto.response.menuItem.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m order by m.price asc ")
    List<MenuItemResponse>sortAsc();

    @Query("select new peaksoft.dto.response.menuItem.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m order by m.price desc")
    List<MenuItemResponse>sortDesc();
    List<MenuItemResponse>findByIsVegetarian(Boolean isVegetarian);
    Page<MenuItemResponse> findAllBy(Pageable pageable);
    Boolean existsMenuItemByName(String name);

}
