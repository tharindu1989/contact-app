package com.vrealcompany.demo.service.client;

import com.vrealcompany.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ContactClient {

    @Autowired
    WebClient webClient;

    public List<Contact> getAllContacts() {
        return webClient.get()
                .uri("/users")  // Replace with the appropriate URI for fetching users
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> handleErrorResponse(clientResponse.statusCode()))
                .bodyToFlux(Contact.class)
                .collectList()
                .block();
//              .onErrorResume(Exception.class, e -> new ArrayList<User>()).block(); // Return an empty collection on error
    }

    /**
     * TODO:: handle log and return an empty list
     */
    private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {
        return Mono.error(new Exception("Failed to fetch employee. Status code: " + statusCode));
    }
}
