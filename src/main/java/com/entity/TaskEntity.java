package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        OPEN,
        INPROGRESS,
        COMPLETED,
        DELIVERED;
    }

    private int fkId;

}// ENDCLASS
