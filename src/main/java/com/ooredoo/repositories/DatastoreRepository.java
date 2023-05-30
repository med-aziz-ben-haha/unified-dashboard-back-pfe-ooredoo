package com.ooredoo.repositories;

import com.ooredoo.entities.Datastore;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DatastoreRepository extends Neo4jRepository<Datastore, Long> {

    //get Datastores and their vms
    /*@Query("MATCH (n:Datastore) RETURN n")
    Collection<Datastore> getAll();*/
    //get Datastores only
    @Query("MATCH (n:Datastore) RETURN n")
    Collection<Datastore> getAllDatastores();
    //get vm based on Datastore
    @Query("MATCH (d:DatastoreCluster {name: $name})-[:contains]->(Datastore:Datastore) RETURN Datastore")
    List<Datastore> findDatastoresByDatastoreClusterName(@Param("name") String DatastoreClusterName);

    //add Datastore
    Datastore save(Datastore Datastore);

    //search
    Datastore findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
