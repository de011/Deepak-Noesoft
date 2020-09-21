package com.neosoft.webflux.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.webflux.model.Employee;
import com.neosoft.webflux.repo.EmployeeRepository;
import com.neosoft.webflux.service.IEmployeeService;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
    EmployeeRepository employeeRepo;
	@Override
	public Disposable saveEmployee(Employee e) {
		return employeeRepo.save(e).subscribe();
	}
	@Override
	public Mono<Employee> findById(Integer id) {
		return employeeRepo.findById(id);
	}

	@Override
	public Flux<Employee> findByName(String name) {
		return employeeRepo.findByName(name);
	}

	@Override
	public Mono<Employee> updateEmployee(Employee e) {
		return employeeRepo.save(e);
	}


	@Override
	public Mono<Void> deleteEmployee(Integer id) {
		return employeeRepo.deleteById(id);
	}

	@Override
	public Flux<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Mono<Boolean> isEmployyeExist(Integer id) {
		return employeeRepo.existsById(id);
	}
	
}
	

	
	
	
