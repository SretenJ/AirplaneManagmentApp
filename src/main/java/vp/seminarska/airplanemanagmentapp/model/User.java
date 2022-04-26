package vp.seminarska.airplanemanagmentapp.model;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    private UserRole userRole;

    private Boolean active = true;

    @ManyToMany
    private List<Flight> flights;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole==null) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(this.userRole.getName());
        auths.add(authority);
        return auths;

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
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
