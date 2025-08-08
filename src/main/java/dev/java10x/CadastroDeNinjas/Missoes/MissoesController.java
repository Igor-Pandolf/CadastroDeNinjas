package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // GET -- Mandar uma requisição para listar as missoes
    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missões listadas!";
    }

    // Post -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada!";
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missão alterada!";
    }

    // Delete -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missão deletada!";
    }
}
