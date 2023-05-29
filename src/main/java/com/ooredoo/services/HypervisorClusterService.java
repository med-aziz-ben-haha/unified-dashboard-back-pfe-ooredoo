package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.repositories.HypervisorClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class HypervisorClusterService {

    final HypervisorClusterRepository HypervisorClusterRepository;
    @Autowired
    HypervisorService HypervisorService;

    public HypervisorClusterService(HypervisorClusterRepository HypervisorClusterRepository) {
        this.HypervisorClusterRepository = HypervisorClusterRepository;
    }


    //------------------- display --------------------------
    //display HypervisorClusters and their Hypervisors list
  public Collection<HypervisorCluster> getAll() {
            Collection<HypervisorCluster> HypervisorClusters = HypervisorClusterRepository.getAll();

            for (HypervisorCluster HypervisorCluster : HypervisorClusters) {
                String HypervisorClusterName = HypervisorCluster.getName();
                List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByHypervisorClusterName(HypervisorClusterName);
                HypervisorCluster.setHypervisors(Hypervisors);
            }

            return HypervisorClusters;
              }
    //display only HypervisorClusters
    public Collection<HypervisorCluster> getAllHypervisorClusters() {
        return HypervisorClusterRepository.getAllHypervisorClusters();
    }

    //-------------------- add ---------------------------------
    //add single
    public HypervisorCluster addSingleHypervisorCluster(HypervisorCluster HypervisorCluster) { return HypervisorClusterRepository.save(HypervisorCluster);}
    //add multiple
    public List<HypervisorCluster> addMultipleHypervisorClusters(List<HypervisorCluster> HypervisorClusters) {return HypervisorClusterRepository.saveAll(HypervisorClusters);}

    //------------------- update --------------------------------
    public HypervisorCluster updateHypervisorClusterByName(String name, HypervisorCluster updatedHypervisorCluster) {
        HypervisorCluster existingHypervisorCluster = HypervisorClusterRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedHypervisorCluster.getName() != null) { existingHypervisorCluster.setName(updatedHypervisorCluster.getName());}
        if (updatedHypervisorCluster.getEsxsInHighMemoryUsage() != 0) {existingHypervisorCluster.setEsxsInHighMemoryUsage(updatedHypervisorCluster.getEsxsInHighMemoryUsage());}
        if (updatedHypervisorCluster.getNumberOfESX() != 0) {existingHypervisorCluster.setNumberOfESX(updatedHypervisorCluster.getNumberOfESX());}
        if (updatedHypervisorCluster.getTotalCPU() != 0) {existingHypervisorCluster.setTotalCPU(updatedHypervisorCluster.getTotalCPU());}
        if (updatedHypervisorCluster.getTotalMemory() != 0) {existingHypervisorCluster.setTotalMemory(updatedHypervisorCluster.getTotalMemory());}


        // Save the updated HypervisorCluster node
        return HypervisorClusterRepository.save(existingHypervisorCluster);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleHypervisorClusterByName(String name) { HypervisorClusterRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleHypervisorClustersByName(List<String> names) { HypervisorClusterRepository.deleteAllByNameIn(names);}

}
