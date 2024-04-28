package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoWithoutSpot;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    byte[] findById(Long id) throws NoResultException;

    List<PhotoDTO> findAll();

    void save(MultipartFile file, Long spotId) throws Exception;

    void save(UpdatePhotoDTO photo);

    void deleteById(Long id);

}
