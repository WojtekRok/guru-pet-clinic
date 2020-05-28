package pl.wojtekrok.gurupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wojtekrok.gurupetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
