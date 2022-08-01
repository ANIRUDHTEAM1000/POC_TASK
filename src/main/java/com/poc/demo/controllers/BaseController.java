package com.poc.demo.controllers;

import com.poc.demo.exception.userdefined.AlreadyExistsException;
import com.poc.demo.exception.userdefined.EmptyFieldsException;
import com.poc.demo.exception.userdefined.NoSuchEmployeeException;
import com.poc.demo.models.Employee;
import com.poc.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BaseController {
    @Autowired
    EmployeeService employeeService;

    // get all employees
    @GetMapping("/employees/all")
    ResponseEntity<List<Employee>> getEmployees(){
       return  new ResponseEntity<List<Employee>>(employeeService.getEmployeesService(), HttpStatus.OK);
    }

    // get employee by id, if not present get NOT_FOUND(404) directly response body
    @GetMapping("/employees/{uid}")
    ResponseEntity<?> getEmployeeById(@PathVariable Integer uid){
        Optional<Employee> emp = employeeService.getEmployeeByIdService(uid);
         if(emp.isPresent()){
            return new ResponseEntity<Employee>(emp.get(),HttpStatus.OK);
         }else{
             // sending response and message body directly
            return new ResponseEntity<>("No such Employee with that Id",HttpStatus.NOT_FOUND);
         }
    }

    // all employee, throws Exceptions if fields are empty and if already employee is present
    @PostMapping("/employees/add")
    ResponseEntity<?> addEmployee(@RequestBody Employee e) throws EmptyFieldsException, AlreadyExistsException {
        return new ResponseEntity<>(employeeService.addEmployeeService(e),HttpStatus.CREATED);
    }

    //
    @DeleteMapping("/employees/delete")
    ResponseEntity<?> removeEmployee(@RequestBody Employee e) throws NoSuchEmployeeException {
        if(e.getUid()==null){
            return new ResponseEntity("Undefined user Id",HttpStatus.BAD_REQUEST);
        }
        Employee deletedEmployee = employeeService.removeEmployeeService(e);
        return new ResponseEntity(deletedEmployee,HttpStatus.OK);
    }

    @PutMapping("/employees/update")
    Employee updateEmployee(@RequestBody Employee e) throws NoSuchEmployeeException {
        return employeeService.updateEmployeeService(e);
    }

    // exception handler specific to this controller only
    @ExceptionHandler(AlreadyExistsException.class)
    public  ResponseEntity<String> handleAlreadyExistsException(){
        return new ResponseEntity<String>("Already Exists",HttpStatus.ALREADY_REPORTED);
    }

}
