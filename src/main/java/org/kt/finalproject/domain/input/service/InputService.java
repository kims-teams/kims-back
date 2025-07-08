package org.kt.finalproject.domain.input.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.input.entity.*;
import org.kt.finalproject.domain.input.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final org.kt.finalproject.domain.input.repository.ToolMapRepository toolMapRepository;
    private final ToolMasterRepository toolMasterRepository;
    private final WorkcenterMapRepository workcenterMapRepository;
    private final WorkcenterMasterRepository workcenterMasterRepository;
    private final ScenarioRepository scenarioRepository;


    public int saveInputData(String item, String dataType) throws JsonProcessingException {

        JsonNode root = mapper.readTree(item);
        JsonNode dataArray = root.path("data");
        Optional<Scenario> OpScenario = scenarioRepository.findById(root.path("scenario_id").asInt());
        if(OpScenario.isEmpty()){
            System.out.println("❌ 해당 ID의 시나리오가 없습니다: " + root.path("scenario_id").asInt());
            return 400;
        }
        Scenario scenario = OpScenario.get();

        ArrayNode cleanedArray = mapper.createArrayNode();

        for (JsonNode node : dataArray) {
            ObjectNode obj = node.deepCopy();
            obj.remove("id");
            cleanedArray.add(obj);
        }

        System.out.println("data = " + cleanedArray);

        try {
            switch (dataType) {
                case "bom" -> {
                    List<Bom> bomList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (Bom bom : bomList){
                        bom.setScenario(scenario);
                    }

                    bomRepository.saveAll(bomList);
                }
                case "manufacturing_process" -> {
                    List<ManufacturingProcess> manufacturingProcessList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for ( ManufacturingProcess manufacturingProcess : manufacturingProcessList){
                        manufacturingProcess.setScenario(scenario);
                    }

                    manufacturingProcessRepository.saveAll(manufacturingProcessList);
                }
                case "material_master" -> {
                    List<MaterialMaster> materialMasterList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (MaterialMaster materialMaster : materialMasterList) {
                        materialMaster.setScenario(scenario);
                    }

                    materialMasterRepository.saveAll(materialMasterList);
                }
                case "operation" -> {
                    List<Operation> operationList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (Operation operation : operationList) {
                        operation.setScenario(scenario);
                    }

                    operationRepository.saveAll(operationList);
                }
                case "operation_sequence" -> {
                    List<OperationSequence> operationSequenceList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (OperationSequence operationSequence : operationSequenceList) {
                        operationSequence.setScenario(scenario);
                    }

                    operationSequenceRepository.saveAll(operationSequenceList);
                }
                case "plant_master" -> {
                    List<PlantMaster> plantMasterList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (PlantMaster plantMaster : plantMasterList) {
                        plantMaster.setScenario(scenario);
                    }

                    plantMasterRepository.saveAll(plantMasterList);
                }
                case "priority" -> {
                    List<Priority> priorityList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (Priority priority : priorityList) {
                        priority.setScenario(scenario);
                    }

                    priorityRepository.saveAll(priorityList);
                }
                case "sales_order" -> {
                    List<SalesOrder> salesOrderList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (SalesOrder salesOrder : salesOrderList) {
                        salesOrder.setScenario(scenario);
                    }

                    salesOrderRepository.saveAll(salesOrderList);
                }
                case "tool_map" -> {
                    List<ToolMap> toolMapList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (ToolMap toolMap : toolMapList) {
                        toolMap.setScenario(scenario);
                    }

                    toolMapRepository.saveAll(toolMapList);
                }
                case "tool_master" -> {
                    List<ToolMaster> toolMasterList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (ToolMaster toolMaster : toolMasterList) {
                        toolMaster.setScenario(scenario);
                    }

                    toolMasterRepository.saveAll(toolMasterList);
                }
                case "workcenter_map" -> {
                    List<WorkcenterMap> workcenterMapList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (WorkcenterMap workcenterMap : workcenterMapList) {
                        workcenterMap.setScenario(scenario);
                    }

                    workcenterMapRepository.saveAll(workcenterMapList);
                }
                case "workcenter_master" -> {
                    List<WorkcenterMaster> workcenterMasterList = mapper.readValue(cleanedArray.traverse(),
                            new TypeReference<>() {
                            });

                    for (WorkcenterMaster workcenterMaster : workcenterMasterList) {
                        workcenterMaster.setScenario(scenario);
                    }

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
    public List<?> getInputDataByScenario(int scenarioId, String dataType){
        System.out.println(scenarioId);
        System.out.println(dataType);
        return switch (dataType) {
            case "bom" -> bomRepository.findByScenarioId(scenarioId);
            case "manufacturing_process" -> manufacturingProcessRepository.findByScenarioId(scenarioId);
            case "material_master" -> materialMasterRepository.findByScenarioId(scenarioId);
            case "operation" -> operationRepository.findByScenarioId(scenarioId);
            case "operation_sequence" -> operationSequenceRepository.findByScenarioId(scenarioId);
            case "plant_master" -> plantMasterRepository.findByScenarioId(scenarioId);
            case "priority" -> priorityRepository.findByScenarioId(scenarioId);
            case "sales_order" -> salesOrderRepository.findByScenarioId(scenarioId);
            case "tool_map" -> toolMapRepository.findByScenarioId(scenarioId);
            case "tool_master" -> toolMasterRepository.findByScenarioId(scenarioId);
            case "workcenter_map" -> workcenterMapRepository.findByScenarioId(scenarioId);
            case "workcenter_master" -> workcenterMasterRepository.findByScenarioId(scenarioId);
            default -> throw new IllegalArgumentException("지원하지 않는 dataType: " + dataType);
        };

    }
}
