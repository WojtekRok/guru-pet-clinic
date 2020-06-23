package pl.wojtekrok.gurupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wojtekrok.gurupetclinic.model.Pet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    private PetMapService petMapService;

    private final Long petId = 1L;
    private final Long secondPetId = 2L;

    @BeforeEach
    void setUp() {

        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).build());
        petMapService.save(Pet.builder().id(secondPetId).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(2, petSet.size());
    }

    @Test
    void findByIdExistingId() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdNonExistingId() {
        Pet pet = petMapService.findById(8L);
        assertNull(pet);
    }

    @Test
    void saveExistingId() {

       Pet savedPet = petMapService.save(Pet.builder().id(secondPetId).build());

       assertEquals(secondPetId, savedPet.getId());

    }

    @Test
    void saveNoId() {

        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(3, petMapService.findAll().size());

    }

    @Test
    void deletePet() {

        petMapService.delete(petMapService.findById(petId));

        assertEquals(1, petMapService.findAll().size());

        petMapService.delete(petMapService.findById(secondPetId));

        assertEquals(0, petMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        petMapService.delete(null);

        assertEquals(2, petMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        petMapService.deleteById(petId);
        petMapService.deleteById(secondPetId);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        petMapService.deleteById(5L);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        petMapService.deleteById(null);

        assertEquals(2, petMapService.findAll().size());
    }
}