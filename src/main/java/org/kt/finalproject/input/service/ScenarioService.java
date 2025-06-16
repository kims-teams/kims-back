package org.kt.finalproject.input.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final BomRepository bomRepository;
    private final ManufacturingProcessRepository manufacturingProcessRepository;
    private final MaterialMasterRepository materialMasterRepository;
    private final OperationRepository operationRepository;
    private final OperationSequenceRepository operationSequenceRepository;
    private final PlantMasterRepository plantMasterRepository;
    private final PriorityRepository priorityRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final ToolMapRepository toolMapRepository;
    private final ToolMasterRepository toolMasterRepository;
    private final WorkcenterMapRepository workcenterMapRepository;
    private final WorkcenterMasterRepository workcenterMasterRepository;

    public ResponseEntity<?> saveScenario(String scenarioName){
        if(scenarioRepository.findByName(scenarioName) != null){
            return ResponseEntity.status(409).body("이미 존재하는 시나리오의 이름입니다");
        }

        Scenario scenario = Scenario.builder().name(scenarioName).build();
        scenarioRepository.save(scenario);

        return ResponseEntity.status(200).body(null);
    }

    public ResponseEntity<?> getScenario(String scenarioName){
        if(scenarioRepository.findByName(scenarioName) == null){
            return ResponseEntity.status(404).body("존재하지 않는 시나리오 입니다");
        }

        Scenario scenario = scenarioRepository.findByName(scenarioName);

        List<Bom> bomList = bomRepository.findByScenario(scenario);
        List<ManufacturingProcess> manufacturingProcessList = manufacturingProcessRepository.findByScenario(scenario);
        List<MaterialMaster> materialMasterList = materialMasterRepository.findByScenario(scenario);
        List<Operation> operationList = operationRepository.findByScenario(scenario);
        List<OperationSequence> operationSequenceList = operationSequenceRepository.findByScenario(scenario);
        List<PlantMaster> plantMasterList = plantMasterRepository.findByScenario(scenario);
        List<Priority> priorityList = priorityRepository.findByScenario(scenario);
        List<SalesOrder> salesOrderList = salesOrderRepository.findByScenario(scenario);
        List<ToolMap> toolMapList = toolMapRepository.findByScenario(scenario);
        List<ToolMaster> toolMasterList = toolMasterRepository.findByScenario(scenario);
        List<WorkcenterMap> workcenterMapList = workcenterMapRepository.findByScenario(scenario);
        List<WorkcenterMaster> workcenterMasterList = workcenterMasterRepository.findByScenario(scenario);

        Map<String, List<?>> scenarioMap = new HashMap<>();
        scenarioMap.put("bom", bomList);
        scenarioMap.put("manufacturingProcess", manufacturingProcessList);
        scenarioMap.put("materialMaster", materialMasterList);
        scenarioMap.put("operation", operationList);
        scenarioMap.put("operationSequence", operationSequenceList);
        scenarioMap.put("plantMaster", plantMasterList);
        scenarioMap.put("priority", priorityList);
        scenarioMap.put("salesOrder", salesOrderList);
        scenarioMap.put("toolMap", toolMapList);
        scenarioMap.put("toolMaster", toolMasterList);
        scenarioMap.put("workcenterMap", workcenterMapList);
        scenarioMap.put("workcenterMaster", workcenterMasterList);

        return ResponseEntity.status(200).body(scenarioMap);

    }
}
