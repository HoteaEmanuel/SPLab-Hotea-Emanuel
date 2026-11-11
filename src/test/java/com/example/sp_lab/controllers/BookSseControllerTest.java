package com.example.sp_lab.controllers;

import com.example.sp_lab.observer.BooksSubject;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

class BookSseControllerTest {

    private final BooksSubject subject = new BooksSubject();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new BookSseController(subject)).build();

    // Without bytes on the wire the browser keeps the EventSource request "pending" and never fires onopen
    @Test
    void writesSomethingAsSoonAsClientConnects() throws Exception {
        String body = mockMvc.perform(get("/books-sse"))
                .andExpect(request().asyncStarted())
                .andReturn().getResponse().getContentAsString();

        assertThat(body).isNotEmpty();
    }
}
