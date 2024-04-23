package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insertPhoto(@Valid @RequestBody InsertPhotoDTO photo) {
        try {
            photoService.save(photo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updatePhoto(@Valid @RequestBody UpdatePhotoDTO photo) {
        try {
            photoService.save(photo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/show/id/{id}")
    public ResponseEntity<PhotoDTO> showPhoto(@PathVariable Long id) throws NoResultException {
        PhotoDTO photo = photoService.findById(id);
        return ResponseEntity.ok(photo);
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<PhotoDTO>> showAllPhotos() {
        List<PhotoDTO> photos = photoService.findAll();
        return ResponseEntity.ok(photos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) throws NoResultException {
        photoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
