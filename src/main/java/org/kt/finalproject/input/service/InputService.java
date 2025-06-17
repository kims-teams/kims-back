package org.kt.finalproject.input.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.input.entity.*;
import org.kt.finalproject.input.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputService {

    private final ObjectMapper mapper;
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
    private final ScenarioRepository scenarioRepository;


    public int saveInputData(String item, String dataType) throws JsonProcessingException {

        JsonNode root = mapper.readTree(item);
        JsonNode dataArray = root.path("data").path("data");
        Optional<Scenario> OpScenario = scenarioRepository.findById(root.path("scenario_id").asInt());
        if(OpScenario.isEmpty()){
            System.out.println("❌ 해당 ID의 시나리오가 없습니다: " + root.path("scenario_id").asInt());
            return 400;
        }
        Scenario scenario = OpScenario.get();

        System.out.println("data = " + item);

        try {
            switch (dataType) {
                case "bom" -> {
                    List<Bom> bomList = mapper.readValue(dataArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (Bom bom : bomList){
                        bom.setScenario(scenario);
                    }

                    bomRepository.saveAll(bomList);
                }
                case "manufacturing-process" -> {
                    List<ManufacturingProcess> manufacturingProcessList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    manufacturingProcessRepository.saveAll(manufacturingProcessList);
                }
                case "material-master" -> {
                    List<MaterialMaster> materialMasterList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    materialMasterRepository.saveAll(materialMasterList);
                }
                case "operation" -> {
                    List<Operation> operationList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    operationRepository.saveAll(operationList);
                }
                case "operationSequence" -> {
                    List<OperationSequence> operationSequenceList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    operationSequenceRepository.saveAll(operationSequenceList);
                }
                case "plantMaster" -> {
                    List<PlantMaster> plantMasterList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    plantMasterRepository.saveAll(plantMasterList);
                }
                case "priority" -> {
                    List<Priority> priorityList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    priorityRepository.saveAll(priorityList);
                }
                case "salesOrder" -> {
                    List<SalesOrder> salesOrderList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    salesOrderRepository.saveAll(salesOrderList);
                }
                case "toolMap" -> {
                    List<ToolMap> toolMapList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    toolMapRepository.saveAll(toolMapList);
                }
                case "toolMaster" -> {
                    List<ToolMaster> toolMasterList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    toolMasterRepository.saveAll(toolMasterList);
                }
                case "workcenterMap" -> {
                    List<WorkcenterMap> workcenterMapList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    workcenterMapRepository.saveAll(workcenterMapList);
                }
                case "workcenterMaster" -> {
                    List<WorkcenterMaster> workcenterMasterList = mapper.readValue(item,
                            new TypeReference<>() {
                            });

                    workcenterMasterRepository.saveAll(workcenterMasterList);
                }
                default -> {
                    return 400;
                }
            }
            return 200;
        } catch (Exception e) {
            System.out.println(e);
            return 500;
        }
    }
}
