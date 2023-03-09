package com.api.petshop.dtos;

import com.api.petshop.enums.EnumPetService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetShopDto {

    @NotBlank
    private String petName;
    @NotBlank
    private String species;
    @NotBlank
    private String breed;
    @NotNull
    private char gender;
    @NotBlank
    private String size;
    @NotBlank
    private String fur;
    @NotBlank
    private String tutorName;
    @NotNull
    private String tutorPhone;
    @NotNull
    private EnumPetService petService;


}
