package com.digimenu;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FoodgroupControler {

	@Autowired
	private FoodRepository frepo;
	
	//Select The foodgroup
	
	@GetMapping("/foodgroup")
	public Map<String,Object> foodgp()
	{
		List<Foodgroup> lst=frepo.findAll();
		Map<String,Object> mp=new LinkedHashMap<>();
		mp.put("status","200");
		mp.put("foodglist",lst);
		return mp;
	}
	//Insert foodgroup
	
	@PostMapping("/addfoodg")
	public ResponseEntity<Map<String,Object>> useradd(@RequestBody @Valid Foodgroup u)
	{
		 frepo.save(u);
		 Map<String,Object> mp=new LinkedHashMap<>();
		 mp.put("status","200");
		 mp.put("message","Insert Success");
	   return  ResponseEntity.status(HttpStatus.CREATED).body(mp);
		
	}
	
	// Update Foodgroup table
	
	@PutMapping("/updatefoodg")
	public ResponseEntity<Map<String, Object>> userupd(@Valid @RequestBody Foodgroup u) {
	    Map<String, Object> response = new LinkedHashMap<>();

	    try {
	        Optional<Foodgroup> existingUserOptional = frepo.findById(u.getGid());

	        if (existingUserOptional.isPresent()) {
	        	Foodgroup existingUser = existingUserOptional.get();

	            // Update relevant fields of existingUser with values from u
	            existingUser.setGname(u.getGname());  // Assuming name is also updatable

	            // Additional validation logic if needed (e.g., check if email is unique)

	            frepo.save(existingUser);
	            response.put("status", "200");
	            response.put("message", "Update Success");
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("status", "400");
	            response.put("message", "Update Failed");
	            return ResponseEntity.ok(response);
	        }
	    } catch (Exception e) {
	        // Handle unexpected exceptions
	        response.put("status", "500");
	        response.put("message", "Internal Server Error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	//--------------------Delete Foodgroup-------------------
	
	@DeleteMapping("/deletefoodg/{id}")
	public ResponseEntity<Map<String, Object>> userdel(@PathVariable int id) {
	    Map<String, Object> response = new LinkedHashMap<>();

	    try {
	        // Check if the user with the given ID exists in the database
	        Optional<Foodgroup> existingUser = frepo.findById(id);
	        if (!existingUser.isPresent()) {
	            response.put("status", "400");
	            response.put("message", "Delete Failed");
	            return ResponseEntity.ok(response);
	        }

	        frepo.deleteById(id);
	        response.put("status", "200");
	        response.put("message", "Delete Success");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Handle unexpected exceptions
	        response.put("status", "500");
	        response.put("message", "Internal Server Error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
}
