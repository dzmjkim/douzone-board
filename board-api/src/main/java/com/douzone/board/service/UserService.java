package com.douzone.board.service;

import com.douzone.board.entity.User;
import com.douzone.board.entity.Role;
import com.douzone.board.repository.RoleRepository;
import com.douzone.board.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        log.info("서비스에서 회원가입을 진행합니다.");

        // 이미 가입된 유저라면
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            log.error("이미 가입된 유저입니다. username -> " + user.getUsername());
//            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + user.getUsername());
//        }

        // TODO 1 : password 암호화
        // TODO 2 : repository 에 저장하기 전에 role 추가

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserService : 진입");

        // find user
        User user = userRepository.findByUsername(username);

        //Fixme 1
//        List<Role> userRoles = userRoleRepository.findRoleByUsername(user.getUsername());


        if (user == null) {
            log.error("user 정보가 db에 존재하지 않습니다.");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //Fixme 2
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        //Fixme
//        user.getRoles().add(role);
    }

}
