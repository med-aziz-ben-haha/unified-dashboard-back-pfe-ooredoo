package com.ooredoo.repositories;

import com.ooredoo.entities.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.*;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:User)<-[r:RATED]-(m:Movie) RETURN u,r,m")
    Collection<User> getAll();

    @Query("MATCH (n:User) RETURN n")
    Collection<User> getAllUsers();
}
