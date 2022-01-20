package one.digitalinovation.personalapi.service;

import one.digitalinovation.personalapi.dto.request.PersonDTO;
import one.digitalinovation.personalapi.dto.response.MessageResponseDTO;
import one.digitalinovation.personalapi.entity.Person;
import one.digitalinovation.personalapi.exception.PersonNotFoundException;
import one.digitalinovation.personalapi.mapper.PersonMapper;
import one.digitalinovation.personalapi.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalService {

    private PersonalRepository personalRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personTosSve = personMapper.toModel(personDTO);

        Person savedPerson = personalRepository.save(personTosSve);

        return MessageResponseDTO.builder().message("Create person with ID" + savedPerson.getId()).build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personalRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

        Person person = personalRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDTO(person);

    }

    public void deleteById(Long id) throws PersonNotFoundException {
        personalRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        personalRepository.deleteById(id);
    }
}
