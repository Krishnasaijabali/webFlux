package com.example.webFlux.service;

import com.example.webFlux.Repository.RegisterRepositoy;
import com.example.webFlux.model.Login;
import com.example.webFlux.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class RegisterService {
    @Autowired
    private RegisterRepositoy registerRepositoy;

    public Mono<String> registering(Register register) {
        return registerRepositoy.findById(register.getEmail())  // Check if the email already exists
                .flatMap(existingRegister -> Mono.just("Email already exists!")) // Return a proper error signal
                .switchIfEmpty(registerRepositoy.insertRegister(register.getEmail(),register.getName(),register.getPassword())  // If not found, insert the new register entry
                        .then(Mono.just("Registration successful!")))
                .onErrorResume(IllegalStateException.class, e -> Mono.just(e.getMessage()));  // Catch the error and return the message as a string
    }



    public Mono<String> loginuser(Login login) {
            // Find user asynchronously by email
            return registerRepositoy.findById(login.email())
                    .flatMap(foundRegister -> {
                        // Check credentials
                        if (login.password().equals(foundRegister.getPassword())) {
                            return Mono.just("Login successful!");
                        } else {
                            return Mono.just("Invalid credentials. Please try again.");
                        }
                    })
                    .onErrorResume(e -> Mono.just("Invalid credentials. Please try again."));
        }
    }


