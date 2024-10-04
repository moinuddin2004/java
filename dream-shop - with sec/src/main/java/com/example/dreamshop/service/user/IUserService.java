package com.example.dreamshop.service.user;

import com.example.dreamshop.dto.UserDto;
import com.example.dreamshop.model.User;
import com.example.dreamshop.request.CreateUserRequest;
import com.example.dreamshop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long id);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long Id);
    void deleteUser(Long id);

    UserDto convertUserToDto(User user);
}
