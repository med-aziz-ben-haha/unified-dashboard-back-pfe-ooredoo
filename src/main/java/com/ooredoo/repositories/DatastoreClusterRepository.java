package com.ooredoo.repositories;

import com.ooredoo.entities.DatastoreCluster;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface DatastoreClusterRepository extends Neo4jRepository<DatastoreCluster, Long> {

    //get DatastoreClusters and their Datastores
    @Query("MATCH (n:DatastoreCluster) RETURN n")
    Collection<DatastoreCluster> getAll();
    //get DatastoreClusters only
    @Query("MATCH (n:DatastoreCluster) RETURN n")
    Collection<DatastoreCluster> getAllDatastoreClusters();

    //add DatastoreCluster
    DatastoreCluster save(DatastoreCluster DatastoreCluster);

    //search
    DatastoreCluster findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
