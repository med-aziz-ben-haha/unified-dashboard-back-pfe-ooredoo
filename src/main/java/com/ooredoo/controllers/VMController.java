package com.ooredoo.controllers;

import com.ooredoo.entities.VM;
import com.ooredoo.services.VMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/VM")
public class VMController {

    @Autowired
    VMService VMService;

    // http://localhost:8089/ooredoo/VM/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<VM> getAllVMs() { return VMService.getAllVMs(); }

    // http://localhost:8089/ooredoo/VM/{hypervisorName}/vms
    @GetMapping("/{hypervisorName}/vms")
    public List<VM> getVMsByHypervisorName(@PathVariable String hypervisorName) {
        return VMService.getVMsByHypervisorName(hypervisorName);
    }

}
