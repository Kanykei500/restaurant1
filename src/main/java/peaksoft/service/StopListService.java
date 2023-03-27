package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.stopList.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.stopList.StopListResponse;

import java.util.List;

public interface StopListService {
    SimpleResponse saveStopList(StopListRequest stopListRequest);
    List<StopListResponse> getAllStopList();
    StopListResponse getStopListById(Long stopListId);
    SimpleResponse updateStopList(Long stopListId,StopListRequest stopListRequest);
    SimpleResponse deleteStopList(Long stopListId);
}
