package com.ooredoo.repositories;

import com.ooredoo.entities.VM;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface VMRepository extends Neo4jRepository<VM, Long> {

    @Query("MATCH (h:Hypervisor {name: $name})-[:run]->(vm:VM) RETURN vm")
    List<VM> findVMsByHypervisorName(@Param("name") String hypervisorName);

    @Query("MATCH (n:VM) RETURN n")
    Collection<VM> getAllVMs();
}
