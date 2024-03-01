package com.example.condominiumApi.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Operation(hidden = true)
    @GetMapping
    public RedirectView redirectToSwagger(RedirectAttributes attributes) {
        return new RedirectView("/swagger-ui/index.html");
    }

    @Operation(hidden = true)
    @GetMapping("/health")
    public ResponseEntity<Boolean> health() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
