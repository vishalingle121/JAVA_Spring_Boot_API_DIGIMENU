package com.digimenu;

import java.util.ArrayList;
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
public class MenuControler {

	@Autowired
	private MenuRepository mrepo;
	
	@GetMapping("/menu")
	public Map<String,Object> allmenu()
	{
		List<Menu> lst=mrepo.findAll();
		Map<String,Object> mp=new LinkedHashMap<>();
		mp.put("status","200");
		mp.put("menulst",lst);
		return mp;
	}
	//---------------Insert foodgroup--------------------------------
	
			@PostMapping("/addmenu")
			public ResponseEntity<Map<String,Object>> menuadd(@RequestBody @Valid Menu u)
			{
				 mrepo.save(u);
				 Map<String,Object> mp=new LinkedHashMap<>();
				 mp.put("status","200");
				 mp.put("message","Insert Success");
			   return  ResponseEntity.status(HttpStatus.CREATED).body(mp);
				
			}
			
			// Update Foodgroup table
			
			@PutMapping("/updatemenu")
			public ResponseEntity<Map<String, Object>> menuupd(@Valid @RequestBody Menu u) {
			    Map<String, Object> response = new LinkedHashMap<>();

			    try {
			        Optional<Menu> existingUserOptional = mrepo.findById(u.getMid());

			        if (existingUserOptional.isPresent()) {
			        	Menu existingUser = existingUserOptional.get();

			            // Update relevant fields of existingUser with values from u
			            existingUser.setMname(u.getMname());  // Assuming name is also updatable
			            existingUser.setMprice(u.getMprice());
			            existingUser.setQid(u.getQid());
			            existingUser.setGid(u.getGid());

			            // Additional validation logic if needed (e.g., check if email is unique)

			            mrepo.save(existingUser);
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
			
			@DeleteMapping("/deletemenu/{id}")
			public ResponseEntity<Map<String, Object>> menudel(@PathVariable int id) {
			    Map<String, Object> response = new LinkedHashMap<>();

			    try {
			        // Check if the user with the given ID exists in the database
			        Optional<Menu> existingUser = mrepo.findById(id);
			        if (!existingUser.isPresent()) {
			            response.put("status", "400");
			            response.put("message", "Delete Failed");
			            return ResponseEntity.ok(response);
			        }

			        mrepo.deleteById(id);
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
			
			//----------------Menu card----------------------------
			
			@GetMapping("/menucard")
			public Map<String, Object> getstfdata() {
			    List<Object[]> rawData = mrepo.lstMenuCard();
			    List<Map<String, Object>> dls = new ArrayList<>();

			    for (Object[] record : rawData) {
			        Map<String, Object> map = new HashMap<>();
			        map.put("group", record[0]);
			        map.put("menu", record[1]);
			        map.put("price", record[2]);
			        map.put("qty", record[3]);
			        dls.add(map);
			    }

			    // Create the final JSON response
			    Map<String, Object> response = new LinkedHashMap<>();
			    response.put("status", 200);   // Adds "status" first
			    response.put("MenuLst", dls);    // Adds "VList" second

			    return response;
			}
			
}
