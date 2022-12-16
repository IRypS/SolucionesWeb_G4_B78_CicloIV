package com.soluciones.web.appGrupo4.utils.interfaces;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IImageCloudHandler {

    public Map<String, String> upload(MultipartFile fileImage) throws IOException;

    public Map<String, String> delete(String id) throws IOException;
    
}
