package com.example.ISA2020.service;

import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public interface SftpFileTransferLiveService {
	
	void whenUploadFileUsingJsch_thenSuccess(Long id) throws JSchException, SftpException, Exception;
	
	void whenDownloadFileUsingJsch_thenSuccess(String nameOfFile) throws JSchException, SftpException;
	
	void whenUploadFileUsingSshj_thenSuccess(String nameOfFile) throws IOException;
	
	void whenDownloadFileUsingSshj_thenSuccess(String nameOfFile) throws IOException;
	
	void whenUploadFileUsingApacheVfs_thenSuccess(String nameOfFile) throws IOException;
	
	void whenDownloadFileUsingApacheVfs_thenSuccess(String nameOfFile) throws IOException;
	
	String getUploadDirectory();
}
