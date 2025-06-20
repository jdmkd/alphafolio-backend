package com.alphafolio.backend.user.service;


import com.alphafolio.backend.user.dto.UserDTO;
import com.alphafolio.backend.user.mapper.UserMapper;
import com.alphafolio.backend.user.model.User;
import com.alphafolio.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // Spring Security method to load user during authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getAccountRole().name()))
        );
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();

        if (users.isEmpty()) {
            throw new RuntimeException("No users found in the system.");
        }
        return users;
    }

    public Optional<UserDTO> getUserById(Long Id){
        return Optional.ofNullable(
                userRepository.findById(Id)
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new RuntimeException("User with ID " + Id + " not found."))
        );
    }

    public Optional<UserDTO> getUserByUserName(String username){
        return Optional.ofNullable(
                userRepository.findByUsername(username)
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found."))
        );
    }

    public UserDTO updateUser(UserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Cannot update: User with ID " + dto.getId() + " does not exist."));

        // Update fields (only editable ones)
        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setBio(dto.getBio());
        user.setLocation(dto.getLocation());
        user.setProfilePhotoUrl(dto.getProfilePhotoUrl());

        //....

        return UserMapper.toDTO(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

    public UserDTO setUserStatus(Long id, boolean isActive) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot update status: User with ID " + id + " not found."));
        user.setActive(isActive);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> searchUsers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new RuntimeException("Search keyword must not be empty.");
        }

        List<User> matchedUsers = userRepository.searchByKeyword(keyword);

        if (matchedUsers.isEmpty()) {
            throw new RuntimeException("No users found matching keyword: '" + keyword + "'");
        }

        return matchedUsers.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

}
