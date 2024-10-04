package com.example.dreamshop.service.user;

import com.example.dreamshop.dto.UserDto;
import com.example.dreamshop.exception.ResourceNotFoundEXception;
import com.example.dreamshop.model.Role;
import com.example.dreamshop.model.User;
import com.example.dreamshop.repo.RoleRepo;
import com.example.dreamshop.repo.UserRepo;
import com.example.dreamshop.request.CreateUserRequest;
import com.example.dreamshop.request.UserUpdateRequest;
import com.example.dreamshop.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final RoleRepo roleRepo;
   private final AuthenticationManager authenticationManager;
   private final JwtUtils jwt;
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return user;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        Role role = roleRepo.findByName("ROLE_USER");
        return Optional.of(request).filter(user -> !userRepo.existsByEmail(request.getEmail())).map(user -> {
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            newUser.setRole(role);
            return userRepo.save(newUser);
        }).orElseThrow(() -> new ResourceNotFoundEXception(
                request.getEmail() + "already exists"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long Id) {
        User user = getUserById(Id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepo.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.findById(id).ifPresentOrElse(userRepo::delete, () -> {
            throw new ResourceNotFoundEXception("user not found");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
