package com.soluciones.web.appGrupo4.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.soluciones.web.appGrupo4.utils.interfaces.IImageCloudHandler;

@Component
public class ImageCloudHandler implements IImageCloudHandler {

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public ImageCloudHandler() {
        valuesMap.put("cloud_name", "mediairypscloud");
        valuesMap.put("api_key", "668979155154929");    
        valuesMap.put("api_secret", "QyC6oa5ZYBEd4LlHAFOIliK7MSk");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Map<String, String> upload(MultipartFile fileImage) throws IOException {
        File file = convert(fileImage);
        Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    @Override
    public Map<String, String> delete(String id) throws IOException {
        Map<String, String> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile fileToConvert) throws FileNotFoundException, IOException {
        File fileC = new File(fileToConvert.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(fileC);
        fo.write(fileToConvert.getBytes());
        fo.close();

        return fileC;
    }

}
