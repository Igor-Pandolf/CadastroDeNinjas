package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisição para listar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // Listar missoes por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){ // @PathVariable Significa que é algo que o usuário está passando

        MissoesDTO missao = missoesService.listarPorId(id);

        if (missao != null){
            return ResponseEntity.ok(missao);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " não encontrado!");
        }

    }

    // Post -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao){// @RequestBody vai pegar o Json que o usuário mandou e serializar para o banco de dados
        MissoesDTO missaoCriada = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criado com sucesso: " + missaoCriada.getNome() + " (ID): " + missaoCriada.getId());
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada){

        if (missoesService.listarPorId(id) != null) {
            missoesService.atualizarMissao(id, missaoAtualizada);
            return ResponseEntity.ok("Missão " + missaoAtualizada.getNome() + " com ID " + id + " atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão com ID " + id + " não foi encontrada!");
        }

    }

    // Delete -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){

        if (missoesService.listarPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missão com ID " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão com ID " + id + " não foi encontrada!");
        }

    }
}
