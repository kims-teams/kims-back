package org.kt.finalproject.input.service;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExportService {

    private final JdbcTemplate jdbcTemplate;

    public void exportTableDataToExcel(String tableName, Integer scenarioId, HttpServletResponse response) throws IOException {
        // 엑셀에서 제외할 컬럼 세트 선언
        Set<String> excludeColumns = Set.of("scenario_id", "target_id");

        // SQL 쿼리 생성 : 시나리오 ID가 필요한 테이블만 where 절 추가, 아니면 생략
        String sql = getSqlByTable(tableName, scenarioId);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(tableName);

        // 헤더 생성
        if (!rows.isEmpty()) {
            Row headerRow = sheet.createRow(0);
            Map<String, Object> firstRow = rows.get(0);
            int colIdx = 0;
            for (String key : firstRow.keySet()) {
                if (excludeColumns.contains(key)) continue;
                Cell cell = headerRow.createCell(colIdx++);
                cell.setCellValue(key);
            }
            // 데이터 행 생성
            int rowIdx = 1;
            for (Map<String, Object> row : rows) {
                Row dataRow = sheet.createRow(rowIdx++);
                int colIdx2 = 0;
                for (String key : firstRow.keySet()) {
                    if (excludeColumns.contains(key)) continue;
                    Object value = row.get(key);
                    Cell cell = dataRow.createCell(colIdx2++);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        // 응답 헤더
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + tableName + ".xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private String getSqlByTable(String tableName, Integer scenarioId) {
        // 필요에 따라 scenarioId 사용 여부 묻기
        switch (tableName) {
            case "bom":
            case "priority":
            case "tool_map":
            case "tool_master":
            case "workcenter_map":
                return "select * from " + tableName + " where scenario_id = " + scenarioId;
            default:
                return "select * from " + tableName;
        }
    }
}
