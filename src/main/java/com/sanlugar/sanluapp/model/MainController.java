package com.sanlugar.sanluapp.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index.html"; // Devuelve el nombre de la vista HTML
    }

}
