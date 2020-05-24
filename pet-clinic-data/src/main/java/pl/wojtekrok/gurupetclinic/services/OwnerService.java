package pl.wojtekrok.gurupetclinic.services;

import pl.wojtekrok.gurupetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
