package com.example.webFlux.Repository;

import com.example.webFlux.model.Register;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RegisterRepositoy extends ReactiveCrudRepository<Register, String> {
    @Query("INSERT INTO register (email, name, password) VALUES (:email, :name, :password)")
    Mono<Void> insertRegister(String email, String name, String password);
}
