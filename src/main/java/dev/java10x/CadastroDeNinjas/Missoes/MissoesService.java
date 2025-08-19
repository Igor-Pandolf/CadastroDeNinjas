package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar todas as missões
    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    // Listar missões por Id
    public MissoesModel listarPorId(Long id){
        Optional<MissoesModel> missaoId = missoesRepository.findById(id);
        return missaoId.orElse(null);
    }

    // Criar missão
    public MissoesDTO criarMissao(MissoesDTO missao){
        MissoesModel missoesModel = missoesMapper.map(missao); // mapeia o DTO no model
        missoesModel = missoesRepository.save(missoesModel); // O model é salvo
        return missoesMapper.map(missoesModel); // Mapeia o model de volta para o DTO
    }

    // Deletar missão
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }

    // Atualizar missão
    public MissoesModel atualizarMissao(Long id, MissoesModel missaoAtualizada){
        if (missoesRepository.existsById(id)){
            missaoAtualizada.setId(id);
            return missoesRepository.save(missaoAtualizada);
        }
        return null;
    }
}
