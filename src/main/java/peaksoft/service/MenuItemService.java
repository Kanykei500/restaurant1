package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.menuItem.MenuItemRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.menuItem.MenuItemResponse;
import peaksoft.dto.response.menuItem.MenuItemSearchResponse;

import java.util.List;

@Service
public interface MenuItemService {
    SimpleResponse saveMenuItem(MenuItemRequest menuItemRequest);
    List<MenuItemResponse> getAllMenuItems();
   MenuItemResponse getMenuItemById(Long menuItemId);
    SimpleResponse updateMenuItem(Long menuItemId,MenuItemRequest menuItemRequest);
    SimpleResponse deleteMenuItem(Long menuItemId);
    List<MenuItemSearchResponse> search(String word);
    List<MenuItemResponse>sort(String ascOrDesc);
    List<MenuItemResponse>findByIsVegetarian(Boolean isVegetarian);
    PaginationResponse getMenuItemPagination(int page,int size);
    List<MenuItemResponse>getAllByStopList();

}
