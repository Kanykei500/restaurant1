package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.cheque.ChequeRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.cheque.ChequeResponse;

import java.util.List;

@Service
public interface ChequeService {
   SimpleResponse saveCheque(ChequeRequest chequeRequest);
    List<ChequeResponse> getAllCheques();
    ChequeResponse getByIdCheque(Long chequeId);
    SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest);
    SimpleResponse deleteByIdCheque(Long chequeId);
    int chequeSumByWaiterId(Long waiterId);
    int averageSumma(Long restaurantId);
}
