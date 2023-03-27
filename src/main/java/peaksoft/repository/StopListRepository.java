package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.stopList.StopListResponse;
import peaksoft.entity.StopList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StopListRepository extends JpaRepository<StopList, Long> {

    @Query("select new peaksoft.dto.response.stopList.StopListResponse(s.id,s.reason,s.date)from StopList  s ")
    List<StopListResponse>getAll();

    Optional<StopListResponse> getStopListById(Long stopListId);
}