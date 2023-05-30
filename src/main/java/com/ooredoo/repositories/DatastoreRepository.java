package com.ooredoo.repositories;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DatastoreRepository extends Neo4jRepository<Datastore, Long> {

    //get Datastores and their vms
    @Query("MATCH (n:Datastore) RETURN n")
    Collection<Datastore> getAll();
    //get Datastores only
    @Query("MATCH (n:Datastore) RETURN n")
    Collection<Datastore> getAllDatastores();
    //get datastore based on DatastoreCluster
    @Query("MATCH (h:DatastoreCluster {name: $name})-[:contains]->(Datastore:Datastore) RETURN Datastore")
    List<Datastore> findDatastoresByDatastoreClusterName(@Param("name") String DatastoreClusterName);
    //get Datastore based on vm
    @Query("MATCH (vm:VM {name: $name})<-[:Associated]-(Datastore:Datastore) RETURN Datastore")
    List<Datastore> findDatastoresByVMName(@Param("name") String VMName);
    //get Datastore based on Datacenter
    @Query("MATCH (dc:Datacenter {name: $name})-[:contains]->(Datastore:Datastore) RETURN Datastore")
    List<Datastore> findDatastoresByDatacenterName(@Param("name") String DatacenterName);

    //add Datastore
    Datastore save(Datastore Datastore);

    //search
    Datastore findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
