package peaksoft.dto.response.subCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Builder
@Getter
@Setter
@AllArgsConstructor
public class SubCategoryResponse {
    private Long  id;
    private String name;
    private Long categoryId;
}
