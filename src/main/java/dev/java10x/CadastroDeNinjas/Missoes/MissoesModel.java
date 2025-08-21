package dev.java10x.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data // Cria os Getters e Setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private RankMissao rank;

    // @OneToMany - Uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes")
    @JsonIgnore // Vai ignorar a serialização, impedindo o loop infinito
    private List<NinjaModel> ninja;
}
