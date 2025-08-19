package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os meus ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    // Listar por ID
    public NinjaModel listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
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
    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado){
        if (ninjaRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }
        return null;
    }
}
