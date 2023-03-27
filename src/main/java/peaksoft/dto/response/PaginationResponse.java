package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.dto.response.menuItem.MenuItemResponse;
import peaksoft.dto.response.restaurant.RestaurantResponse;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    private List<MenuItemResponse>menuItemResponses;
    private int currentPage;
    private int pageSize;
}
