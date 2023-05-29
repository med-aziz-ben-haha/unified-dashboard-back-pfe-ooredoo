package com.ooredoo.controllers;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.services.HypervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Hypervisor")
public class HypervisorController {

    @Autowired
    HypervisorService hypervisorService;

    // http://localhost:8089/ooredoo/Hypervisor/Hypervisor-VM
    @GetMapping("/Hypervisor-VM")
    @ResponseBody
    public Collection<Hypervisor> getAll() {
        return hypervisorService.getAll();
    }

    // http://localhost:8089/ooredoo/Hypervisor/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Hypervisor> getAllHypervisors() { return hypervisorService.getAllHypervisors(); }

    // http://localhost:8089/ooredoo/Hypervisor/add
    @PostMapping("/add")
    public ResponseEntity<Hypervisor> addHypervisor(@RequestBody Hypervisor hypervisor) {
        Hypervisor savedHypervisor = hypervisorService.addHypervisor(hypervisor);
        return ResponseEntity.ok(savedHypervisor);
    }

    // http://localhost:8089/ooredoo/Hypervisor/update/{name}
    @PutMapping("update/{name}")
    public Hypervisor updateHypervisorByName(@PathVariable String name, @RequestBody Hypervisor updatedHypervisor) {
        return hypervisorService.updateHypervisorByName(name, updatedHypervisor);
    }
}
