package com.ooredoo.controllers;

import com.ooredoo.entities.Datacenter;
import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.services.DatacenterService;
import com.ooredoo.services.DatastoreClusterService;
import com.ooredoo.services.GeneralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GeneralControllerImplTest {
    @Mock
    private GeneralService generalService;

    @InjectMocks
    private GeneralController generalController;

    @Mock
    private DatastoreClusterService datastoreClusterService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // ############################## Relationships Update ########################################################

    //--------------------------------- Datacenter DatastoreCluster --------------------------------------
    @Test
    void createRelationshipBetweenDatacenterAndDatastoreCluster_ShouldCallServiceWithCorrectArguments() {
        // Arrange
        String datacenterName = "Datacenter1";
        String datastoreClusterName = "DatastoreCluster1";
        Map<String, String> request = new HashMap<>();
        request.put("Datacenter", datacenterName);
        request.put("DatastoreCluster", datastoreClusterName);

        // Act
        generalController.createRelationshipBetweenDatacenterAndDatastoreCluster(request);

        // Assert
        verify(generalService).createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);
    }
    @Test
    void deleteRelationshipBetweenDatacenterAndDatastoreCluster_ShouldCallServiceWithCorrectArguments() {
        // Arrange
        String datacenterName = "Datacenter1";
        String datastoreClusterName = "DatastoreCluster1";
        Map<String, String> request = new HashMap<>();
        request.put("Datacenter", datacenterName);
        request.put("DatastoreCluster", datastoreClusterName);

        // Act
        generalController.deleteRelationshipBetweenDatacenterAndDatastoreCluster(request);

        // Assert
        verify(generalService).deleteRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);
    }
    @Test
    void createRelationshipBetweenDatacenterAndDatastoreClusters_ShouldCallServiceWithCorrectArguments() {
        // Arrange
        String datacenterName = "Datacenter1";
        List<String> datastoreClusterNames = Arrays.asList("DatastoreCluster1", "DatastoreCluster2");
        Map<String, Object> request = new HashMap<>();
        request.put("Datacenter", datacenterName);
        request.put("DatastoreClusters", datastoreClusterNames);

        // Act
        generalController.createRelationshipBetweenDatacenterAndDatastoreClusters(request);

        // Assert
        for (String clusterName : datastoreClusterNames) {
            verify(generalService).createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, clusterName);
        }
    }
    @Test
    void deleteRelationshipBetweenDatacenterAndDatastoreClusters_ShouldCallServiceWithCorrectArguments() {
        // Arrange
        String datacenterName = "Datacenter1";
        List<String> datastoreClusterNames = Arrays.asList("DatastoreCluster1", "DatastoreCluster2");
        Map<String, Object> request = new HashMap<>();
        request.put("Datacenter", datacenterName);
        request.put("DatastoreClusters", datastoreClusterNames);

        // Act
        generalController.deleteRelationshipBetweenDatacenterAndDatastoreClusters(request);

        // Assert
        for (String clusterName : datastoreClusterNames) {
            verify(generalService).deleteRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, clusterName);
        }
    }
    @Test
    void updateOneDatacenterDatastoreClusterList_ShouldCallServiceMethodsAndReturnSuccessResponse() {
        // Arrange
        List<DatastoreCluster> datastoreClusterList = Arrays.asList(
                new DatastoreCluster("DatastoreCluster1"),
                new DatastoreCluster("DatastoreCluster2")
        );
        when(datastoreClusterService.getDatastoreClustersByDatacenterName(anyString())).thenReturn(datastoreClusterList);

        // Act
        ResponseEntity<String> response = generalController.updateOneDatacenterDatastoreClusterList();

        // Assert
        verify(generalService).linkDatacenterDatastoreClusters();
        //verify(generalService).updateRelationshipBetweenOneDatacenterAndDatastoreClusters(datastoreClusterList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datacenter DatastoreCluster List updated successfully", response.getBody());
    }
}
