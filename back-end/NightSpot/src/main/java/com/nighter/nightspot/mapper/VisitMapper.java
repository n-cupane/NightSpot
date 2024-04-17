package com.nighter.nightspot.mapper;

import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring",uses = UserServiceJPA.class)
public interface VisitMapper {
}
