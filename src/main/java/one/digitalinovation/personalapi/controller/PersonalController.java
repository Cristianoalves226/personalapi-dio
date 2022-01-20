package one.digitalinovation.personalapi.controller;

import one.digitalinovation.personalapi.dto.request.PersonDTO;
import one.digitalinovation.personalapi.dto.response.MessageResponseDTO;
import one.digitalinovation.personalapi.exception.PersonNotFoundException;
import one.digitalinovation.personalapi.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {

        return personalService.createPerson(personDTO);

    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personalService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personalService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable  Long id) throws PersonNotFoundException {

        personalService.deleteById(id);
    }
}
