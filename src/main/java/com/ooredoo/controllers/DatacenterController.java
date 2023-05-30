package com.ooredoo.controllers;

import com.ooredoo.entities.Datacenter;
import com.ooredoo.services.DatacenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Datacenter")
public class DatacenterController {

    final DatacenterService DatacenterService;

    public DatacenterController(DatacenterService DatacenterService) {
        this.DatacenterService = DatacenterService;
    }

    // http://localhost:8089/ooredoo/Datacenter/Datacenter-Hypervisors-VMs
   /* @GetMapping("/Datacenter-Hypervisors-VMs")
    @ResponseBody
    public Collection<Datacenter> getAll() {
        return DatacenterService.getAll();
    }*/

    // http://localhost:8089/ooredoo/Datacenter/Datacenter-Hypervisors
   /* @GetMapping("/Datacenter-Hypervisors")
    @ResponseBody
    public Collection<Datacenter> getAllDatacentersandHypervisors() {
        return DatacenterService.getAllDatacentersandHypervisors();
    }*/
    // http://localhost:8089/ooredoo/Datacenter/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Datacenter> getAllDatacenters() { return DatacenterService.getAllDatacenters(); }



    // http://localhost:8089/ooredoo/Datacenter/add-one
    @PostMapping("/add-one")
    public ResponseEntity<Datacenter> addSingleDatacenter(@RequestBody Datacenter Datacenter) {
        Datacenter savedDatacenter = DatacenterService.addSingleDatacenter(Datacenter);
        return ResponseEntity.ok(savedDatacenter);
    }
    // http://localhost:8089/ooredoo/Datacenter/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Datacenter>> addMultipleDatacenters(@RequestBody List<Datacenter> Datacenters) {
        List<Datacenter> savedDatacenters = DatacenterService.addMultipleDatacenters(Datacenters);
        return ResponseEntity.ok(savedDatacenters);
    }



    // http://localhost:8089/ooredoo/Datacenter/update/{name}
    @PutMapping("/update/{name}")
    public Datacenter updateDatacenterByName(@PathVariable String name, @RequestBody Datacenter updatedDatacenter) {
        return DatacenterService.updateDatacenterByName(name, updatedDatacenter);
    }



    // http://localhost:8089/ooredoo/Datacenter/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteDatacenterByName(@PathVariable("name") String name) {
        DatacenterService.deleteSingleDatacenterByName(name);
        return ResponseEntity.ok("Datacenter deleted successfully");
    }
    // http://localhost:8089/ooredoo/Datacenter/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleDatacentersByName(@RequestBody List<String> names) {
        DatacenterService.deleteMultipleDatacentersByName(names);
        return ResponseEntity.ok("Datacenters deleted successfully");
    }

}
