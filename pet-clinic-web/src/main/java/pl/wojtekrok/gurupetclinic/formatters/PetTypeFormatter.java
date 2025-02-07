package pl.wojtekrok.gurupetclinic.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.wojtekrok.gurupetclinic.model.PetType;
import pl.wojtekrok.gurupetclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for(PetType petType : findPetTypes){
            if(petType.getName().equals(s)){
                return petType;
            }
        }
        throw new ParseException("type not found: " + s, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
