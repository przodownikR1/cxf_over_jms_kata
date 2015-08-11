package pl.java.scalatech.spring_camel.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "uploadService")
public interface UploadWebService {

    void uploadFile(@WebParam(name = "fileContainer") FileContainer file);
}