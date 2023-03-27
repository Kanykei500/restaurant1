package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.user.UserAssignRequest;
import peaksoft.dto.request.user.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.user.UserResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SimpleResponse saveUser(UserRequest userRequest) {

        if (userRepository.existsUserByEmail(userRequest.email())) {
            return SimpleResponse.builder().
                    status(HttpStatus.BAD_REQUEST).
                    message(String.format("User with email :%s already exists",
                            userRequest.email())).build();
        }


        if (userRequest.role().equals(Role.ADMIN)) {
                return SimpleResponse.builder().status(HttpStatus.FORBIDDEN).message("Forbidden").build();
            }
            if (userRequest.role().equals(Role.CHEF)) {
                Period between = Period.between(userRequest.dateOfBirth(), LocalDate.now());
                if (between.getYears() > 45 || between.getYears() < 25) {
                    return SimpleResponse.builder().
                            status(HttpStatus.BAD_REQUEST).
                            message("The cook must be between 25 and 45 years of age").build();
                }
            }
            if (userRequest.role().equals(Role.WAITER)) {
                Period b = Period.between(userRequest.dateOfBirth(), LocalDate.now());
                if (b.getYears() > 30 || b.getYears() < 18) {
                    return SimpleResponse.builder().
                            status(HttpStatus.BAD_REQUEST).
                            message("The waiter must be between 18 and 30 years of age").build();
                }
            }
            if (userRequest.role().equals(Role.WAITER)) {
                if (userRequest.experience() < 1) {
                    return SimpleResponse.builder().
                            status(HttpStatus.BAD_REQUEST).
                            message("Waiter experience must be between 1 and 10.").build();
                }
            }
            if (userRequest.role().equals(Role.CHEF)) {
                if (userRequest.experience() < 2) {
                    return SimpleResponse.builder().
                            status(HttpStatus.BAD_REQUEST).
                            message("Chef experience must be between 2 and 10.").build();
                }
            }
            User user = new User();
            user.setFirstName(userRequest.firstName());
            user.setLastName(userRequest.lastName());
            user.setDateOfBirth(userRequest.dateOfBirth());
            user.setEmail(userRequest.email());
            user.setPassword(passwordEncoder.encode(userRequest.password()));
            user.setExperience(userRequest.experience());
            user.setPhoneNumber(userRequest.phoneNumber());
            user.setRole(userRequest.role());
            userRepository.save(user);
            return SimpleResponse.builder().status(HttpStatus.OK).
                    message("Zayavka kabyl alyndy").build();

        }



        @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return userRepository.getUserById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id" + userId + "doesn't exist")));
    }


    @Transactional
    @Override

    public SimpleResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.
                    format("User with id" + userId + "doesn't exist")));
        if (userRepository.existsUserByEmail(userRequest.email())) {
            return SimpleResponse.builder().
                    status(HttpStatus.BAD_REQUEST).
                    message(String.format("User with email :%s already exists",
                            userRequest.email())).build();
        }
        if (userRequest.role().equals(Role.ADMIN)) {
            return SimpleResponse.builder().status(HttpStatus.FORBIDDEN).message("Forbidden").build();
        }
        if (userRequest.role().equals(Role.CHEF)) {
            Period between = Period.between(userRequest.dateOfBirth(), LocalDate.now());
            if ( between.getYears() > 45  || between.getYears() < 25 ) {
                return SimpleResponse.builder().
                        status(HttpStatus.BAD_REQUEST).
                        message("The cook must be between 25 and 45 years of age").build();
            }
        }if (userRequest.role().equals(Role.WAITER)) {
            Period b = Period.between(userRequest.dateOfBirth(), LocalDate.now());
            if (b.getYears()>30 ||  b.getYears()<18) {
                return SimpleResponse.builder().
                        status(HttpStatus.BAD_REQUEST).
                        message("The waiter must be between 18 and 30 years of age").build();
            }
        }
        if (userRequest.role().equals(Role.WAITER)) {
            if (userRequest.experience()< 1 ) {
                return SimpleResponse.builder().
                        status(HttpStatus.BAD_REQUEST).
                        message("Waiter experience must be between 1 and 10.").build();
            }
        }if (userRequest.role().equals(Role.CHEF)) {
            if (userRequest.experience() < 2) {
                return SimpleResponse.builder().
                        status(HttpStatus.BAD_REQUEST).
                        message("Chef experience must be between 2 and 10.").build();
            }
        }

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setDateOfBirth(userRequest.dateOfBirth());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setExperience(userRequest.experience());
        userRepository.save(user);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("User with firstname : %s "+", LastName: %s "+" dateOfBirth: %s "+"phoneNumber: %s "+"experience: %s "+" successfully updated",
                        user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getPhoneNumber(),user.getExperience())).build();


    }




    @Override
    public SimpleResponse deleteUser(Long userId) {
       userRepository.deleteById(userId);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message("Successfully deleted").build();
    }

    @Override
    public SimpleResponse assignEmployees(UserAssignRequest userAssignRequest) {


        if (userAssignRequest.userId()==1){
           throw new AlreadyExistException("Conflict!!!");
        }
        Restaurant restaurant = restaurantRepository.findById(userAssignRequest.restaurantId()).orElseThrow(() -> new NoSuchElementException("Restaurant with id doesn't exists"));
        User user1 = userRepository.findById(userAssignRequest.userId()).orElseThrow(() -> new NoSuchElementException("User with id doesn't exists"));
        user1.setRestaurant(restaurant);
        restaurant.addEmployees(user1);
        userRepository.save(user1);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Jumushka kabyl alyndy").build();



    }


}

