package com.grupo7.studentsrepo.mapper;

import com.grupo7.studentsrepo.dto.request.AdminRequestDTO;
import com.grupo7.studentsrepo.dto.response.AdminResponseDTO;

import com.grupo7.studentsrepo.model.entity.Admin;

public class AdminMapper {

    // RequestDTO -> Entity
    public static Admin toEntity(AdminRequestDTO dto) {

        Admin admin = new Admin();

        admin.setNombre(dto.getNombre());

        admin.setEmail(dto.getEmail());

        return admin;
    }

    // Entity -> ResponseDTO
    public static AdminResponseDTO toResponseDTO(Admin admin) {

        AdminResponseDTO dto =
                new AdminResponseDTO();

        dto.setId(admin.getId());

        dto.setNombre(admin.getNombre());

        dto.setEmail(admin.getEmail());

        return dto;
    }

}