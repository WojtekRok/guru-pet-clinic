package pl.wojtekrok.gurupetclinic.services.map;

import org.springframework.stereotype.Service;
import pl.wojtekrok.gurupetclinic.model.Speciality;
import pl.wojtekrok.gurupetclinic.model.Vet;
import pl.wojtekrok.gurupetclinic.services.SpecialitesService;
import pl.wojtekrok.gurupetclinic.services.VetService;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialitesService specialitesService;

    public VetServiceMap(SpecialitesService specialitesService) {
        this.specialitesService = specialitesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {

        if(vet.getSpecialities().size() > 0) {
            vet.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpeciality = specialitesService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }

        return super.save(vet);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
