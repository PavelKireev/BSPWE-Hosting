package cz.upce.bpswehosting.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.bpswehosting.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static AuthUser build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));

        return new AuthUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthUser user = (AuthUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getAuthorities());
    }
}
