package com.ooredoo.controllers;

import com.ooredoo.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.neo4j.core.Neo4jTemplate;



import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/General")
public class GeneralController {

    final Neo4jTemplate neo4jTemplate;
    final com.ooredoo.services.VMService VMService;
    final com.ooredoo.services.GeneralService GeneralService;



    public GeneralController(VMService VMService, GeneralService GeneralService, Neo4jTemplate neo4jTemplate) {
        this.VMService = VMService;
        this.GeneralService = GeneralService;
        this.neo4jTemplate = neo4jTemplate;
    }

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisor-SingleVM
    @PostMapping("/createRelationshipHypervisor-SingleVM")
    public void createRelationshipBetweenHypervisorAndVM(@RequestBody Map<String, String> request) {
        String hypervisorName = request.get("Hypervisor");
        String vmName = request.get("VM");
        GeneralService.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisor-SingleVM
    @DeleteMapping("/deleteRelationshipHypervisor-SingleVM")
    public void deleteRelationshipBetweenHypervisorAndVM(@RequestBody Map<String, String> request) {
        String hypervisorName = request.get("Hypervisor");
        String vmName = request.get("VM");
        GeneralService.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisor-MultipleVMs
    @PostMapping("/createRelationshipHypervisor-MultipleVMs")
    public void createRelationshipBetweenHypervisorAndVMs(@RequestBody Map<String, Object> request) {
        String hypervisorName = (String) request.get("Hypervisor");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisor-MultipleVMs
    @DeleteMapping("/deleteRelationshipHypervisor-MultipleVMs")
    public void deleteRelationshipBetweenHypervisorAndVMs(@RequestBody Map<String, Object> request) {
        String hypervisorName = (String) request.get("Hypervisor");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Hypervisor-VMList
    @PostMapping("/update-Hypervisor-VMList")
    public ResponseEntity<String> updateOneHypervisorVMList() {
        GeneralService.linkHypervisorVMs();
        GeneralService.updateRelationshipBetweenOneHypervisorAndVMs(GeneralService.HypervisorVMList);
        return ResponseEntity.ok("Hypervisor VM List updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-vm-database
    @PostMapping("/update-vm-database")
    public ResponseEntity<String> updateVMDatabase() {
        GeneralService.addVMToList();
        GeneralService.updateVMDatabase(GeneralService.VMsList);
        return ResponseEntity.ok("VM Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-Hypervisor-database
    @PostMapping("/update-Hypervisor-database")
    public ResponseEntity<String> updateHypervisorDatabase() {
        GeneralService.addHypervisorToList();
        GeneralService.updateHypervisorDatabase(GeneralService.HypervisorsList);
        return ResponseEntity.ok("Hypervisor Database updated successfully");
    }

}
