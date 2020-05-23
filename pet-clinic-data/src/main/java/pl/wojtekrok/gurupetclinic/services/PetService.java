package pl.wojtekrok.gurupetclinic.services;

import pl.wojtekrok.gurupetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save (Pet pet);

    Set<Pet> findAll();
}
