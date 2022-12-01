package com.soluciones.web.appGrupo4.model;

import org.springframework.web.multipart.MultipartFile;

public class ResponseFile {
    
    private Boolean state;
	private String fileName;
	private MultipartFile multipartFile;
	private String errorMessage;


    public ResponseFile() {
        this.state = false;
        this.fileName = "";
        this.multipartFile = null;
        this.errorMessage = "";
    }


    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
