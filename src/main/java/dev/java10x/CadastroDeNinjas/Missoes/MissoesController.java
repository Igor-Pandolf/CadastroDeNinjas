package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lista todos as missões", description = "Rota lista todos as missões no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel listar as missões!")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // Listar missoes por id
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista as missões por id", description = "Rota lista uma missões pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada!")
    })
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
    @Operation(summary = "Cria uma nova missão", description = "Rota cria uma nova missão e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão!")
    })
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao){// @RequestBody vai pegar o Json que o usuário mandou e serializar para o banco de dados
        MissoesDTO missaoCriada = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criado com sucesso: " + missaoCriada.getNome() + " (ID): " + missaoCriada.getId());
    }

    // PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza a missão por id", description = "Rota atualiza uma missão pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Missão não encontrada!")
    })
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
    @Operation(summary = "Deleta a missão por id", description = "Rota deleta uma missão pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada!")
    })
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
