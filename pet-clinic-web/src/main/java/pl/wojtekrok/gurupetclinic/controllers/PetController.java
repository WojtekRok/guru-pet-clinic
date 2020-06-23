package pl.wojtekrok.gurupetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.wojtekrok.gurupetclinic.model.Owner;
import pl.wojtekrok.gurupetclinic.model.Pet;
import pl.wojtekrok.gurupetclinic.model.PetType;
import pl.wojtekrok.gurupetclinic.services.OwnerService;
import pl.wojtekrok.gurupetclinic.services.PetService;
import pl.wojtekrok.gurupetclinic.services.PetTypeService;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {

        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult bindingResult, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }

        if (!StringUtils.hasLength(pet.getName())) {
            bindingResult.rejectValue("name", "required", "name of pet cannot be empty");
        }

        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

//    @PostMapping("/pets/{petId}/edit")
//    public String processUpdateForm(Pet pet, BindingResult bindingResult, Owner owner, Model model){
//        if(bindingResult.hasErrors()){
//            pet.setOwner(owner);
//            model.addAttribute("pet", pet);
//            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//        } else {
//            owner.getPets().add(pet);
//            petService.save(pet);
//            return "redirect:/owners" + owner.getId();
//        }
//
//    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@ModelAttribute Owner owner,
                                       @ModelAttribute Pet pet,
                                       @PathVariable String petId, BindingResult bindingResult,
                                       Model model) {
        // validate the input data
        if (StringUtils.hasLength(pet.getName())) {
            Pet foundPet = owner.getPet(pet.getName());
            if (foundPet != null && !foundPet.getId().equals(Long.valueOf(petId))) {
                bindingResult.rejectValue("name", "duplicate", "already exist");
            }
        }
        if (!StringUtils.hasLength(pet.getName())) {
            bindingResult.rejectValue("name", "null", "name of pet cannot be empty");
        }
        pet.setOwner(owner);
        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        Pet foundPet = petService.findById(Long.valueOf(petId));
        foundPet.setOwner(owner);
        foundPet.setPetType(pet.getPetType());
        foundPet.setName(pet.getName());
        foundPet.setBirthDate(pet.getBirthDate());
        petService.save(foundPet);
        return "redirect:/owners/" + owner.getId();
    }
}
