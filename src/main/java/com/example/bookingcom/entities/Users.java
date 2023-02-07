package com.example.bookingcom.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Gender gender;
    private String passportId;
    private String phoneNumber;
    private String email;
    private String password;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Country citizenship;
    @Nullable
    private String photo;
    private Boolean children;
    @JsonIgnore
    @OneToOne
    private Bank_Card bank_card;
    @Nullable
    @JsonIgnore
    @ManyToMany
    private List<Hotels> favoriteHotels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
