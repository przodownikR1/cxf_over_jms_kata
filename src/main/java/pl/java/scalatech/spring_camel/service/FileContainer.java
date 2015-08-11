package pl.java.scalatech.spring_camel.service;

import javax.activation.DataHandler;

public class FileContainer {
    private String fileName;
    private String fileExtension;
    private DataHandler data;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public DataHandler getData() {
        return data;
    }

    public void setData(DataHandler data) {
        this.data = data;
    }
}