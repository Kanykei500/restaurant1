package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.cheque.ChequeRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.cheque.ChequeResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequeService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    public ChequeServiceImpl(ChequeRepository chequeRepository, UserRepository userRepository, MenuItemRepository menuItemRepository) {
        this.chequeRepository = chequeRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse saveCheque(ChequeRequest chequeRequest) {
        int number=0;
        User user = userRepository.findById(chequeRequest.getWaiterId()).
                orElseThrow(() -> new NotFoundException("User with id doesn't exists "));
        Cheque cheque=new Cheque();
        cheque.setUser(user);
        for (MenuItem menuItem : menuItemRepository.findAllById(chequeRequest.getMenuItems())) {
            cheque.addMenuItems(menuItem);
            number += menuItem.getPrice();
    }
        cheque.setPriceAverage(number);
        cheque.setCreatedAt(LocalDate.now());
        int total = (number * user.getRestaurant().getService())/100;
        cheque.setGrandTotal(total+number);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully saved").build();
    }

    @Override
    public List<ChequeResponse> getAllCheques() {



        return chequeRepository.getAll() ;
    }

    @Override
    public ChequeResponse getByIdCheque(Long chequeId) {
        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow(() -> new NotFoundException("Cheque with id: " + chequeId + " is no exist!"));
         ChequeResponse chequeResponse=new ChequeResponse();
        chequeResponse.setId(cheque.getId());
        chequeResponse.setWaiterFullName(cheque.getUser().getFirstName()+" "+cheque.getUser().getLastName());
        chequeResponse.setMenuItems(cheque.getMenuItems());
        chequeResponse.setService(cheque.getUser().getRestaurant().getService());
        chequeResponse.setPriceAverage(cheque.getPriceAverage());
        chequeResponse.setGrandTotal(cheque.getGrandTotal());
        return chequeResponse;
    }

    @Override
    public SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest) {
        Cheque cheque = chequeRepository.findById(chequeIdd).orElseThrow(() -> new NotFoundException("Cheque with id: "+chequeIdd+" doesn't exists"));
        User user = userRepository.findById(chequeRequest.getWaiterId()).orElseThrow(() -> new NotFoundException("User with id: "+chequeRequest.getWaiterId()+" doesn't exists"));
        List<MenuItem>menuItems1=new ArrayList<>();
        for (Long menuId : chequeRequest.getMenuItems()) {
            MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new NotFoundException("MenuItem with id: "+menuId+" doesn't exists"));
            menuItems1.add(menuItem);
        }
        cheque.setMenuItems(menuItems1);
        cheque.setUser(user);
        chequeRepository.save(cheque);

        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully updated").build();
    }

    @Override
    public SimpleResponse deleteByIdCheque(Long chequeId) {
        chequeRepository.deleteById(chequeId);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully deleted").build();
    }

    @Override
    public int chequeSumByWaiterId(Long waiterId) {
       int number = 0;
        for (Cheque cheque : chequeRepository.findAll()) {
            if(cheque.getUser().getId().equals(waiterId) && cheque.getCreatedAt().equals(LocalDate.now())){
                number += cheque.getGrandTotal();
            }
        }
        return number;
    }



    @Override
    public int averageSumma(Long restaurantId) {
        return chequeRepository.averageSumma(restaurantId);
    }
}
