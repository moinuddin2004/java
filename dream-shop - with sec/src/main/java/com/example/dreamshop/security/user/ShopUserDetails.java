package com.example.dreamshop.security.user;

import com.example.dreamshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ShopUserDetails implements UserDetails{
    private Long id;
    private String email;
    private String password;

        private Collection<GrantedAuthority> authorities;
//    private  GrantedAuthority authorities;

    public static  ShopUserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                        . collect(Collectors.toList());
//        GrantedAuthority  authority = new SimpleGrantedAuthority(user.getRole().getName());
        ShopUserDetails details = new ShopUserDetails();
        details.id = user.getId();
        details.email = user.getEmail();
        details.password = user.getPassword();
        details.authorities = authorities;
        return details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authorities.toString()));

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
