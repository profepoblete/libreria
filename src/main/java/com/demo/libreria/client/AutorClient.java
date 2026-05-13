package com.demo.libreria.client;

import com.demo.libreria.dto.AutorDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AutorClient {

    private final WebClient webClient;


    public AutorClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public AutorDTO obtenerAutor(Long id)
    {
        return webClient.get().uri("/api/autores/{id}",id)
                .retrieve()
                .bodyToMono(AutorDTO.class)
                .block();
    }
}
