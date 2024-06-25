package com.example.portalsecurity.controller.restController;

import com.example.portalsecurity.controller.dto.PersonDto;
import com.example.portalsecurity.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<PersonDto> loadPersonList() {
        return personService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PersonDto> loadPersonById(@PathVariable("id") Long id) {
        PersonDto personDto = personService.findById(id);
        return ResponseEntity.ok(personDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('person:write')")
    public PersonDto insertPerson(@Valid @RequestBody PersonDto personDto) {
        return personService.insert(personDto);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public PersonDto updatePerson(@PathVariable Long id, @Valid @RequestBody PersonDto personDto) {
        return personService.update(id, personDto);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }
}