package com.cramstack.contact_management.Controllers;

import com.cramstack.contact_management.Models.Contact;
import com.cramstack.contact_management.Payloads.ContactPayload;
import com.cramstack.contact_management.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact/list" , method = RequestMethod.GET)
    public ModelAndView contactList(){

        ModelAndView modelAndView = new ModelAndView();

        List<Contact> list = contactService.getAllContact();
        modelAndView.addObject("allContact" , list);
        modelAndView.setViewName("contact/list");

        return modelAndView;
    }

    @RequestMapping(value = "/contact/list/{id}" , method = RequestMethod.GET)
    public ModelAndView showContact(@PathVariable("id") Integer id){

        ModelAndView modelAndView = new ModelAndView();

        Optional<Contact> contact = contactService.findContact(id);
        if(contact.isPresent()) {
            modelAndView.addObject("contact" , contact);
            modelAndView.setViewName("contact/show");
        }
        else {
            modelAndView.setViewName("redirect:/contact/list");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/contact/edit/{id}" , method = RequestMethod.GET)
    public ModelAndView editContact(@PathVariable("id") Integer id){

        ModelAndView modelAndView = new ModelAndView();

        Optional<Contact> contact = contactService.findContact(id);
        ContactPayload contactPayload = new ContactPayload();
        if(contact.isPresent()) {
            Contact one = contact.get();
            modelAndView.addObject("contactPayload" , contactPayload);
            modelAndView.addObject("contact" , one);
            modelAndView.setViewName("contact/edit");
        }
        else {
            modelAndView.setViewName("redirect:/contact/list");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/contact/edit/{id}" , method = RequestMethod.POST)
    public ModelAndView editContact(@PathVariable("id") Integer id , @Valid ContactPayload contactPayload, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/contact/edit/{id}");
        }
        else {
            contactService.saveContact(id,contactPayload);
            modelAndView.setViewName("redirect:/contact/list");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/contact/delete/{id}" , method = RequestMethod.GET)
    public ModelAndView deleteContact(@PathVariable("id") Integer id){

        ModelAndView modelAndView = new ModelAndView();

        Optional<Contact> contact = contactService.findContact(id);
        if(contact.isPresent()){
            contactService.deleteContact(contact.get());
            modelAndView.setViewName("redirect:/contact/list");
        }
        else {
            modelAndView.setViewName("redirect:/contact/list/{id}");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/contact/create" , method = RequestMethod.GET)
    public ModelAndView createContact(){

        ModelAndView modelAndView = new ModelAndView();

        ContactPayload contactPayload = new ContactPayload();
        modelAndView.addObject("contactPayload" , contactPayload);
        modelAndView.setViewName("contact/create");

        return modelAndView;
    }

    @RequestMapping(value = "/contact/create" , method = RequestMethod.POST)
    public ModelAndView saveContact(@Valid ContactPayload contactPayload, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("contact/create");
        }
        else {
            contactService.saveContact(contactPayload);
            modelAndView.setViewName("redirect:/contact/list");
        }

        return modelAndView;
    }
}
