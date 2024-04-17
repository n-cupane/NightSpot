package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User fromInsertUserDTO(InsertUserDTO insertUserDTO);

    User fromUpdateUserDTO(UpdateUserDTO updateUserDTO);

}
