package com.ooredoo.repositories;

import com.ooredoo.entities.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface HypervisorRepository extends Neo4jRepository<Hypervisor, Long> {

    //get hypervisors and their vms
    @Query("MATCH (n:Hypervisor) RETURN n")
    Collection<Hypervisor> getAll();
    //get hypervisors only
    @Query("MATCH (n:Hypervisor) RETURN n")
    Collection<Hypervisor> getAllHypervisors();
    //get Hypervisors based on hypervisorCluster
    @Query("MATCH (h:HypervisorCluster {name: $name})-[:contains]->(Hypervisor:Hypervisor) RETURN Hypervisor")
    List<Hypervisor> findHypervisorsByHypervisorClusterName(@Param("name") String hypervisorClusterName);


    //add hypervisor
    Hypervisor save(Hypervisor hypervisor);

    //search
    Hypervisor findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
