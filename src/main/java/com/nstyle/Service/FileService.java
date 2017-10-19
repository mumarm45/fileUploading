package com.nstyle.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by mumarm45 on 17/10/2017.
 */
@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${fileUpload.path}")
    private String folderPath = "upload-dir/";

    private  Path rootLocation = Paths.get(folderPath);

    File store(MultipartFile multipartFile){
    try {
        init();
        Files.copy(multipartFile.getInputStream(),this.rootLocation.resolve(multipartFile.getOriginalFilename()));
        Path file = this.rootLocation.resolve(multipartFile.getOriginalFilename());
        Resource resource = new UrlResource(file.toUri());
        return  resource.getFile();
    }catch (FileAlreadyExistsException fileEx){
        throw new RuntimeException("File already exist");
    }
    catch (Exception ex){
        logger.error("Error while storing file in the upload directory");
        logger.debug("Error while storing file in the upload directory and Exception is"+ex.getMessage());
        throw new RuntimeException("FAIL!");
    }
    }

     File getFile(String fileName){
        try {
            Path file = this.rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource.getFile();
            }else{
                logger.error("File does not exist in the upload directory.");
                throw new RuntimeException("FAIL!");
            }
        }catch (Exception ex){
            logger.error("Error while getting file from the upload directory");
            logger.debug("Error while storing file from the upload directory and Exception is"+ex.getMessage());
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }

  //  @PostConstruct
    public void init() {
        try {
            if(!Files.exists(this.rootLocation))
            Files.createDirectory(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    Stream<String> createBuffer(File file){
        BufferedReader br;
        try {
             br = new BufferedReader(new FileReader(file));
             return br.lines();
        }catch (Exception ex){
            throw new RuntimeException("Error while reading BufferReader!");
        }
    }



}
