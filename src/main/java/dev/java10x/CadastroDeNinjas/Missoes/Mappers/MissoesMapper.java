package dev.java10x.CadastroDeNinjas.Missoes.Mappers;

import dev.java10x.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.Models.MissoesModel;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setRank(missoesDTO.getRank());
        missoesModel.setNinja(missoesDTO.getNinja());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel) {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setRank(missoesModel.getRank());
        missoesDTO.setNinja(missoesModel.getNinja());

        return missoesDTO;
    }
}
