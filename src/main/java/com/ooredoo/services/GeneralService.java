package com.ooredoo.services;

import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorRepository;
import org.neo4j.driver.types.Relationship;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeneralService {

    private final HypervisorRepository HypervisorRepository;
    private final VMService VMService;

    public GeneralService(HypervisorRepository HypervisorRepository, VMService VMService) {
        this.HypervisorRepository = HypervisorRepository;
        this.VMService  = VMService;
    }

    public Relationship createRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        return HypervisorRepository.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    public void deleteRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        HypervisorRepository.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    public Map<String, Object> HypervisorVMList = new HashMap<>();
    public void linkHypervisorVMs(){
        HypervisorVMList.put("Hypervisor", "HypervisorTest");
        HypervisorVMList.put("VMs", Arrays.asList("TestVM1", /*"TestVM2",*/ "TestVM"));
    }
/*
    public void createRelationshipBetweenOneHypervisorAndVMs(Map<String, Object> HypervisorVMList) {
        String hypervisorName = (String) HypervisorVMList.get("Hypervisor");
        List<String> vmNames = (List<String>) HypervisorVMList.get("VMs");
        for (String vmName : vmNames) {
            createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
        }
    }
*/

    public void updateRelationshipBetweenOneHypervisorAndVMs(Map<String, Object> hypervisorVMList) {
        String hypervisorName = (String) hypervisorVMList.get("Hypervisor");
        List<String> vmList = (List<String>) hypervisorVMList.get("VMs");

        List<VM> currentVMs = VMService.getVMsByHypervisorName(hypervisorName);// Get the current VMs associated with the hypervisor

        for (String vmName : vmList) // Create relationships for new VMs
            if (!currentVMs.stream().anyMatch(vm -> vm.getName().equals(vmName))) createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);

        for (VM vm : currentVMs) // Delete relationships for VMs not in the list
            if (!vmList.contains(vm.getName())) deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vm.getName());

    }



    //-------------------- Update vm database from api ------------------
    public void updateVMDatabase(List<VM> VMs) {
        Collection<VM> existingVMs = VMService.getAllVMs(); // Retrieve all existing VMs from the database
        List<String> existingVMNames = existingVMs.stream().map(VM::getName).collect(Collectors.toList());

        // Add or update VMs
        for (VM vm : VMs) {
            if (existingVMNames.contains(vm.getName()))  VMService.updateVMByName(vm.getName(), vm);  // VM already exists, update it
            else VMService.addMultipleVMs(Collections.singletonList(vm)); // VM is new, add it
        }
        // Delete VMs not present in the input list
        List<String> inputVMNames = VMs.stream().map(VM::getName).collect(Collectors.toList());
        List<String> vmNamesToDelete = existingVMNames.stream().filter(name -> !inputVMNames.contains(name)).collect(Collectors.toList());

        VMService.deleteMultipleVMsByName(vmNamesToDelete);
    }

    public List<VM> VMsList = new ArrayList<>();
    public void addVMToList() {
        VMsList.add(new VM("vmNAFprod2", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("Customized RHEL 7.5 (b0732cab-9479-40bf-99f8-ba4820ff26cb)", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("vmPrepCasApp2", 2, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("noi-devcol-01", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("SNP-WSO2", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VoMS-data33", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("rc1vm10 (c6ee83fa-ab6e-49f9-9c09-c400aa2601ae)", 22, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 6350, 34096, "Normal", 2020, 0.7, 560, 340, 0.4, 10042));
        VMsList.add(new VM("drcrmprodcomp05", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("noi-netimp-02", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VmDBINFORMATICAprd", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("VM_TEST_DMZ_WEB", 2, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("VM-Rhel-Hiba-", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("VM-CDHTestWKn02", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VMSoftSecCent", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("rm2dev.orascomtunisie.com", 22, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 6350, 34096, "Normal", 2020, 0.7, 560, 340, 0.4, 10042));
    }

}
