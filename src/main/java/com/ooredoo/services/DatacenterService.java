package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.Datacenter;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.DatacenterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class DatacenterService {

    final DatacenterRepository DatacenterRepository;
    final HypervisorService HypervisorService;
    final VMService vmService;

    public DatacenterService(DatacenterRepository DatacenterRepository, VMService vmService, HypervisorService HypervisorService) {
        this.DatacenterRepository = DatacenterRepository;
        this.vmService = vmService;
        this.HypervisorService = HypervisorService;
    }


    //------------------- display --------------------------
    //display Datacenters and their Hypervisors list and vms
   /* public Collection<Datacenter> getAll() {
        Collection<Datacenter> Datacenters = DatacenterRepository.getAll();

        for (Datacenter Datacenter : Datacenters) {
            String DatacenterName = Datacenter.getName();
            List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByDatacenterName(DatacenterName);
            for (Hypervisor hypervisor : Hypervisors) {
                String hypervisorName = hypervisor.getName();
                List<VM> vms = vmService.getVMsByHypervisorName(hypervisorName);
                hypervisor.setVMS(vms);
            }
            Datacenter.setHypervisors(Hypervisors);
        }

        return Datacenters;
    }
    //display Datacenters and their Hypervisors list
  public Collection<Datacenter> getAllDatacentersandHypervisors() {
            Collection<Datacenter> Datacenters = DatacenterRepository.getAll();

            for (Datacenter Datacenter : Datacenters) {
                String DatacenterName = Datacenter.getName();
                List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByDatacenterName(DatacenterName);
                Datacenter.setHypervisors(Hypervisors);
            }

            return Datacenters;
              }*/
    //display only Datacenters
    public Collection<Datacenter> getAllDatacenters() {
        return DatacenterRepository.getAllDatacenters();
    }

    //-------------------- add ---------------------------------
    //add single
    public Datacenter addSingleDatacenter(Datacenter Datacenter) { return DatacenterRepository.save(Datacenter);}
    //add multiple
    public List<Datacenter> addMultipleDatacenters(List<Datacenter> Datacenters) {return DatacenterRepository.saveAll(Datacenters);}

    //------------------- update --------------------------------
    public Datacenter updateDatacenterByName(String name, Datacenter updatedDatacenter) {
        Datacenter existingDatacenter = DatacenterRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedDatacenter.getName() != null) { existingDatacenter.setName(updatedDatacenter.getName());}
        if (updatedDatacenter.getDatastoreClusters() != 0) {existingDatacenter.setDatastoreClusters(updatedDatacenter.getDatastoreClusters());}
        if (updatedDatacenter.getDatastores() != 0) {existingDatacenter.setDatastores(updatedDatacenter.getDatastores());}
        if (updatedDatacenter.getHypervisors() != 0) {existingDatacenter.setHypervisors(updatedDatacenter.getHypervisors());}
        if (updatedDatacenter.getVirtualMachines() != 0) {existingDatacenter.setVirtualMachines(updatedDatacenter.getVirtualMachines());}


        // Save the updated Datacenter node
        return DatacenterRepository.save(existingDatacenter);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleDatacenterByName(String name) { DatacenterRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleDatacentersByName(List<String> names) { DatacenterRepository.deleteAllByNameIn(names);}


}
