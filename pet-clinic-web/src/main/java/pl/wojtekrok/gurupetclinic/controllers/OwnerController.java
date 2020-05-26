package pl.wojtekrok.gurupetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojtekrok.gurupetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/owners", "/owners.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "/owners/owners";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }
}
