package com.nstyle.Service;

import com.nstyle.Model.Deal;
import com.nstyle.Model.InvalidDeal;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Stream;


/**
 * Created by mumarm45 on 17/10/2017.
 */
@Service
public class UploadFile {

    @Value("${fileUpload.separator}")
    private String separator;

    private FileService fileService;
    private DealService dealService;
    private EntityManager entityManager;
    private DealCheck dealCheck;



    private final Logger logger = LoggerFactory.getLogger(UploadFile.class);

    @Autowired
    UploadFile(FileService fileService,DealService dealService,EntityManager entityManager,DealCheck dealCheck){
        this.dealService=dealService;
        this.dealCheck=dealCheck;
        this.fileService=fileService;
        this.entityManager = entityManager;
    }


    @Transactional
    public Stream<String> fileUpload(MultipartFile multipartFile) {


        if (dealCheck.checkFileExist(multipartFile.getOriginalFilename())){
            throw  new RuntimeException("File Already uploaded");
        }
        //Storing file in the upload directory
        File file = fileService.store(multipartFile);
        //Getting file from the upload directory
        // File file = fileService.getFile(multipartFile.getOriginalFilename());

        Stream<String> lines  = fileService.createBuffer(file);

        iterateLines(lines,multipartFile.getOriginalFilename());
        //delete File after upload records
        fileService.deleteAll();

        return lines;

    }



    Boolean iterateLines(Stream<String> lines, String fileName){
        Session session = entityManager.unwrap(Session.class);
        final int[] count = {0};

        lines.forEach((line) -> {
            dealService.insertRecords(line,count[0]++,fileName,session);
        });
        dealService.getCount(fileName).forEach(System.out::println);
        return true;
    }


}
