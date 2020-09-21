package com.neosoft.webflux.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neosoft.webflux.model.Employee;
import com.neosoft.webflux.service.impl.EmployeeService;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("rest/employee")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(
			@RequestBody Employee employee)
	{
		ResponseEntity<String> resp=null;
		try {
			//if Employee Id exist
			
			if(employee.getEmpId()!=null && employeeService.isEmployyeExist(employee.getEmpId()).subscribe() != null)
					
			{
				resp = new ResponseEntity<String>(
						"Given Id '"+employee.getEmpId()+"' Data already exist",HttpStatus.BAD_REQUEST);

			} else { //Employee id not exist
				Disposable id=employeeService.saveEmployee(employee);
				resp = new ResponseEntity<String>("Employee '"+id+"' created Successfully!",HttpStatus.OK //200
						//HttpStatus.CREATED //201
						);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to Save Product",
					HttpStatus.INTERNAL_SERVER_ERROR //Our App Got Exception
					);
			e.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id)
	{
		ResponseEntity<?>  resp = null;
		try {
			Optional<Employee> opt = Optional.empty();	
			if(opt.isPresent()) { //if Employee exist
				Employee employee = opt.get();
				resp = new ResponseEntity<Employee>(employee,HttpStatus.OK);
				
			} else { //if Employee not exist
				resp = new ResponseEntity<String>("Employee '"+id+"' Not exist!",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to fetch Employee", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		ResponseEntity<?> resp = null;
		try {
			
			Flux<Employee> flux = employeeService.getAllEmployees();
			resp = new ResponseEntity<Flux<Employee>>(flux,HttpStatus.OK);
			
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Employee",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@PutMapping("/modify")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) 
	{
		ResponseEntity<String> resp = null;
		try {
			//if employee exist - then update
			if(employee.getEmpId()!=null && employeeService.isEmployyeExist(employee.getEmpId()).subscribe() != null)
			{
				employeeService.updateEmployee(employee);
				resp = new ResponseEntity<String>(
						"employee '"+employee.getEmpId()+"' Updated!",HttpStatus.OK);
				
			} else {
				//if Employee not exist - return error message
				resp = new ResponseEntity<String>(
						"employee '"+employee.getEmpId()+"' not exist!",
						HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to update Employee", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable Integer id) 
	{ 
		ResponseEntity<String> resp  = null;
		try {
			//if Employee exist based on Id - DELETE call
			if(employeeService.isEmployyeExist(id) != null) {
				employeeService.deleteEmployee(id);
				resp = new ResponseEntity<String>(
						"employee '"+id+"' Deleted!",
						HttpStatus.OK);
			} else {
				//if given Employee id not exist
				resp = new ResponseEntity<String>("employee '"+id+"' not exist",
						//HttpStatus.NOT_FOUND
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to Delete Employee", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	
		
}

