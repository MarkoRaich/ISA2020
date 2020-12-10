package com.example.ISA2020.serviceImpl;

import java.io.IOException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.springframework.stereotype.Service;

import com.example.ISA2020.service.SftpFileTransferLiveService;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

@Service
public class SftpFileTransferLiveServiceImpl implements SftpFileTransferLiveService {

    private String remoteHost = "192.168.56.1";
    private String username = "tester";
    private String password = "password";
    private String localFile = "src/main/resources/input.txt"; //upload
    private String remoteFile = "PSW-uploads/2020-12-08-to-2020-12-17-report.json";
    private String localDir = "src/main/resources/";
    private String remoteDir = "remote_sftp_test/"; //upload
    //private String knownHostsFileLoc = "C:/Users/dioni/OneDrive/Desktop/Rebex/data";
    //  C:\Users\dioni\eclipse\java-2020-09\eclipse\eclipse.exe
    
    
    public void whenUploadFileUsingJsch_thenSuccess(String nameOfFile) throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();
        channelSftp.put(nameOfFile, remoteDir + "jschFile.txt"); //localFile
        channelSftp.exit();
    }

   
    public void whenDownloadFileUsingJsch_thenSuccess(String nameOfFile) throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();
        //String putanja = nameOfFile + ".json";
        channelSftp.get(nameOfFile, localDir + "jschFile.txt"); //remoteFile
        channelSftp.exit();
    }

   
    public void whenUploadFileUsingSshj_thenSuccess(String nameOfFile) throws IOException {
        SSHClient sshClient = setupSshj();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        sftpClient.put(nameOfFile, remoteDir + "sshjFile.txt"); //localFile
        sftpClient.close();
        sshClient.disconnect();
    }

    
    public void whenDownloadFileUsingSshj_thenSuccess(String nameOfFile) throws IOException {
        SSHClient sshClient = setupSshj();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        sftpClient.get(nameOfFile, localDir + "sshjFile.txt"); //remoteFile
        sftpClient.close();
        sshClient.disconnect();
    }

    
    public void whenUploadFileUsingApacheVfs_thenSuccess(String nameOfFile) throws IOException {
        FileSystemManager manager = VFS.getManager();
        FileObject local = manager.resolveFile(System.getProperty("user.dir") + "/" + nameOfFile); //localFile
        FileObject remote = manager.resolveFile("sftp://" + username + ":" + password + "@" + remoteHost + "/" + remoteDir + "vfsFile.txt");
        remote.copyFrom(local, Selectors.SELECT_SELF);
        local.close();
        remote.close();
    }

    
    public void whenDownloadFileUsingApacheVfs_thenSuccess(String nameOfFile) throws IOException {
        FileSystemManager manager = VFS.getManager();
        FileObject local = manager.resolveFile(System.getProperty("user.dir") + "/" + localDir + "vfsFile.txt");
        FileObject remote = manager.resolveFile("sftp://" + username + ":" + password + "@" + remoteHost + "/" + nameOfFile); //remoteFile
        local.copyFrom(remote, Selectors.SELECT_SELF);
        local.close();
        remote.close();
    }

    // ====== ssh-keyscan -H -t rsa remoteHost >> known_hosts_sample

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        //jsch.setKnownHosts(knownHostsFileLoc);
        
        Session jschSession = jsch.getSession(username, remoteHost);
        java.util.Properties config	= new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    private SSHClient setupSshj() throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(remoteHost);
        client.authPassword(username, password);
        return client;
    }

}

