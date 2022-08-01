package com.poc.demo.services;

import com.poc.demo.exception.userdefined.AlreadyExistsException;
import com.poc.demo.exception.userdefined.EmptyFieldsException;
import com.poc.demo.exception.userdefined.NoSuchEmployeeException;
import com.poc.demo.models.Employee;
import com.poc.demo.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getEmployeesService(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeByIdService(Integer uid){
        return employeeRepo.findById(uid);
    }

    public Employee addEmployeeService(Employee e) throws EmptyFieldsException, AlreadyExistsException {
        if(e.getName().isBlank() || e.getName().isEmpty() || e.getName().length()==0 || e.getUid().describeConstable().isEmpty()){
            throw new EmptyFieldsException();
        }
        if(employeeRepo.findById(e.getUid()).isPresent()){
            throw new AlreadyExistsException();
        }
        return employeeRepo.save(e);
    }

    public Employee removeEmployeeService(Employee e) throws NoSuchEmployeeException {
        if(employeeRepo.findById(e.getUid()).isEmpty()){
            throw new NoSuchEmployeeException();
        }
        employeeRepo.delete(e);
        return e;
    }

    public Employee updateEmployeeService(Employee e) throws NoSuchEmployeeException {
        if(!employeeRepo.findById(e.getUid()).isPresent()){
            throw new NoSuchEmployeeException();
        }
        return employeeRepo.save(e);
    }

}
