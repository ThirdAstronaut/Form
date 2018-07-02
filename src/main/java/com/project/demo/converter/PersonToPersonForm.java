package com.project.demo.converter;

import com.project.demo.command.PersonForm;
import com.project.demo.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonForm implements Converter<Person, PersonForm> {

    @Override
    public PersonForm convert(Person person) {
        PersonForm personForm = new PersonForm();
        personForm.setId(person.getId().toHexString());
        personForm.setName(person.getName());
        personForm.setSalary(person.getSalary());
        personForm.setSurname(person.getSurname());
        return personForm;
    }
}
