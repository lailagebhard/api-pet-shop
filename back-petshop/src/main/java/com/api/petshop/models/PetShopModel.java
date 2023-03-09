package com.api.petshop.models;

import com.api.petshop.enums.EnumPetService;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TB_PET_SHOP")
public class PetShopModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 130)
    private String petName;
    @Column(nullable = false, length = 70)
    private String species;
    @Column(nullable = false, length = 70)
    private String breed;
    @Column(nullable = false)
    private char gender;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String fur;
    @Column(nullable = false)
    private String tutorName;
    @Column(nullable = false)
    private String tutorPhone;
    @Column(nullable = false)
    private EnumPetService petService;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

}
