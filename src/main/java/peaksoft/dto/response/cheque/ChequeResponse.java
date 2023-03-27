package peaksoft.dto.response.cheque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChequeResponse {
    private Long id;
    private String waiterFullName;
    private List<MenuItem>menuItems;
    private int service;
    private int priceAverage;
    private int grandTotal;
}
