package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
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
    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }

    // Deletar missão
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }
}
