package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisição para listar as missoes
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    // Listar missoes por id
    @GetMapping("/listar/{id}")
    public MissoesModel listarPorId(@PathVariable Long id){ // @PathVariable Significa que é algo que o usuário está passando
        return missoesService.listarPorId(id);
    }

    // Post -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){ // @RequestBody vai pegar o Json que o usuário mandou e serializar para o banco de dados
        return missoesService.criarMissao(missao);
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
