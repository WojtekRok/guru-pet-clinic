package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
