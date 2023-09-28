package br.pucmg.sigam.monitoramento.infra.configurations.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String login;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String login, Collection<? extends GrantedAuthority> authorities) {
        this.login = login;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(String login, String rolesStr) {
        var adjustedRoles = rolesStr
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
        List<GrantedAuthority> authorities = Arrays.stream(adjustedRoles.split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                login,
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
