package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.user.UserResponse;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("select new peaksoft.dto.response.user.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth ,u.phoneNumber,u.experience)from User u")
    List<UserResponse> getAllUsers();
    @Query("select new peaksoft.dto.response.user.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth ,u.phoneNumber,u.experience)from User u  where u.id =?1")
    Optional<UserResponse> getUserById(Long userId);
    Boolean existsUserByEmail(String email);


}