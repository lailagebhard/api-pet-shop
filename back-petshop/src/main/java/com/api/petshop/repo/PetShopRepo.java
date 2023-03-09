package com.api.petshop.repo;

import com.api.petshop.models.PetShopModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetShopRepo extends JpaRepository<PetShopModel, Long>{


}
