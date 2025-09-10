package dev.java10x.CadastroDeNinjas.Ninjas.DTOs;

import dev.java10x.CadastroDeNinjas.Missoes.Models.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String rank;
    private MissoesModel missoes;
    private String img_url;
}
