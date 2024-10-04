package com.example.dreamshop.security.user;

import com.example.dreamshop.model.User;
import com.example.dreamshop.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShopUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepo.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ShopUserDetails.buildUserDetails(user);
    }
}
