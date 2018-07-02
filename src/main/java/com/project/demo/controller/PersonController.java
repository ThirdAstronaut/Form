package com.project.demo.controller;

import com.project.demo.command.PersonForm;
import com.project.demo.converter.PersonToPersonForm;
import com.project.demo.model.Person;
import com.project.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class PersonController {

    private final PersonService personService;
    private final PersonToPersonForm personToPersonForm;

    @Autowired
    public PersonController(PersonService personService, PersonToPersonForm personToPersonForm) {
        this.personService = personService;
        this.personToPersonForm = personToPersonForm;
    }

    @RequestMapping("/")
    public String redirectToList() {
        return "redirect:/person/list";
    }

    @RequestMapping({"/person/list", "/people"})
    public String listPeople(Model model) {
        model.addAttribute("people", personService.listAll());
        return "person/list";
    }

    @RequestMapping("person/show/{id}")
    public String getPerson(@PathVariable String id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person/show";
    }

    @RequestMapping("person/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Person person = personService.getById(id);
        PersonForm personForm = personToPersonForm.convert(person);
        model.addAttribute("personForm", personForm);
        return "person/personform";
    }

    @RequestMapping("person/new")
    public String newPerson(Model model) {
        model.addAttribute("personForm", new PersonForm());
        return "person/personform";
    }

    @PostMapping("/person")
    public String savePerson(@Valid final PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "person/personform";
        Person savedPerson = personService.savePersonForm(personForm);
        return "redirect:/person/show/" + savedPerson.getId();
    }

    @PostMapping("/person")
    public String updatePerson(@Valid final PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "person/personform";
        Person updatedPerson = personService.updatePersonForm(personForm);
        return "redirect:/person/show/" + updatedPerson.getId();
    }

    @RequestMapping("person/delete/{id}")
    public String delete(@PathVariable String id) {
        personService.delete(id);
        return "redirect:/person/list";
    }

}
