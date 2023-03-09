package com.api.petshop.services;

import com.api.petshop.models.PetShopModel;
import com.api.petshop.repo.PetShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetShopService {

    @Autowired
    private PetShopRepo petShopRepo;

    //Adiciona novo pet
    public PetShopModel save(PetShopModel petShopModel) {
        return petShopRepo.save(petShopModel);
    }

    //Mostra todos os pets cadastrados
    public List<PetShopModel> findAll() {
        return petShopRepo.findAll();
    }

    //Pesquisa pet por id
    public Optional<PetShopModel> findById(Long id) {
        return petShopRepo.findById(id);
    }

    //Deleta pet por id
    public void delete(PetShopModel petShopModel) {
        petShopRepo.delete(petShopModel);
    }

}
