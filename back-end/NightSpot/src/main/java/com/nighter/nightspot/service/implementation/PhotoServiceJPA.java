package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoWithoutSpot;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.PhotoMapper;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.repository.PhotoRepositoryJPA;
import com.nighter.nightspot.service.definition.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceJPA implements PhotoService {

    @Autowired
    private PhotoRepositoryJPA repo;

    private final PhotoMapper mapper;

    @Override
    public PhotoDTO findById(Long id) throws NoResultException {
        return mapper.toPhotoDTO(
                repo.findById(id)
                        .orElseThrow(
                                () -> new NoResultException("Photo with id " + id + " not found")
                        )
        );
    }

    @Override
    public List<PhotoDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toPhotoDTO)
                .toList();
    }

    @Override
    public void save(InsertPhotoDTO photo) {
        repo.save(
                mapper.fromInsertPhotoDTO(photo)
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
