package com.example.tv_track_backend.show;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.tv_track_backend.show.dto.UpdateShowRequest;
import com.example.tv_track_backend.utils.JsonNullableMapper;

@Mapper(componentModel = "spring", uses = JsonNullableMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShowMapper {

    void updateShow(UpdateShowRequest request, @MappingTarget Show show);
}
