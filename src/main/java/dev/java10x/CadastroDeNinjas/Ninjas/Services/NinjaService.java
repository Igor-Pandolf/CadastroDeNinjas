package dev.java10x.CadastroDeNinjas.Ninjas.Services;

import dev.java10x.CadastroDeNinjas.Ninjas.DTOs.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.Mappers.NinjaMapper;
import dev.java10x.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.Repositories.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();

        return ninjas.stream() // 1. Cria o "fluxo"
                .map(ninjaMapper::map) // 2. Transforma cada item
                .collect(Collectors.toList()); // 3. Coleta os resultados
    }

    // Listar NinjaDTO ID
    public NinjaDTO listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninja){
        // 1. Traduz de DTO para Entidade
        NinjaModel ninjaModel = ninjaMapper.map(ninja);

        // 2. Persiste a Entidade no banco de dados
        ninjaModel = ninjaRepository.save(ninjaModel);

        // 3. Traduz a Entidade (agora com ID) de volta para DTO
        return ninjaMapper.map(ninjaModel);
    }

    // Deletar um ninja -- VOID pq não retorna nada, apenas deleta
    public void deletarNinjaPorID(Long id){
        ninjaRepository.deleteById(id);
    }

    // Atualizar ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id); // 1. Busca
        if (ninjaExistente.isPresent()) { // 2. Valida
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO); // 3. Mapeia DTO -> Entidade
            ninjaAtualizado.setId(id); // 4. Garante o ID correto
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado); // 5. Salva
            return ninjaMapper.map(ninjaSalvo); // 6. Mapeia Entidade -> DTO
        }
        return null;
    }
}
