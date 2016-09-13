/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitaliivitrenko.theatre.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Vitalii_Vitrenko
 */
@Configuration
@ComponentScan(basePackages = "com.vitaliivitrenko.theatre.model")
@Import({RepositoryConfig.class})
public class RootConfig {
    
}
