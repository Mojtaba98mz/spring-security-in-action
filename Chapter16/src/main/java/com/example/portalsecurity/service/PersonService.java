package com.example.portalsecurity.service;

import com.example.portalsecurity.controller.dto.PersonDto;
import com.example.portalsecurity.controller.mapper.PersonMapper;
import com.example.portalsecurity.model.entity.Person;
import com.example.portalsecurity.model.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.portalsecurity.common.exception.ExceptionWrapper.NotFoundException;

@Slf4j
@Service
public class PersonService implements BusinessService<PersonDto, Long> {
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> personList = personRepository.findAll();
        return PersonMapper.INSTANCE.toDtoList(personList);
    }

    @Override
    public PersonDto findById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        return PersonMapper.INSTANCE.toDto(person);
    }

    @Override
    public PersonDto insert(PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.toEntity(personDto);
        Person newPerson = personRepository.save(person);
        //person = personRepository.save(person);
        return PersonMapper.INSTANCE.toDto(newPerson);
    }

    @Override
    public PersonDto update(Long id, PersonDto personDto) {
        Person loadedPerson = personRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        PersonMapper.INSTANCE.toEntity(personDto, loadedPerson);
        Person newPerson = personRepository.save(loadedPerson);
        //person = personRepository.save(person);
        return PersonMapper.INSTANCE.toDto(newPerson);
    }

    @Override
    public void delete(Long id) {
        Person loadedPerson = personRepository.findById(id).orElseThrow(() -> throwNotFoundException(id));
        personRepository.delete(loadedPerson);
    }

    private static NotFoundException throwNotFoundException1(Long id) {
        NotFoundException notFoundException =
                new NotFoundException("Person is not exist with id :" + id);
        LOGGER.error("Error in findById Method with {}", id, notFoundException);
        return notFoundException;
    }

    private static NotFoundException throwNotFoundException(Long id) {
        NotFoundException notFoundException =
                new NotFoundException("Person is not exist with id :" + id);
        log.error("Error in findById Method with {}", id, notFoundException);

        return notFoundException;
    }
}