package peaksoft.dto.response.menuItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class MenuItemSearchResponse {
    private String subCategoryName;
    private String categoryName;
    private String menuItemName;
}
