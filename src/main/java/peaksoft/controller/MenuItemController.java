package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.menuItem.MenuItemRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.menuItem.MenuItemResponse;
import peaksoft.dto.response.menuItem.MenuItemSearchResponse;
import peaksoft.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menuItems")
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping()
    public SimpleResponse saveMenuItem(@RequestBody @Valid MenuItemRequest menuItemRequest){
        return menuItemService.saveMenuItem(menuItemRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping
    public List<MenuItemResponse>getAll(){
        return menuItemService.getAllMenuItems();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/{menuItemId}")
    public MenuItemResponse getById(@PathVariable Long menuItemId){
        return menuItemService.getMenuItemById(menuItemId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{menuItemId}")
    public SimpleResponse updateMenuItem( @Valid @PathVariable Long menuItemId,@RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.updateMenuItem(menuItemId,menuItemRequest);
    }
    @DeleteMapping("/{menuItemId}")
    public SimpleResponse deleteMenuItem(@PathVariable Long menuItemId){
        return menuItemService.deleteMenuItem(menuItemId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/search")
    public List<MenuItemSearchResponse>searchResponses( @RequestParam(name = "word",required = false) String word){
        return menuItemService.search(word);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/sort")
    public List<MenuItemResponse>sort(@RequestParam String ascOrDesc){
        return menuItemService.sort(ascOrDesc);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/filter")
    public List<MenuItemResponse>filter(@RequestParam Boolean isVegetarian){
        return menuItemService.findByIsVegetarian(isVegetarian);
    }

    @GetMapping("/pagination")
    public PaginationResponse getPagination(@RequestParam int page,
                                            @RequestParam int size){
        return menuItemService.getMenuItemPagination(page, size);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/stopList")
    public List<MenuItemResponse>getAllByStopList(){
        return menuItemService.getAllByStopList();
    }
}

