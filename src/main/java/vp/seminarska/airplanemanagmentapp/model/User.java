package vp.seminarska.airplanemanagmentapp.model;

import lombok.Getter;
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
@Getter
public class User  {
    @Id
    @Column(name="username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USERs_userrole", joinColumns = {
            @JoinColumn(name = "USERs_USERNAME", referencedColumnName = "username")}, inverseJoinColumns = {
            @JoinColumn(name = "userroles_NAME", referencedColumnName = "name") })
    private List<UserRole> userRole;

    private Boolean active = true;

    @ManyToMany
    private List<Flight> flights;



}
