package org.kt.finalproject.input.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/input")
@CrossOrigin
@RequiredArgsConstructor
public class InputController {

    @PostMapping
    public ResponseEntity<?> saveInputData(@RequestBody List<Object> item, @RequestParam("data-type") String dataType){



        return ResponseEntity.ok().build();
    }
}
