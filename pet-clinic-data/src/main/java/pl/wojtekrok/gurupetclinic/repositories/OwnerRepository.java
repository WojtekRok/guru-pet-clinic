package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
