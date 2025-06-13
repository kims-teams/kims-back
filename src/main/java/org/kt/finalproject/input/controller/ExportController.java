package org.kt.finalproject.input.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.service.ExportService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/exports")
@RequiredArgsConstructor
public class ExportController {

    private final ExportService exportService;

    @PostMapping("/input-data")
    public void exportInputData(@RequestParam("data-type") String tableName,
                                @RequestParam("data-id") Integer scenarioId,
                                HttpServletResponse response) throws IOException {

        // Service에서 엑셀 파일 생성 및 반환 처리 과정
        exportService.exportTableDataToExcel(tableName, scenarioId, response);
    }
}
