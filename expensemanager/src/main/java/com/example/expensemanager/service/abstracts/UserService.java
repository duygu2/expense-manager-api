package com.example.expensemanager.service.abstracts;



import com.example.expensemanager.dto.user.requests.CreateUserRequest;
import com.example.expensemanager.dto.user.requests.UpdateUserRequest;
import com.example.expensemanager.dto.user.response.*;
import com.example.expensemanager.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void saverUser(User user);

    CreateUserResponse createUser(CreateUserRequest user);

    Optional<GetUserByIdResponse> getUserById(Long id);

    Optional<GetUserByUsernameResponse> getUserByUsername(String username);
    List<GetAllUserResponse> getAllUser();

    void deleteUserById(Long id);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest , Long id);
    UserDetails loadUserByUsername(String username);


}
