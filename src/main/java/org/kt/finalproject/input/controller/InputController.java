package org.kt.finalproject.input.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.service.InputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/input")
@CrossOrigin
@RequiredArgsConstructor
public class InputController {

    private final InputService inputService;

    @PostMapping("/{dataType}")
    public ResponseEntity<?> saveInputData(@RequestBody String item, @PathVariable("dataType") String dataType) throws JsonProcessingException {

        System.out.println(dataType);

        int input = inputService.saveInputData(item, dataType);

        return ResponseEntity.status(input).build();
    }
}
