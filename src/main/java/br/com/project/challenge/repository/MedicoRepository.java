package br.com.project.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.challenge.model.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
