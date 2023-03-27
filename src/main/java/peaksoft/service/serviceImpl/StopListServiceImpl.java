package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.stopList.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.stopList.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    public StopListServiceImpl(StopListRepository stopListRepository, MenuItemRepository menuItemRepository) {
        this.stopListRepository = stopListRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse saveStopList(StopListRequest stopListRequest) {
        MenuItem menuItem = menuItemRepository.findById(stopListRequest.menuItemId())
                .orElseThrow(() -> new NotFoundException("Not found"));

        List<StopList> stopLists = stopListRepository.findAll();
        for (StopList stopList1 : stopLists) {
            if (stopList1.getDate().equals(stopListRequest.date()) && stopList1.getMenuItem().getId().equals(stopListRequest.menuItemId())) {
                throw new AlreadyExistException("Bir data memen bir ele jolu saktalat");
            }
        }
        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.reason());
        stopList.setDate(stopListRequest.date());
        stopList.setMenuItem(menuItem);
        stopListRepository.save(stopList);

        return SimpleResponse.builder().status(HttpStatus.OK)
                .message("successfully saved").build();
    }


    @Override
    public List<StopListResponse> getAllStopList() {
        return stopListRepository.getAll();
    }

    @Override
    public StopListResponse getStopListById(Long stopListId) {
        return stopListRepository.getStopListById(stopListId).
                orElseThrow(() -> new NotFoundException(String.format("StopLisr with id" + stopListId + "doesn't exists")));

    }

    @Transactional

    @Override
    public SimpleResponse updateStopList(Long stopListId, StopListRequest stopListRequest) {
        StopList stopList = stopListRepository.findById(stopListId).orElseThrow(() -> new NotFoundException(String.format("StopLisr with id" + stopListId + "doesn't exists")));
        stopList.setReason(stopListRequest.reason());
        stopList.setDate(stopListRequest.date());
        stopListRepository.save(stopList);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully updated").build();
    }

    @Override
    public SimpleResponse deleteStopList(Long stopListId) {
        stopListRepository.deleteById(stopListId);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();
    }
}
