package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Role;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface UserService {

    UserDTO findById(Long id) throws NoResultException;

    List<UserDTO> findAll();

    void save(InsertUserDTO user);

    void uploadPhoto(MultipartFile file, String username) throws NoResultException;

    void save(UpdateUserDTO user);

    void deleteById(Long id) throws NoResultException;

    UserDTO findByEmail(String email) throws NoResultException;

    UserDTO findByUsername(String username) throws NoResultException;

    String login(String username, String password) throws Exception;

    void addFavorite(SpotWithCategoryDTO spot, UserDTO user);

    List<UserDTO> findAllAdmins();

    void updateRole(Long id, Role role) throws NoResultException;

}
