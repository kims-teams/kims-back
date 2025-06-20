package org.kt.finalproject.domain.input.service;


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

    //================ 테이블 데이터 엑셀로 내보내기 =================
    public void exportTableDataToExcel(String tableName, Integer scenarioId, HttpServletResponse response) throws IOException { // 테이블 데이터를 엑셀 파일로 내보내는 메서드
        // 엑셀에서 제외할 컬럼 세트 선언
        Set<String> excludeColumns = Set.of("scenario_id", "target_id");

        // SQL 쿼리 생성 : 시나리오 ID가 필요한 테이블만 where 절 추가, 아니면 생략
        String sql = getSqlByTable(tableName, scenarioId);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);    // 쿼리 결과 리스트로 조회

        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(tableName);  // 시트 이름은 테이블명으로

        //================ 엑셀 헤더 생성 =================
        if (!rows.isEmpty()) {  // 데이터가 있을 경우
            Row headerRow = sheet.createRow(0); // 첫 번째 행에 헤더 생성
            Map<String, Object> firstRow = rows.get(0); // DB에서 읽은 데이터 결과 중 첫 번째 행을 컬럼명-값 의 Map으로 꺼냄
            int colIdx = 0; // 셀 인덱스 초기화
            for (String key : firstRow.keySet()) {  // 첫 row의 컬럼명 순회
                if (excludeColumns.contains(key)) continue; // 제외 컬럼이면 skip
                Cell cell = headerRow.createCell(colIdx++); // 셀에 컬럼명 입력
                cell.setCellValue(key);
            }

            //================ 엑셀 데이터 행 생성 =================

            int rowIdx = 1; // 데이터는 1번 행부터
            for (Map<String, Object> row : rows) {  // 각 row별로
                Row dataRow = sheet.createRow(rowIdx++);    // 행 생성
                int colIdx2 = 0;    // 한 행씩 데이터를 넣을 때, 왼쪽부터 순서대로 값을 넣는 용도
                for (String key : firstRow.keySet()) {
                    if (excludeColumns.contains(key)) continue;
                    Object value = row.get(key);    // 현재 행(row)에서 해당 컬럼(key)의 값 가져옴
                    Cell cell = dataRow.createCell(colIdx2++);  // 새 셀 만들고 (colIdx2 위치), 인덱스 증가
                    cell.setCellValue(value != null ? value.toString() : "");   // 값이 null이면 빈칸, 아니면 문자열로 입력
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
