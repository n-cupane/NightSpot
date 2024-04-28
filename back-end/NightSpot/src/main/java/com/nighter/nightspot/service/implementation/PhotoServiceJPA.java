package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoWithoutSpot;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.PhotoMapper;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.repository.PhotoRepositoryJPA;
import com.nighter.nightspot.repository.SpotRepositoryJPA;
import com.nighter.nightspot.service.definition.PhotoService;
import com.nighter.nightspot.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceJPA implements PhotoService {

    @Autowired
    private PhotoRepositoryJPA repo;

    @Autowired
    private SpotRepositoryJPA spotRepo;

    private final PhotoMapper mapper;

    @Override
    public byte[] findById(Long id) throws NoResultException {
        Optional<Photo> dbPhoto = repo.findById(id);
        byte[] photo = ImageUtil.decompressImage(dbPhoto.get().getPhoto());
        return photo;
    }

    @Override
    public List<PhotoDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toPhotoDTO)
                .toList();
    }

    @Override
    public void save(MultipartFile file, Long spotId) throws IOException {

        Photo photo = new Photo();
        photo.setPhoto(
                ImageUtil.compressImage(file.getBytes())
        );
        photo.setSpot(spotRepo.findById(spotId).get());
        repo.save(
                photo
        );



    }

    @Override
    public void save(UpdatePhotoDTO photo) {
        Photo p = repo.getById(photo.getId());
        if(photo.getPhoto()==null) {
            photo.setPhoto(p.getPhoto());
        }
        repo.save(
                mapper.fromUpdatePhotoDTO(photo)
        );
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
