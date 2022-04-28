package vp.seminarska.airplanemanagmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.model.User;
import vp.seminarska.airplanemanagmentapp.model.UserRole;
import vp.seminarska.airplanemanagmentapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl  {
    @Autowired
    private  UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User registrationDto)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        registrationDto.setPassword(encoder.encode(registrationDto.getPassword()));
        UserRole memberRole = new UserRole("ADMIN");
        List<UserRole> roles = new ArrayList<>();
        roles.add(memberRole);
        registrationDto.setUserRole(roles);
        userRepository.save(registrationDto);
        return registrationDto;
    }


}
