package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.cheque.ChequeRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.cheque.ChequeResponse;
import peaksoft.service.ChequeService;

import java.util.List;

@RestController
@RequestMapping("/api/cheques")
public class ChequeController {
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid ChequeRequest chequeRequest){
        return chequeService.saveCheque(chequeRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @GetMapping
    public List< ChequeResponse> getAll(){
        return chequeService.getAllCheques();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @GetMapping("/get/{chequeId}")
    public ChequeResponse getById(@PathVariable Long chequeId){
        return chequeService.getByIdCheque(chequeId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{chequeId}")
    public SimpleResponse update(@PathVariable Long chequeId,@RequestBody @Valid ChequeRequest chequeRequest){
        return chequeService.updateCheque(chequeId,chequeRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{chequeId}")
    public SimpleResponse delete(@PathVariable Long chequeId){
        return chequeService.deleteByIdCheque(chequeId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")

    @GetMapping("/sum/{waiterId}")
    public int chequeSum(@PathVariable Long waiterId){
        return chequeService.chequeSumByWaiterId(waiterId);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/res/{restaurantId}")
    public int chequeSumByRestaurantId(@PathVariable Long restaurantId){
        return chequeService.averageSumma(restaurantId);
    }
}
