package com.ooredoo.repositories;

import com.ooredoo.entities.Datacenter;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface DatacenterRepository extends Neo4jRepository<Datacenter, Long> {

    //get Datacenters and their vms
   // @Query("MATCH (n:Datacenter) RETURN n")
    //Collection<Datacenter> getAll();
    //get Datacenters only
    @Query("MATCH (n:Datacenter) RETURN n")
    Collection<Datacenter> getAllDatacenters();

    //add Datacenter
    Datacenter save(Datacenter Datacenter);

    //search
    Datacenter findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);


}
