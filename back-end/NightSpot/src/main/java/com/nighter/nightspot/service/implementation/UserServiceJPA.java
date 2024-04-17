package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.UserMapper;
import com.nighter.nightspot.repository.UserRepositoryJPA;
import com.nighter.nightspot.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    @Autowired
    private UserRepositoryJPA repo;

    private final UserMapper mapper;


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
    public void save(InsertUserDTO user) {
        repo.save(
                mapper.fromInsertUserDTO(user)
        );
    }

    @Override
    public void save(UpdateUserDTO user) {
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
                repo.findByEmail(email)
        );
    }

    @Override
    public UserDTO findByUsername(String username) throws NoResultException {
        return mapper.toUserDTO(
                repo.findByUsername(username)
        );
    }
}
