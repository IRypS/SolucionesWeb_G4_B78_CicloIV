package com.soluciones.web.appGrupo4.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.model.ResponseFile;
import com.soluciones.web.appGrupo4.utils.interfaces.IImageLocalHandler;

@Component
public class ImageLocalHandler implements IImageLocalHandler {
    
    @Value("${image.folder.path.general}")
    public String mainFolderPath;


    @Override
    public ResponseFile saveImageInLocal(MultipartFile fileImage, String folderPath) {
        
        ResponseFile responseFile = new ResponseFile();
		
		String newNameFile = UUID.randomUUID().toString();		
		String extesionFile = org.springframework.util.StringUtils
                .getFilenameExtension(fileImage.getOriginalFilename());

        try {

            byte[] bytesCoverImg = fileImage.getBytes();
            Path pathImage = Paths.get(folderPath);
            Path completePath = Paths.get(pathImage + "//" + newNameFile + "." + extesionFile);
            Files.write(completePath, bytesCoverImg);
            
            responseFile.setState(true);
			responseFile.setFileName(newNameFile + "." + extesionFile);
            return responseFile;

        } catch (Exception e) {
            responseFile.setState(false);
            responseFile.setErrorMessage(e.getStackTrace().toString());
            return responseFile;
        }
                
    };


    @Override
    public void deleteImageLocal(String fileNameImage, String folderPath) {

        Path pathDeleteFile = Paths.get(folderPath + "//" + fileNameImage);
        File fileToDelete = pathDeleteFile.toFile();

        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }

    };
}
