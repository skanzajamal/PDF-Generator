package com.service;

import com.entity.StaffEntity;
import com.entity.TaskEntity;
import com.entity.TaskEntity.Status;
import com.repository.StaffRepository;
import com.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataModelService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    TaskRepository taskRepository;

    //staff

    public StaffEntity createStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    public StaffEntity updateStaff(StaffEntity staff, int id) {
        var record = staffRepository.getOne(id);
        BeanUtils.copyProperties(record, staff);
        return staffRepository.save(record);
    }

    public StaffEntity getStaffById(int id) {
        return staffRepository.findById(id).orElse(null);
    }

    public void deleteStaffById(int id) {
        staffRepository.deleteById(id);
    }

    public List<StaffEntity> getStaffList() {
        return (List<StaffEntity>) staffRepository.findAll();
    }

    //task

    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public TaskEntity updateTask(TaskEntity task, Integer id) {
        var record = taskRepository.getOne(id);
        BeanUtils.copyProperties(record, task);
        return taskRepository.save(record);
    }

    public TaskEntity getTaskById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    public void deleteTask() {
        taskRepository.deleteAll();
    }

    public List<TaskEntity> getTaskList() {
        return (List<TaskEntity>) taskRepository.findAll();
    }

    public List<StaffEntity> getStaffByStatus(Status status) {

        return taskRepository.retrieveStaffByStatus(status);
    }

}// ENDCLASS
