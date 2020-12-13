package com.example.ISA2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.NormalUserDTO;
import com.example.ISA2020.dto.SftpDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.service.DrugService;
import com.example.ISA2020.service.SftpFileTransferLiveService;

@RestController
@RequestMapping(value = "/api/noAuth/sftp")
public class SftpFileTransferLiveController {
	
	@Autowired
	public SftpFileTransferLiveService sftpService;
	
	/*
	 * @Autowired public DrugService drugService;
	 */
	
	
	//1
	@GetMapping(value = "/uploadJsch/{id}")
    public ResponseEntity<String> uploadJsch(@PathVariable Long id) {
		try {
			sftpService.whenUploadFileUsingJsch_thenSuccess(id);

		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		
		String putanja = sftpService.getUploadDirectory();
		
		return new ResponseEntity<>(putanja, HttpStatus.OK);
	}
	
	//2
	@PostMapping(value = "/downloadJsch")
    public ResponseEntity<SftpDTO> downloadJsch(@RequestBody SftpDTO sftpFile) {
		try {
			if(sftpFile == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			sftpService.whenDownloadFileUsingJsch_thenSuccess(sftpFile.getNameOfFile());
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		SftpDTO sftpDTO = new SftpDTO(sftpFile);
		return new ResponseEntity<>(sftpDTO, HttpStatus.OK);
	}
	
	//3
	@PostMapping(value = "/uploadSshj")
    public ResponseEntity<SftpDTO> uploadSshj(@RequestBody SftpDTO sftpFile) {
		try {
			if(sftpFile == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			sftpService.whenUploadFileUsingSshj_thenSuccess(sftpFile.getNameOfFile());
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		SftpDTO sftpDTO = new SftpDTO(sftpFile);
		return new ResponseEntity<>(sftpDTO, HttpStatus.OK);
	}
	
	//4
	@PostMapping(value = "/downloadSshj")
    public ResponseEntity<SftpDTO> downloadSshj(@RequestBody SftpDTO sftpFile) {
		try {
			if(sftpFile == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			sftpService.whenDownloadFileUsingSshj_thenSuccess(sftpFile.getNameOfFile());
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		SftpDTO sftpDTO = new SftpDTO(sftpFile);
		return new ResponseEntity<>(sftpDTO, HttpStatus.OK);
	}
	
	//5
	@PostMapping(value = "/uploadVfs")
    public ResponseEntity<SftpDTO> uploadApacheVfs(@RequestBody SftpDTO sftpFile) {
		try {
			if(sftpFile == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			sftpService.whenUploadFileUsingApacheVfs_thenSuccess(sftpFile.getNameOfFile());
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		SftpDTO sftpDTO = new SftpDTO(sftpFile);
		return new ResponseEntity<>(sftpDTO, HttpStatus.OK);
	}
	
	//6
	@PostMapping(value = "/downloadVfs")
    public ResponseEntity<SftpDTO> downloadVfs(@RequestBody SftpDTO sftpFile) {
		try {
			if(sftpFile == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			sftpService.whenDownloadFileUsingApacheVfs_thenSuccess(sftpFile.getNameOfFile());
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		SftpDTO sftpDTO = new SftpDTO(sftpFile);
		return new ResponseEntity<>(sftpDTO, HttpStatus.OK);
	}
	
	
	
}
