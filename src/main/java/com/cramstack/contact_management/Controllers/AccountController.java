package com.cramstack.contact_management.Controllers;


import com.cramstack.contact_management.Models.User;
import com.cramstack.contact_management.Payloads.UserPayload;
import com.cramstack.contact_management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public ModelAndView loginForm(){

        ModelAndView modelAndView = new ModelAndView();

        UserPayload userPayload = new UserPayload();
        modelAndView.addObject("loginPayload" , userPayload);
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public ModelAndView registrationForm(){

        ModelAndView modelAndView = new ModelAndView();

        UserPayload userPayload = new UserPayload();
        modelAndView.addObject("userPayload" , userPayload);
        modelAndView.setViewName("register");

        return modelAndView;
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ModelAndView createUser(@Valid UserPayload userPayload, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserByEmail(userPayload.getEmail());
        if(user != null) {
            bindingResult.rejectValue("email" , "errors.email" ,"This email all ready in use");
        }
        else if(!userPayload.getPassword().equals(userPayload.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword" , "errors.confirmPassword" ,"Passwords are not match");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        }
        else {
            userService.saveUser(userPayload);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public void logout(){
    }
}
