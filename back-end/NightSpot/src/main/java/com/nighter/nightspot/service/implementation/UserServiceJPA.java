package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.UserMapper;
import com.nighter.nightspot.models.Role;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.repository.UserRepositoryJPA;
import com.nighter.nightspot.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    @Autowired
    private UserRepositoryJPA repo;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO findById(Long id) throws NoResultException {
        return mapper.toUserDTO(
                repo.findById(id)
                        .orElseThrow(
                                () -> new NoResultException("User with id " + id + " not found")
                        )
        );
    }

    @Override
    public List<UserDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toUserDTO)
                .toList();
    }

    @Override
    public void save(InsertUserDTO insertUser) {

        User user = mapper.fromInsertUserDTO(insertUser);
        user.setRole(Role.BASE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    @Override
    public void save(UpdateUserDTO user) {
        Optional<User> u = repo.findById(user.getId());
        User userGet = u.get();
        if(user.getPhoto()==null) {
            user.setPhoto(userGet.getPhoto());
        }
        if(user.getPhoto()==null) {
            user.setInstagramHandle(userGet.getInstagramHandle());
        }
        repo.save(
                mapper.fromUpdateUserDTO(user)
        );
    }

    @Override
    public void deleteById(Long id) throws NoResultException {
        repo.deleteById(id);
    }

    @Override
    public UserDTO findByEmail(String email) throws NoResultException {
        return mapper.toUserDTO(
                repo.findByEmail(email).get()
        );
    }

    @Override
    public UserDTO findByUsername(String username) throws NoResultException {
        return mapper.toUserDTO(
                repo.findByUsername(username).get()
        );
    }

    @Override
    public UserDTO login(String username, String password) throws NoResultException {

        Optional<User> optionalUser = repo.findByUsername(username);
        if(optionalUser.isPresent()) {
            if (!passwordEncoder.matches(password, optionalUser.get().getPassword())) return null;
            return mapper.toUserDTO(optionalUser.get());
        }
        throw new UsernameNotFoundException("Username not found: " + username);

    }
}
