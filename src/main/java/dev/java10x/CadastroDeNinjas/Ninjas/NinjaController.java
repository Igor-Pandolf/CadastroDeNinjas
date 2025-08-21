package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    public NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // GetMapping vai pegar informações
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){ // @RequestBody vai pegar o Json que o usuário mandou e serializar para o banco de dados
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());

    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // localhost:8080/ninjas/listar/id
    // Mostrar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){ // @PathVariable Significa que é algo que o usuário está passando

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " não encontrado!");
        }


    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){

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
