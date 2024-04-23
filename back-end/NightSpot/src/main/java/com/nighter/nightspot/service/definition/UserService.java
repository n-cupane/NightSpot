package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id) throws NoResultException;

    List<UserDTO> findAll();

    void save(InsertUserDTO user);

    void save(UpdateUserDTO user);

    void deleteById(Long id) throws NoResultException;

    UserDTO findByEmail(String email) throws NoResultException;

    UserDTO findByUsername(String username) throws NoResultException;

    String login(String username, String password) throws Exception;

}
