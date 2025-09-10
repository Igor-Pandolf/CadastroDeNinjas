package dev.java10x.CadastroDeNinjas.Missoes.Services;

import dev.java10x.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.Mappers.MissoesMapper;
import dev.java10x.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.java10x.CadastroDeNinjas.Missoes.Repositories.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar todas as missões
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();

        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // Listar missões por Id
    public MissoesDTO listarPorId(Long id){
        Optional<MissoesModel> missaoId = missoesRepository.findById(id);

        return missaoId.map(missoesMapper::map).orElse(null);
    }

    // Criar missão
    public MissoesDTO criarMissao(MissoesDTO missao){
        // 1. Traduz de DTO para Entidade
        MissoesModel missoesModel = missoesMapper.map(missao); // mapeia o DTO no model

        // 2. Persiste a Entidade no banco de dados
        missoesModel = missoesRepository.save(missoesModel); // O model é salvo

        // 3. Traduz a Entidade (agora com ID) de volta para DTO
        return missoesMapper.map(missoesModel); // Mapeia o model de volta para o DTO
    }

    // Deletar missão
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }

    // Atualizar missão
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);

        if (missaoExistente.isPresent()) {
            MissoesModel missaoAtualizado = missoesMapper.map(missoesDTO);
            missaoAtualizado.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizado);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
