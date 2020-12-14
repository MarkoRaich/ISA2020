package com.example.ISA2020.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugNameAndCodeDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.service.DrugService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/noAuth/drug")
public class DrugController {
	
	@Autowired 
	private DrugService drugService;

	
	@PostMapping(value = "/create")
    public ResponseEntity<Drug> create(@RequestBody DrugDTO drugDTO) {
        try {
            Drug newDrug = drugService.createDrug(drugDTO);
            if (newDrug == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newDrug, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<Drug>> getAllDrugs() throws IOException {
		List<Drug> drugs = drugService.getAllDrugs();
		
		File file = new File("./Resources/response" + "AllDrugs" + ".txt"); //C:\Users\dioni\OneDrive\Desktop
		  
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		
		//Write Content
		FileWriter writer = new FileWriter(file);
		for(Drug d : drugs) {
			writer.write("Sifra leka: " + d.getCode());
			writer.write(System.getProperty( "line.separator" ));
			writer.write("Ime leka: " + d.getName());
			writer.write(System.getProperty( "line.separator" ));
			writer.write(System.getProperty( "line.separator" ));
		}
		writer.close();
		
		return new ResponseEntity<>(drugs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Drug> getOneById(@PathVariable Long id) throws Exception{
		Drug drug = drugService.findById(id);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		File file = new File("./Resources/response" + id + ".txt"); //C:\Users\dioni\OneDrive\Desktop
		  
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		
		//Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Sifra leka: " + drug.getCode());
		writer.write(System.getProperty( "line.separator" ));
		writer.write("Ime leka: " + drug.getName());
		writer.write(System.getProperty( "line.separator" ));
		writer.close();
		
		return new ResponseEntity<>(drug, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByCode/{code}")
	public ResponseEntity<Drug> getOneByCode(@PathVariable String code) throws IOException{
		Drug drug = drugService.findByCode(code);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		File file = new File("./Resources/response" + code + ".txt"); 
		  
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		
		//Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Sifra leka: " + drug.getCode());
		writer.write(System.getProperty( "line.separator" ));
		writer.write("Ime leka: " + drug.getName());
		writer.write(System.getProperty( "line.separator" ));
		writer.close();
		
		return new ResponseEntity<>(drug, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getAllWithSameName")
	public ResponseEntity<List<Drug>> getListOfDrugsByName(@RequestParam("name") String name) throws Exception{
		List<Drug> drugs = drugService.getAllDrugs();
		
		if(drugs == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<Drug> drugsWithSameName = new ArrayList<>();
		
		for(Drug d : drugs) {
			if(d.getName().equals(name)) {
				drugsWithSameName.add(d);
			}
		}
		
		return new ResponseEntity<List<Drug>>(drugsWithSameName, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getByCodeInfo/{code}")
	public ResponseEntity<DrugNameAndCodeDTO> getOneByCodeInfo(@PathVariable String code){
		Drug drug = drugService.findByCode(code);
		DrugNameAndCodeDTO drugDTO = new DrugNameAndCodeDTO(drug.getName(), drug.getCode());
		if(drugDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(drugDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = { RequestMethod.GET },value = "/multipartdata/getByCode/{code}", produces=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MultiValueMap<String, Object>> getFileByCode(@PathVariable String code) throws IOException{
		Drug drug = drugService.findByCode(code);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		File file = new File("./Resources/response" + code + ".txt"); 
		  
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		} else {
		    System.out.println("File already exists.");
		}
		
		//Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Sifra leka: " + drug.getCode());
		writer.write(System.getProperty( "line.separator" ));
		writer.write("Ime leka: " + drug.getName());
		writer.write(System.getProperty( "line.separator" ));
		writer.close();
		
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
		formData.add(drug.getCode(),  file);
		
		return new ResponseEntity<MultiValueMap<String, Object>>(formData, HttpStatus.OK);
	}
	
	@RequestMapping(method = { RequestMethod.GET },value = "/multipartdata/getById/{id}")
    public ResponseEntity<List<String>> getFileById(@PathVariable Long id) throws IOException{
        Drug drug = drugService.findById(id);
        if(drug == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        File file = new File("./Resources/response" + id + ".txt"); 
          
        //Create the file
        if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        
        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write("Sifra leka: " + drug.getCode());
        writer.write(System.getProperty( "line.separator" ));
        writer.write("Ime leka: " + drug.getName());
        writer.write(System.getProperty( "line.separator" ));
        writer.close();
        
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        List<String> ret = new ArrayList<String>();
        ret.add("response" + id + ".txt");
        ret.add(encodedString);
        
        return new ResponseEntity<List<String>>(ret, HttpStatus.OK);
    }
	
}