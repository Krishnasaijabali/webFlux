package com.example.webFlux.controller;

import com.example.webFlux.model.Login;
import com.example.webFlux.model.Register;
import com.example.webFlux.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
        @Autowired
        private RegisterService registerService;
        @PostMapping("/Register")
        public Mono<String> registering(@RequestBody Register register){
            return registerService.registering(register);
        }
        @PostMapping("/Login")
        public Mono<String> loginuser(@RequestBody Login login){
            return registerService.loginuser(login);
        }
}




