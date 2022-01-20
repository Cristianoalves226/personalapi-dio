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

        return createMessageResponse("Create person with ID", savedPerson.getId());
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

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personTosSve = personMapper.toModel(personDTO);

        Person updatePerson = personalRepository.save(personTosSve);

        return createMessageResponse("Update with ID ", updatePerson.getId());
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personalRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        personalRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        personalRepository.deleteById(id);
    }


    private MessageResponseDTO createMessageResponse(String s, Long id) {
        return MessageResponseDTO.builder().message("Update person with ID" + id).build();
    }
}
