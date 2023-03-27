package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.user.UserAssignRequest;
import peaksoft.dto.request.user.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.user.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('WAITER','CHEF','ADMIN')")
    @PostMapping
    public SimpleResponse saveUser(@RequestBody @Valid UserRequest userRequest){
        return userService.saveUser(userRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAllUsers();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping ("/{userId}")
    public UserResponse getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}")
    public SimpleResponse updateUser(@PathVariable Long userId,@RequestBody @Valid UserRequest userRequest){
        return userService.updateUser(userId,userRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    @DeleteMapping("/{userId}")
    public SimpleResponse deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign")
    public SimpleResponse assign( @Valid @RequestBody  UserAssignRequest userAssignRequest){
        return userService.assignEmployees(userAssignRequest);
    }
}

