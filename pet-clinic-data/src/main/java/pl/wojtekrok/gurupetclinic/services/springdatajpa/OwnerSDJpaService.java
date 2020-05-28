package pl.wojtekrok.gurupetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wojtekrok.gurupetclinic.model.Owner;
import pl.wojtekrok.gurupetclinic.repositories.OwnerRepository;
import pl.wojtekrok.gurupetclinic.repositories.PetRepository;
import pl.wojtekrok.gurupetclinic.repositories.PetTypeRepository;
import pl.wojtekrok.gurupetclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owner -> owners.add(owner));
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        return optionalOwner.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
