package pl.wojtekrok.gurupetclinic.services;

import pl.wojtekrok.gurupetclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
