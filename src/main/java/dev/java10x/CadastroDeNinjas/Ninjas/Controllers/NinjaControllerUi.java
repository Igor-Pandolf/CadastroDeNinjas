package dev.java10x.CadastroDeNinjas.Ninjas.Controllers;

import dev.java10x.CadastroDeNinjas.Ninjas.DTOs.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.Services.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    public final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // Tem que retornar o nome da pagina que renderiza
    }

    // Deletar Ninja (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorID(@PathVariable Long id){
        ninjaService.deletarNinjaPorID(id);
        return "redirect:/ninjas/ui/listar";
    }
}
