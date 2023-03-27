package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.menuItem.MenuItemRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.menuItem.MenuItemResponse;
import peaksoft.dto.response.menuItem.MenuItemSearchResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.SubCategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.SubCategoryRepository;
import peaksoft.service.MenuItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubCategoryRepository subCategoryRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository, SubCategoryRepository subCategoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SimpleResponse saveMenuItem(MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(menuItemRequest.restaurantId()).
                orElseThrow(() -> new NotFoundException("MenuItem with id :" + menuItemRequest.restaurantId()+ "doesn't exists"));
        SubCategory subCategory=subCategoryRepository.findById(menuItemRequest.subCategoryId()).orElseThrow(() -> new NoSuchElementException("SubCategoryId with id doesn't exists"));
        if (menuItemRepository.existsMenuItemByName(menuItemRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("MenuItem with name :%s already exists",
                            menuItemRequest.name())).build();
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.name());
        menuItem.setImage(menuItemRequest.image());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setIsVegetarian(menuItemRequest.isVegetarian());
        menuItem.setRestaurant(restaurant);
        menuItem.setSubCategory(subCategory);
        restaurant.addMenuItem(menuItem);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully saved").build();
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.getAllMenuItems();
    }

    @Override
    public MenuItemResponse getMenuItemById(Long menuItemId) {
        return menuItemRepository.getMenuItemById(menuItemId).orElseThrow(() -> new NotFoundException("MenuItem with id" + menuItemId + "doesn't exists"));
    }
    @Transactional

    @Override
    public SimpleResponse updateMenuItem(Long menuItemId, MenuItemRequest menuItemRequest) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() ->
                new NotFoundException(String.format
                        ("MenuItem with id " + menuItemId + "doesn't exists")));
        if (menuItemRepository.existsMenuItemByName(menuItemRequest.name())) {
            return SimpleResponse.builder().
                    status(HttpStatus.CONFLICT).
                    message(String.format("MenuItem with name :%s already exists",
                            menuItemRequest.name())).build();
        }
        menuItem.setName(menuItemRequest.name());
        menuItem.setImage(menuItemRequest.image());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setIsVegetarian(menuItemRequest.isVegetarian());
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully updated").build();
    }

    @Override
    public SimpleResponse deleteMenuItem(Long menuItemId) {
        menuItemRepository.deleteById(menuItemId);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();
    }

    @Override
    public List<MenuItemSearchResponse> search(String word) {
       return menuItemRepository.searchMenuItem(word);
    }

    @Override
    public List<MenuItemResponse> sort(String ascOrDesc) {
        if (ascOrDesc.equals("asc")){
            return menuItemRepository.sortAsc();
        }
        return menuItemRepository.sortDesc();
    }

    @Override
    public List<MenuItemResponse> findByIsVegetarian(Boolean isVegetarian) {
       return menuItemRepository.findByIsVegetarian(isVegetarian);
    }

    @Override
    public PaginationResponse getMenuItemPagination(int page, int size) {
        Pageable pageable= PageRequest.of(page-1, size);
        Page<MenuItemResponse> page1 = menuItemRepository.findAllBy(pageable);
        List<MenuItemResponse>page2=page1.getContent().stream().map(menuItem->new MenuItemResponse(menuItem.getId(),
                menuItem.getName(),menuItem.getImage(),menuItem.getPrice(),menuItem.getDescription(),menuItem.getIsVegetarian())).toList();
        PaginationResponse paginationResponse=new PaginationResponse();
        paginationResponse.setMenuItemResponses(page2);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setPageSize(page1.getTotalPages());

        return paginationResponse;

    }

    @Override
    public List<MenuItemResponse> getAllByStopList() {
        List<MenuItemResponse>list=new ArrayList<>();
        List<MenuItem>list1=menuItemRepository.getAllByStopListNull();
        for (MenuItem menuItem:list1) {
            MenuItemResponse response=new MenuItemResponse(
                    menuItem.getId(),
                    menuItem.getName(),
                    menuItem.getImage(),
                    menuItem.getPrice(),
                    menuItem.getDescription(),
                    menuItem.getIsVegetarian()
            );
            list.add(response);

        }return list;
    }
}


