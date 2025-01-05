package com.digimenu;

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
public class LoginControler {

	@Autowired
	private LoginRepository lrepo;
	
	@GetMapping("/login")
	public Map<String,Object> foodgp()
	{
		List<Login> lst=lrepo.findAll();
		Map<String,Object> mp=new LinkedHashMap<>();
		mp.put("status","200");
		mp.put("userlst",lst);
		return mp;
	}
	
	//Insert foodgroup
	
		@PostMapping("/addlogin")
		public ResponseEntity<Map<String,Object>> loginadd(@RequestBody @Valid Login u)
		{
			 lrepo.save(u);
			 Map<String,Object> mp=new LinkedHashMap<>();
			 mp.put("status","200");
			 mp.put("message","Insert Success");
		   return  ResponseEntity.status(HttpStatus.CREATED).body(mp);
			
		}
		
		// Update Foodgroup table
		
		@PutMapping("/updatelogin")
		public ResponseEntity<Map<String, Object>> loginupd(@Valid @RequestBody Login u) {
		    Map<String, Object> response = new LinkedHashMap<>();

		    try {
		        Optional<Login> existingUserOptional = lrepo.findById(u.getLid());

		        if (existingUserOptional.isPresent()) {
		        	Login existingUser = existingUserOptional.get();

		            // Update relevant fields of existingUser with values from u
		        	existingUser.setUname(u.getUname());  // Assuming name is also updatable
		            existingUser.setPwd(u.getPwd());  // Assuming name is also updatable

		            // Additional validation logic if needed (e.g., check if email is unique)

		            lrepo.save(existingUser);
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

		
		//--------------------Delete Login-------------------
		
		@DeleteMapping("/deletelogin/{id}")
		public ResponseEntity<Map<String, Object>> logindel(@PathVariable int id) {
		    Map<String, Object> response = new LinkedHashMap<>();

		    try {
		        // Check if the user with the given ID exists in the database
		        Optional<Login> existingUser = lrepo.findById(id);
		        if (!existingUser.isPresent()) {
		            response.put("status", "400");
		            response.put("message", "Delete Failed");
		            return ResponseEntity.ok(response);
		        }

		        lrepo.deleteById(id);
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
