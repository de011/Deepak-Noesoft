package com.neosoft.webflux.service;
import com.neosoft.webflux.model.Employee;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
		public Disposable saveEmployee(Employee e);
		public Mono<Employee>findById(Integer id);
		public Flux<Employee>findByName(String name);
		public Mono<Employee>updateEmployee(Employee e);
		public Mono<Void> deleteEmployee(Integer id);
		public Flux<Employee>getAllEmployees();
		public Mono<Boolean> isEmployyeExist(Integer id);
		
		}
