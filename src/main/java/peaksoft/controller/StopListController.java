package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.stopList.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.stopList.StopListResponse;
import peaksoft.service.StopListService;

import java.util.List;

@RestController
@RequestMapping("/api/stopLists")
public class StopListController {
    private final StopListService stopListService;

    public StopListController(StopListService stopListService) {
        this.stopListService = stopListService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse saveStopList(@RequestBody @Valid StopListRequest stopListRequest){
        return stopListService.saveStopList(stopListRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping
    public List<StopListResponse>getAll(){
        return stopListService.getAllStopList();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/{stopListId}")
    public StopListResponse getStopListId(@PathVariable Long stopListId){
        return stopListService.getStopListById(stopListId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{stopListId}")
    public SimpleResponse update(@PathVariable Long stopListId,@RequestBody @Valid StopListRequest stopListRequest){
        return stopListService.updateStopList(stopListId,stopListRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{stopListId}")
    public SimpleResponse delete(@PathVariable Long stopListId){
        return stopListService.deleteStopList(stopListId);
    }
}
