package com.nstyle.Service;

import com.nstyle.Model.Deal;
import com.nstyle.Model.InvalidDeal;
import com.nstyle.Repository.DealRepository;
import com.nstyle.Repository.InvalidDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by mumarm45 on 19/10/2017.
 */
@Service
public class DealCheck {
    @Autowired
    private DealService dealService;


    @Autowired
    private InvalidDealService invalidDealService;

    boolean checkFileExist(String fileName){
        boolean isExist = true;
        if(!dealService.checkByFileName(fileName)){
         if(!invalidDealService.checkByFileName(fileName)){
             isExist= false;
         }
        }

        return  isExist;
    }



}
