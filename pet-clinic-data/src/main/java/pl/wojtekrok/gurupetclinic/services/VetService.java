package pl.wojtekrok.gurupetclinic.services;

import pl.wojtekrok.gurupetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
