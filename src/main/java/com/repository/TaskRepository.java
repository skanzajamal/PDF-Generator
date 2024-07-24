package com.repository;

import com.entity.StaffEntity;
import com.entity.TaskEntity;
import com.entity.TaskEntity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Query("SELECT s FROM StaffEntity s INNER JOIN TaskEntity t ON s.id = t.fkId WHERE t.status = :status")
    List<StaffEntity> retrieveStaffByStatus(@Param("status") Status status);


}// ENDINTERFACE

