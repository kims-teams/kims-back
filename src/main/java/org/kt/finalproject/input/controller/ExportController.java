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

    //================ DI 및 필드 ========================
    private final ExportService exportService;

    //================ 엑셀 내보내기 API =================
    @PostMapping("/input-data")
    public void exportInputData(@RequestParam("data-type") String tableName,    // 내보낼 테이블명 파라미터
                                @RequestParam("data-id") Integer scenarioId,    // 시나리오 ID 파라미터
                                HttpServletResponse response) throws IOException {

        // Service에서 엑셀 파일 생성 및 반환 처리 과정
        exportService.exportTableDataToExcel(tableName, scenarioId, response);
    }
}
