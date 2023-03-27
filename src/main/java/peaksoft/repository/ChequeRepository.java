package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.cheque.ChequeResponse;
import peaksoft.entity.Cheque;

import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select avg (ch.grandTotal)  from  Cheque ch  where ch.user.restaurant.id=:restaurantId")
    int averageSumma(Long restaurantId);
    @Query("select new peaksoft.dto.response.cheque.ChequeResponse(c.id,concat( c.user.lastName,c.user.firstName),c.menuItems,c.user.restaurant.service,c.priceAverage,c.grandTotal) from Cheque  c ")
    List<ChequeResponse>getAll();
}