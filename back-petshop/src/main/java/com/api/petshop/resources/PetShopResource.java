package com.api.petshop.resources;

import com.api.petshop.dtos.PetShopDto;
import com.api.petshop.models.PetShopModel;
import com.api.petshop.services.PetShopService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet-shop")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PetShopResource {

    @Autowired
    private PetShopService petShopService;

    //Adiciona novo pet
    @PostMapping("/add")
    public ResponseEntity<Object> savePet(@RequestBody @Valid PetShopDto petShopDto){
        PetShopModel petShopModel = new PetShopModel();
        BeanUtils.copyProperties(petShopDto, petShopModel);
        petShopModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(petShopService.save(petShopModel));
    }

    //Mostra todos os pets cadastrados
    @GetMapping("/all")
    public ResponseEntity<List<PetShopModel>> getAllPets(){
        List<PetShopModel> petShopModel = petShopService.findAll();
        return new ResponseEntity<>(petShopModel, HttpStatus.OK);
    }

    //Pesquisa pet por id
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getPetById(@PathVariable(value="id") Long id){
        Optional<PetShopModel> petShopModelOptional = petShopService.findById(id);
            return new ResponseEntity<>(petShopModelOptional, HttpStatus.OK);
    }

    //Atualiza pet por id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePetById (@PathVariable(value="id") Long id, @RequestBody PetShopDto petShopDto){
        Optional<PetShopModel> petShopModelOptional = petShopService.findById(id);

        if(petShopModelOptional.isPresent()){
            PetShopModel petShopModel = petShopModelOptional.get();
            BeanUtils.copyProperties(petShopDto, petShopModel);
            petShopModel.setId(petShopModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(petShopService.save(petShopModel));

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado!");        }
    }



    //Deleta pet por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePetById(@PathVariable(value="id") Long id){

        Optional<PetShopModel> petShopModelOptional = petShopService.findById(id);

        if(!petShopModelOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            petShopService.delete(petShopModelOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }




}
