package br.com.project.challenge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.challenge.repository.ConsultorioRepository;
import br.com.project.challenge.repository.MedicoRepository;

@RestController
@RequestMapping("/challenge")
public class ChallengeResource {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultorioRepository consultorioRepository;
}
