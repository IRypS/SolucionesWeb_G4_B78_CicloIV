package com.soluciones.web.appGrupo4.utils.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.model.ResponseFile;

public interface IImageLocalHandler {
    
    public ResponseFile saveImageInLocal(MultipartFile fileImage, String folderPath);

    public void deleteImageLocal(String fileNameImage, String folderPath);

}
