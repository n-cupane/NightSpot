package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.SpotMapper;
import com.nighter.nightspot.mapper.UserMapper;
import com.nighter.nightspot.models.*;
import com.nighter.nightspot.repository.SpotRepositoryJPA;
import com.nighter.nightspot.repository.UserRepositoryJPA;
import com.nighter.nightspot.security.jwt.JWTUtilities;
import com.nighter.nightspot.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    @Autowired
    private UserRepositoryJPA repo;

//    @Autowired
//    private SpotRepositoryJPA spotRepo;

    private final UserMapper mapper;

    private final SpotMapper spotMapper;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtilities jwtUtilities;

    private static final String imagesFolder = "src/main/resources/static/images";


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
    public void uploadPhoto(UploadImageRequest image, String username) throws NoResultException, IOException {

        byte[] imageByte = image.getImageData();

        String fileName = System.currentTimeMillis() + "_" + image.getFileName();

        String pathName = imagesFolder + File.separator + fileName;

        Files.write(Paths.get(pathName),imageByte);

        User u = mapper.fromUserDTO(findByUsername(username));

        u.setPhoto("images/" + fileName);

        repo.save(u);

    }



    @Override
    public void save(UpdateUserDTO user) {
        Optional<User> u = repo.findById(user.getId());
        User userGet = u.get();
        System.out.println(userGet);
        userGet.setEmail(user.getEmail());
        userGet.setUsername(user.getUsername());
        userGet.setFirstName(user.getFirstName());
        userGet.setLastName(user.getLastName());
        userGet.setInstagramHandle(user.getInstagramHandle());
        userGet.setPassword(user.getPassword());
        userGet.setDateOfBirth(user.getDateOfBirth());
        userGet.setPhoto(user.getPhoto());
        System.out.println(userGet);
        repo.save(
                userGet
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
    public String login(String username, String password) throws NoResultException {

        User user = repo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username not found: " + username)
                );

        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtilities.generateToken(user);
        }
        else throw new BadCredentialsException("Wrong password");

    }

    @Override
    public void addFavorite(SpotWithCategoryDTO spot, UserDTO user) {
        List<SpotWithCategoryDTO> spots = user.getSpots();
        boolean contains = false;

        for (SpotWithCategoryDTO userSpots : spots) {
            if(userSpots.getId() == spot.getId()) {
                contains = true;
            }
        }

        if(!contains) {
            user.getSpots().add(spot);
            User u = mapper.fromUserDTO(user);
            repo.save(u);
        }

    }

    @Override
    public void updateUserSpots(Long id, Spot spot) throws NoResultException {
        User u = mapper.fromUserDTO(findById(id));
        List<Spot> spots = new ArrayList<>(u.getSpots());
        System.out.println("PRIMA: " + u.getSpots());
        u.setSpots(
                spots.stream()
                        .filter(s -> s.getId() == spot.getId())
                        .toList()
        );
        System.out.println("DOPO" + u.getSpots());
        repo.save(u);

    }

    @Override
    public List<UserDTO> findAllAdmins() {
        return repo.findAllAdmins().stream()
                .map(mapper::toUserDTO)
                .toList();
    }

    @Override
    public void updateRole(Long id, Role role) throws NoResultException {
        repo.updateRole(id, role);
    }

}
