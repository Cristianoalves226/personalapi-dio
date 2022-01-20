package one.digitalinovation.personalapi.controller;

import one.digitalinovation.personalapi.dto.MessageResponseDTO;
import one.digitalinovation.personalapi.entity.Person;
import one.digitalinovation.personalapi.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import one.digitalinovation.personalapi.repository.PersonalRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonalController {

    PersonalService personalService;

    @Autowired
    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {

        return personalService.createPerson(person);

    }

}
