package com.ooredoo.services;

import com.ooredoo.entities.VM;
import com.ooredoo.repositories.VMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VMService {

    @Autowired
    VMRepository VMRepository;

    public Collection<VM> getAllVMs() {
        return VMRepository.getAllVMs();
    }

    public List<VM> getVMsByHypervisorName(String hypervisorName) {
        return VMRepository.findVMsByHypervisorName(hypervisorName);
    }

}
