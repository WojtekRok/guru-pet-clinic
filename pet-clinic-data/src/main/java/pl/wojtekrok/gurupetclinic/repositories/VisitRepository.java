package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
