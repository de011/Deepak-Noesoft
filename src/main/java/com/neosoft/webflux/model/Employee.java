package com.neosoft.webflux.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	@Id
	Integer empId;
	String empName;
	String empAddressLine1;
	String empAddressLine2;
    double salary;
    
    }
