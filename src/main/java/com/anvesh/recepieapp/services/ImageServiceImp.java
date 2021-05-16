package com.anvesh.recepieapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ImageServiceImp implements ImageService {

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Image is being Uploaded");
        log.debug("Image Uploaded");
    }
}
