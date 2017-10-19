package com.nstyle.Service;

import com.nstyle.Model.InvalidDeal;
import com.nstyle.Repository.InvalidDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mumarm45 on 17/10/2017.
 */
@Service
public class InvalidDealService {
    private InvalidDealRepository invalidDealRepository;
    @Autowired
    InvalidDealService(InvalidDealRepository invalidDealRepository){
        this.invalidDealRepository =invalidDealRepository;
    }

    public  boolean checkByFileName(String fileName){
     List<InvalidDeal> invalidDeals = invalidDealRepository.findAllByFileName(fileName);
     return  invalidDeals.size()>=1;
    }
    public InvalidDeal create(InvalidDeal invalidDeal){
        return   invalidDealRepository.save(invalidDeal);
    }
}
