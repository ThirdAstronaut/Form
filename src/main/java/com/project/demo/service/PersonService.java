package com.project.demo.service;

import com.project.demo.command.PersonForm;
import com.project.demo.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> listAll();

    Person getById(final String id);

    Person save(final Person person);

    Person update(final Person person);

    void delete(final String id);

    Person savePersonForm(final PersonForm personForm);

    Person updatePersonForm(final PersonForm personForm);
}
