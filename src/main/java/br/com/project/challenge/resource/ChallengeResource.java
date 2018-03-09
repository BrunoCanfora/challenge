package br.com.project.challenge.resource;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping("/salvarMedico")
	public ResponseEntity<Medico> criar(@RequestBody Medico medico, HttpServletResponse response) {
		Medico medicoSalva = medicoRepository.save(medico);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(medicoSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(medicoSalva);
	}
	
	@PostMapping("/salvarConsultorio")
	public ResponseEntity<Consultorio> criar(@RequestBody Consultorio consultorio, HttpServletResponse response) {
		Consultorio consultorioSalva = consultorioRepository.save(consultorio);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(consultorioSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(consultorioSalva);
	}
	
	@PostMapping("/salvarConsulta")
	public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta, HttpServletResponse response) {
		List<Consulta> listarConsultas = consultaRepository.findAll();		
		Date dataListagem = new Date();
		Date dataNow = new Date();		
		for (Consulta con : listarConsultas) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
			dataNow.setTime(consulta.getDataHora().getTime());
			DateTime time = formatter.parseDateTime(dateFormat.format(dataNow));
			time = time.plusMinutes(15);
			dataListagem.setTime(con.getDataHora().getTime());
			System.out.println("Data e Hora + 15m: "+time);
			System.out.println("Data e Hora Banco: "+formatter.parseDateTime((dateFormat.format(dataListagem))));
			if(time.isBefore((formatter.parseDateTime((dateFormat.format(dataListagem)))))
					|| formatter.parseDateTime((dateFormat.format(dataListagem))).equals((time))) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}			
		}
		
		Consulta consultaSalva = consultaRepository.save(consulta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(consultaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(consultaSalva);
	}
}
