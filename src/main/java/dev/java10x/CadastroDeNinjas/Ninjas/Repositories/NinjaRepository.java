package dev.java10x.CadastroDeNinjas.Ninjas.Repositories;

import dev.java10x.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
}
