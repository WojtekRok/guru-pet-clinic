package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
