package com.ooredoo.repositories;

import com.ooredoo.entities.HypervisorCluster;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface HypervisorClusterRepository extends Neo4jRepository<HypervisorCluster, Long> {

    //get HypervisorClusters and their vms
    @Query("MATCH (n:HypervisorCluster) RETURN n")
    Collection<HypervisorCluster> getAll();
    //get HypervisorClusters only
    @Query("MATCH (n:HypervisorCluster) RETURN n")
    Collection<HypervisorCluster> getAllHypervisorClusters();

    //add HypervisorCluster
    HypervisorCluster save(HypervisorCluster HypervisorCluster);

    //search
    HypervisorCluster findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
