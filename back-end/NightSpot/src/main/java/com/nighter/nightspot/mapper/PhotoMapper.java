package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.photo.InsertPhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoDTO;
import com.nighter.nightspot.dto.photo.PhotoWithoutSpot;
import com.nighter.nightspot.dto.photo.UpdatePhotoDTO;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface PhotoMapper {

    PhotoWithoutSpot toPhotoWithoutSpot(Photo photo);

    PhotoDTO toPhotoDTO(Photo photo);

    Photo fromInsertPhotoDTO(InsertPhotoDTO insertPhotoDTO);

    Photo fromUpdatePhotoDTO(UpdatePhotoDTO updatePhotoDTO);

}
