package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    // GetMapping vai pegar informações
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado!";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public String mostrarTodosOsNinjas(){
        return "Mostrando todos os ninjas!";
    }

    // Mostrar ninja por ID (READ)
    @GetMapping("/listarID")
    public String mostrarTodosOsNinjasPorId(){
        return "Mostrando ninja por ID!";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar")
    public String alterarNinjaPorId(){
        return "Alterar ninja por ID!";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinjaPorID(){
        return "Ninja deletado por ID!";
    }
}
