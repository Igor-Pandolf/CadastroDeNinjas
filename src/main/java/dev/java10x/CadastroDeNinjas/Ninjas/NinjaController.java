package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    public final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // GetMapping vai pegar informações
    @GetMapping("/boasvindas")
    @Operation(summary="Mensagem de boas vindas", description="Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja!")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){ // @RequestBody vai pegar o Json que o usuário mandou e serializar para o banco de dados
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED) // Status 201 Created
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId()); // Body: mensagem de sucesso

    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota lista todos os ninjas no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel listar os ninjas!")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas); // Status 200 OK, Body: lista de ninjas
    }

    // localhost:8080/ninjas/listar/id
    // Mostrar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por id", description = "Rota lista um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado!")
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){ // @PathVariable Significa que é algo que o usuário está passando

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else{
            // O cliente pediu um ninja que não existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Status 404 Not Found
                    .body("Ninja com o id: " + id + " não encontrado!"); // Body: mensagem de erro
        }

    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza o ninja por id", description = "Rota atualiza um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado!")
    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuário manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado
    ){

        if (ninjaService.listarNinjasPorId(id) != null) {
            NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com ID " + id + " não foi encontrado!");
        }

    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por id", description = "Rota deleta um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado!")
    })
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id){

        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorID(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com ID " + id + " não foi encontrado!");
        }
    }
}
