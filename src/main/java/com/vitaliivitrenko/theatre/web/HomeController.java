/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitaliivitrenko.theatre.web;

import com.vitaliivitrenko.theatre.model.data.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Vitalii_Vitrenko
 */
@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    private final UserRepository userRepository;

    @Inject
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = GET)
    public String showHome(Model model) {
        userRepository.find();
        return "home";
    }


}
