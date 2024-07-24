package com.controller;

import com.entity.StaffEntity;
import com.entity.TaskEntity;
import com.entity.TaskEntity.Status;
import com.lowagie.text.DocumentException;
import com.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.service.DataModelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataModelController {

    @Autowired
    private DataModelService dataModelService;

    //staff

    @RequestMapping(value = "/staff/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffEntity addStaff(@RequestBody StaffEntity staff) {
        return dataModelService.createStaff(staff);
    }

    @RequestMapping(value = "staff/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffEntity updateStaff(@RequestBody StaffEntity staff, @PathVariable("id") int id) {
        return dataModelService.updateStaff(staff, id);
    }

    @RequestMapping(value = "staff/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffEntity getStaffById(@PathVariable("id") int id) {
        return dataModelService.getStaffById(id);
    }

    @RequestMapping(value = "staff/delete/{id}", method = RequestMethod.DELETE)
    public void deleteStaffById(@PathVariable("id") int id) {
        dataModelService.deleteStaffById(id);
    }

    @RequestMapping(value = "/staff/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffEntity> getAllStaffs() {
        return dataModelService.getStaffList();
    }

    //task

    @RequestMapping(value = "task/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskEntity addTask(@RequestBody TaskEntity task) {
        return dataModelService.createTask(task);
    }

    @RequestMapping(value = "task/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskEntity updateTask(@RequestBody TaskEntity task, @PathVariable("id") Integer id) {
        return dataModelService.updateTask(task, id);
    }

    @RequestMapping(value = "task/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskEntity getTaskById(@PathVariable("id") Integer id) {
        return dataModelService.getTaskById(id);
    }

    @RequestMapping(value = "task/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTaskById(@PathVariable("id") int id) {
        dataModelService.deleteTaskById(id);
    }

    @RequestMapping(value = "task/delete", method = RequestMethod.DELETE)
    public void deleteTask() {
        dataModelService.deleteTask();
    }

    @RequestMapping(value = "task/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskEntity> getAllTasks() {
        return dataModelService.getTaskList();
    }

    //staff by status

    @RequestMapping(value = "status/get/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffEntity> getStaffByStatus(@PathVariable("status") Status status) {
        return dataModelService.getStaffByStatus(status);
    }

    //import pdf

    @GetMapping(value = "/export-to-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePdfReport(HttpServletResponse response) throws DocumentException, IOException {

        PdfGenerator generator = new PdfGenerator();
        List<StaffEntity> listOfEmployees = dataModelService.getStaffList();
        generator.generatePdf(listOfEmployees, setHeader(response));
    }

    private HttpServletResponse setHeader(HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return response;
    }

}// ENDCLASS
