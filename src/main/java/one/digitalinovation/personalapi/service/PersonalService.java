package one.digitalinovation.personalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinovation.personalapi.dto.MessageResponseDTO;
import one.digitalinovation.personalapi.entity.Person;
import one.digitalinovation.personalapi.repository.PersonalRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonalService {

	private PersonalRepository personalRepository;

	@Autowired
	public PersonalService(PersonalRepository personalRepository) {
		this.personalRepository = personalRepository;
	}


	public MessageResponseDTO createPerson( Person person){
		Person savedPerson = personalRepository.save(person);
		return MessageResponseDTO.builder().message("Create person with ID"+savedPerson.getId()).build();
	}
	
	
	
}
