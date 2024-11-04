package com.service;

import com.entity.StaffEntity;
import com.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataModelService {

    @Autowired
    StaffRepository staffRepository;

    public List<StaffEntity> getStaffList() {
        return (List<StaffEntity>) staffRepository.findAll();
    }

}// ENDCLASS
