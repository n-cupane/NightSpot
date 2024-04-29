package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.PhotoService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin()
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/admin/photo/upload/{spotId}")
    public ResponseEntity<Void> uploadPhoto(@RequestParam("image") MultipartFile file, @PathVariable Long spotId) {
        try {
            photoService.save(file, spotId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/photo/update")
    public ResponseEntity<Void> updatePhoto(@Valid @RequestBody UpdatePhotoDTO photo) {
        try {
            photoService.save(photo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all/photo/show/id/{id}")
    public ResponseEntity<byte[]> showPhoto(@PathVariable Long id) throws NoResultException {
        byte[] photo = photoService.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(photo);
    }


    @GetMapping("/photo/show-all")
    public ResponseEntity<List<PhotoDTO>> showAllPhotos() {
        List<PhotoDTO> photos = photoService.findAll();
        return ResponseEntity.ok(photos);
    }

    @DeleteMapping("/photo/delete/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) throws NoResultException {
        photoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
