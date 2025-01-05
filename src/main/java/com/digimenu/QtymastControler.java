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
public class QtymastControler {

	@Autowired
	private QtymastRepository qrepo;
	
	@GetMapping("/qtymast")
	public Map<String,Object> allqtymast()
	{
		List<Qtymast> lst=qrepo.findAll();
		Map<String,Object> mp=new LinkedHashMap<>();
		mp.put("status","200");
		mp.put("qtymastlst",lst);
		return mp;
	}
	
	//---------------Insert foodgroup--------------------------------
	
		@PostMapping("/addqty")
		public ResponseEntity<Map<String,Object>> qtyadd(@RequestBody @Valid Qtymast u)
		{
			 qrepo.save(u);
			 Map<String,Object> mp=new LinkedHashMap<>();
			 mp.put("status","200");
			 mp.put("message","Insert Success");
		   return  ResponseEntity.status(HttpStatus.CREATED).body(mp);
			
		}
		
		// Update Foodgroup table
		
		@PutMapping("/updateqty")
		public ResponseEntity<Map<String, Object>> qtyupd(@Valid @RequestBody Qtymast u) {
		    Map<String, Object> response = new LinkedHashMap<>();

		    try {
		        Optional<Qtymast> existingUserOptional = qrepo.findById(u.getQid());

		        if (existingUserOptional.isPresent()) {
		        	Qtymast existingUser = existingUserOptional.get();

		            // Update relevant fields of existingUser with values from u
		            existingUser.setQtytype(u.getQtytype());  // Assuming name is also updatable

		            // Additional validation logic if needed (e.g., check if email is unique)

		            qrepo.save(existingUser);
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
		
		@DeleteMapping("/deleteqty/{id}")
		public ResponseEntity<Map<String, Object>> qtydel(@PathVariable int id) {
		    Map<String, Object> response = new LinkedHashMap<>();

		    try {
		        // Check if the user with the given ID exists in the database
		        Optional<Qtymast> existingUser = qrepo.findById(id);
		        if (!existingUser.isPresent()) {
		            response.put("status", "400");
		            response.put("message", "Delete Failed");
		            return ResponseEntity.ok(response);
		        }

		        qrepo.deleteById(id);
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
