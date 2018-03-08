package br.com.project.challenge.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.challenge.model.Consultorio;
import br.com.project.challenge.model.Medico;
import br.com.project.challenge.repository.ConsultorioRepository;
import br.com.project.challenge.repository.MedicoRepository;

@RestController
@RequestMapping("/challenge")
public class ChallengeResource {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultorioRepository consultorioRepository;
	
	
	@GetMapping("/medico")
	public List<Medico> listarMedico(){
		return medicoRepository.findAll();
	}
	
	@GetMapping("/consultorio")
	public List<Consultorio> listarConsultorio(){
		return consultorioRepository.findAll();
	}
	
	@PostMapping("/salvarMedico")
	public ResponseEntity<Medico> criar(@RequestBody Medico medico, HttpServletResponse response) {
		Medico medicoSalva = medicoRepository.save(medico);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(medicoSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(medicoSalva);
	}
}
