package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
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
    private final HypervisorService HypervisorService;

    public GeneralService(HypervisorRepository HypervisorRepository, VMService VMService, HypervisorService HypervisorService) {
        this.HypervisorRepository = HypervisorRepository;
        this.VMService  = VMService;
        this.HypervisorService = HypervisorService;
    }


    //-------------------- Update Hypervisor VM  relationship database ------------------
    public Relationship createRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        return HypervisorRepository.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    public void deleteRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        HypervisorRepository.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    public Map<String, Object> HypervisorVMList = new HashMap<>();

    public void linkHypervisorVMs(){
        HypervisorVMList.put("Hypervisor", "HypervisorTest1");
        HypervisorVMList.put("VMs", Arrays.asList("TestVM1", /*"TestVM2",*/ "TestVM"));
    }

    public void updateRelationshipBetweenOneHypervisorAndVMs(Map<String, Object> hypervisorVMList) {
        String hypervisorName = (String) hypervisorVMList.get("Hypervisor");
        List<String> vmList = (List<String>) hypervisorVMList.get("VMs");

        List<VM> currentVMs = VMService.getVMsByHypervisorName(hypervisorName);// Get the current VMs associated with the hypervisor

        for (String vmName : vmList) // Create relationships for new VMs
            if (!currentVMs.stream().anyMatch(vm -> vm.getName().equals(vmName))) createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);

        for (VM vm : currentVMs) // Delete relationships for VMs not in the list
            if (!vmList.contains(vm.getName())) deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vm.getName());

    }

    //-------------------- Update Hypervisor database ------------------
    public void updateHypervisorDatabase(List<Hypervisor> Hypervisors) {
        Collection<Hypervisor> existingHypervisors = HypervisorService.getAllHypervisors(); // Retrieve all existing Hypervisors from the database
        List<String> existingHypervisorNames = existingHypervisors.stream().map(Hypervisor::getName).collect(Collectors.toList());

        // Add or update VMs
        for (Hypervisor Hypervisor : Hypervisors) {
            if (existingHypervisorNames.contains(Hypervisor.getName()))  HypervisorService.updateHypervisorByName(Hypervisor.getName(), Hypervisor);  // Hypervisor already exists, update it
            else HypervisorService.addMultipleHypervisors(Collections.singletonList(Hypervisor)); // Hypervisor is new, add it
        }
        // Delete Hypervisors not present in the input list
        List<String> inputHypervisorNames = Hypervisors.stream().map(Hypervisor::getName).collect(Collectors.toList());
        List<String> HypervisorNamesToDelete = existingHypervisorNames.stream().filter(name -> !inputHypervisorNames.contains(name)).collect(Collectors.toList());

        HypervisorService.deleteMultipleHypervisorsByName(HypervisorNamesToDelete);
    }

    public List<Hypervisor> HypervisorsList = new ArrayList<>();
    public void addHypervisorToList() {
        HypervisorsList.add(new Hypervisor("cha-esxi-itaas04.intra.local", 48.78, 140.97, 76.77, "CH121 V3", "Normal", 88, 1048173, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-itaas03.intra.local", 45.14, 254.57, 88.26, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-edge04.intra.local", 1.18, 0.06, 3.29, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-edge01.intra.local", 1.36, 0.29, 2.0, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("esxi01-edge.unifydev.local", 0.52, 0.12, 2.15, "CH121 V3", "Normal", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("cha-esxi-edge03.intra.local", 1.66, 0.14, 1.88, "CH121 V3", "Normal", 88, 1048173, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("esxi01-itaas.unifydev.local", 25.03, 8.61, 89.21, "CH121 V3", "Warning", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("esxi04-itaas.unifydev.local", 68.78, 0.98, 95.38, "CH121 V3", "Alert", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("HypervisorTest1", 8.78, 40.0, 6.77, "V2", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353"));

    }

    //-------------------- Update VM database ------------------
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
