package peaksoft.dto.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RestaurantResponse {
    private Long id;
    private String name;
    private String location;
    private String restType;

    private int service;
    private int numberOfEmployees;

    public RestaurantResponse(Long id,String name, String location, String restType, int service) {
        this.name = name;
        this.id=id;
        this.location = location;
        this.restType = restType;
        this.service = service;
    }
}
