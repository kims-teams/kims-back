package org.kt.finalproject.input.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.Getter;
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

    //================ 필드(서비스 주입) =================
    private final InputService inputService;


    //================ 입력데이터 저장 API =================
    @PostMapping("/{dataType}")
    public ResponseEntity<?> saveInputData(@RequestBody String item, @PathVariable("dataType") String dataType) throws JsonProcessingException {

        System.out.println(dataType);

        int input = inputService.saveInputData(item, dataType); // 서비스로 저장 위임 ( 결과 status 코드로 반환 )

        return ResponseEntity.status(input).build();
    }

    @GetMapping("/{dataType}/{scenarioId}")
    public ResponseEntity<?> getInputDataByScenarioId(@PathVariable("dataType") String dataType, @PathVariable("scenarioId") int scenarioId) {
        System.out.println(dataType);
        System.out.println(scenarioId);

        List<?> dataList = inputService.getInputDataByScenario(scenarioId, dataType);

        return ResponseEntity.ok(dataList);
    }
}
