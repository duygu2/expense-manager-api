package com.example.expensemanager.mapper;


import com.example.expensemanager.dto.user.requests.CreateUserRequest;
import com.example.expensemanager.dto.user.requests.UpdateUserRequest;
import com.example.expensemanager.dto.user.response.GetAllUserResponse;
import com.example.expensemanager.dto.user.response.GetUserByIdResponse;
import com.example.expensemanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User createUserMapper(CreateUserRequest user);

    GetUserByIdResponse getUserByIdMapper(User user);
    User getUserByUsernameMapper(User user);

    GetAllUserResponse getAllUserMapper(User user);

    List<GetAllUserResponse> userListMapper(List<User> users);

    User updateUserMapper(UpdateUserRequest updateUserRequest , @MappingTarget User user);


}

