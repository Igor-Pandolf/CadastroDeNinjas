package dev.java10x.CadastroDeNinjas.Missoes.DTOs;

import dev.java10x.CadastroDeNinjas.Missoes.Enums.RankMissao;
import dev.java10x.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {

    private Long id;
    private String nome;
    private RankMissao rank;
    private List<NinjaModel> ninja;
}
