package br.pucmg.sigam.autenticacao.application.domain.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private UserRole role;

    @Column
    private Boolean locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role.equals(UserRole.OUTSOURCED)) {
            return List.of(new SimpleGrantedAuthority("ROLE_OUTSOURCED"));
        } else if (this.role.equals(UserRole.ADMIN)) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonExpired() && isAccountNonLocked() && isAccountNonExpired();
    }
}
