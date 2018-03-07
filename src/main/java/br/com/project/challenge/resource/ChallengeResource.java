package br.com.project.challenge.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.challenge.model.Consulta;
import br.com.project.challenge.model.Consultorio;
import br.com.project.challenge.model.Medico;
import br.com.project.challenge.repository.ConsultaRepository;
import br.com.project.challenge.repository.ConsultorioRepository;
import br.com.project.challenge.repository.MedicoRepository;

@RestController
@RequestMapping("/challenge")
public class ChallengeResource {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultorioRepository consultorioRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@GetMapping("/medico")
	public List<Medico> listarMedico(){
		return medicoRepository.findAll();
	}
	
	@GetMapping("/consultorio")
	public List<Consultorio> listarConsultorio(){
		return consultorioRepository.findAll();
	}
	
	@GetMapping("/consulta")
	public List<Consulta> listarConsulta(){
		return consultaRepository.findAll();
	}
}
