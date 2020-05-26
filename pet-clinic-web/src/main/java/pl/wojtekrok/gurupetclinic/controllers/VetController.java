package pl.wojtekrok.gurupetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojtekrok.gurupetclinic.services.VetService;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/vets", "/vets/vets.html", "/vets.html"})
    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/vets";
    }
}
