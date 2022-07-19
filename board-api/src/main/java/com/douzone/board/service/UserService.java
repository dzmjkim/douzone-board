package com.douzone.board.service;

import com.douzone.board.entity.User;
import com.douzone.board.entity.Role;
import com.douzone.board.entity.UserRole;
import com.douzone.board.repository.RoleRepository;
import com.douzone.board.repository.UserRepository;
import com.douzone.board.repository.UserRoleRepository;
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
    private final UserRoleRepository userRoleRepository;

    public User create(User user) throws IllegalAccessException {
        log.info("계정을 생성합니다. username => {}", user.getUsername());

        // 이미 가입된 유저라면
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            log.error("이미 가입된 유저입니다. username -> " + user.getUsername());
//            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + user.getUsername());
//        }
        //FixMe: role 받아서 하는거 구현하면 수정되야 할 구문
        Role role = roleRepository.findById(1L).orElseThrow(IllegalAccessException::new);

        // TODO 1 : password 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // TODO 2 : role을 받아서 admin, user 구분
        UserRole userRole = UserRole.builder()
            .user(user)
            .role(role).build();

        userRoleRepository.save(userRole);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("사용자가 로그인을 시도합니다. username => {}", username);

        // find user
        User user = userRepository.findByUsername(username);

        //Fixme 1
//        List<Role> userRoles = userRoleRepository.findRoleByUsername(user.getUsername());


        if (user == null) {
            log.error("user 정보가 db에 존재하지 않습니다.");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("user 정보를 찾았습니다 username => {}", username);
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

        // Fixme
//        user.getRoles().add(role);
    }

}
