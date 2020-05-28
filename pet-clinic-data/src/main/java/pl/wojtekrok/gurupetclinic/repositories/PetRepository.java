package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
