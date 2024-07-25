package com.example.expensemanager.service.concretes;


import com.example.expensemanager.dto.user.requests.CreateUserRequest;
import com.example.expensemanager.dto.user.requests.UpdateUserRequest;
import com.example.expensemanager.dto.user.response.*;
import com.example.expensemanager.exception.BussinessExcepiton;
import com.example.expensemanager.mapper.UserMapper;
import com.example.expensemanager.repository.UserRepository;
import com.example.expensemanager.service.abstracts.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.el.lang.ELArithmetic;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.expensemanager.model.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saverUser(User user) {

        userRepository.save(user);
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user= UserMapper.INSTANCE.createUserMapper(request);
        User createdUser= userRepository.save(user);
        return new CreateUserResponse(
                createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getEmail(),
                createdUser.getPassword(),
                createdUser.getRole(),
                createdUser.getCreatedAt(),
                createdUser.getUpdatedAt()
        );
    }

    @Override
    public Optional<GetUserByIdResponse> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper.INSTANCE::getUserByIdMapper);
    }

    @Override
    public Optional<GetUserByUsernameResponse> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        //return user.map(UserMapper.INSTANCE::getUserByUsernameMapper);
        return null;
    }

    @Override
    public List<GetAllUserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.userListMapper(users);
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }
        else throw new BussinessExcepiton("User not found : " + id);

    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User existingUser = user.get();
            User userMapper = UserMapper.INSTANCE.updateUserMapper(updateUserRequest,existingUser);
            User updatedUser = userRepository.save(userMapper);
            return new UpdateUserResponse(
                    updatedUser.getId(),
                    updatedUser.getUsername(),
                    updatedUser.getEmail(),
                    updatedUser.getPassword(),
                    updatedUser.getRole(),
                    updatedUser.getTransaction(),
                    updatedUser.getCreatedAt(),
                    updatedUser.getUpdatedAt()
            );
        }
        else throw new BussinessExcepiton("User not found : " + id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        else throw new BussinessExcepiton("User not found : " + username);
    }


}
