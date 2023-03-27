package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.user.UserAssignRequest;
import peaksoft.dto.request.user.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.user.UserResponse;

import java.util.List;
@Service
public interface UserService {
    SimpleResponse saveUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
    SimpleResponse updateUser(Long userId,UserRequest userRequest);
    SimpleResponse deleteUser(Long userId);
    SimpleResponse assignEmployees(UserAssignRequest userAssignRequest);

}
