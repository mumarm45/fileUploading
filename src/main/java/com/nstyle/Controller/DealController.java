package com.nstyle.Controller;


import com.nstyle.Model.Deal;
import com.nstyle.Service.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by mumarm45 on 17/10/2017.
 */
@RestController
@RequestMapping(value = "/deal")
public class DealController {
    private final Logger logger = LoggerFactory.getLogger(DealController.class);
    @Autowired
   private UploadFile uploadFile;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity getDeal(@RequestParam("file") MultipartFile file){

     uploadFile.fileUpload(file);
        return ResponseEntity.ok("Uploaded");

    }
}
