package com.project.demo.converter;

import com.project.demo.command.PersonForm;
import com.project.demo.model.Person;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonFormToPerson implements Converter<PersonForm, Person> {

    @Override
    public Person convert(PersonForm personForm) {
        Person person = new Person();
        if (personForm.getId() != null && personForm.getId().isEmpty()) {
            person.setId(new ObjectId(personForm.getId()));
        }
        person.setName(personForm.getName());
        person.setSalary(personForm.getSalary());
        person.setSurname(personForm.getSurname());
        return person;
    }
}
