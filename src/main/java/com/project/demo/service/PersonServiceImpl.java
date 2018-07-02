package com.project.demo.service;

import com.project.demo.command.PersonForm;
import com.project.demo.converter.PersonFormToPerson;
import com.project.demo.model.Person;
import com.project.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PersonFormToPerson personFormToPerson;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonFormToPerson personFormToPerson) {
        this.personRepository = personRepository;
        this.personFormToPerson = personFormToPerson;
    }

    @Override
    public List<Person> listAll() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    @Override
    public Person getById(final String id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(final Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(final Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(final String id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person savePersonForm(final PersonForm personForm) {
        return save(personFormToPerson.convert(personForm));
    }

    @Override
    public Person updatePersonForm(final PersonForm personForm) {
        return update(personFormToPerson.convert(personForm));
    }
}
