package com.nstyle.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.nstyle.Model.Deal;
import com.nstyle.Model.DealCountQuery;
import com.nstyle.Model.InvalidDeal;
import com.nstyle.Repository.DealRepository;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mumarm45 on 17/10/2017.
 */
@Service
public class DealService {
    private DealRepository dealRepository;


    @Value("${fileUpload.separator}")
    private String separator;

    private  StatelessSession statelessSession ;


    private EntityManager entityManager;

    @Autowired
    DealService(DealRepository dealRepository,EntityManager entityManager){
        this.dealRepository = dealRepository;
        this.entityManager= entityManager;
    }

    Deal create(Deal deal){
        return dealRepository.save(deal);
    }

    List<DealCountQuery> getCount(String fileName){
        System.out.println(fileName);
        return dealRepository.countByFileName(fileName);
    }
    @Transactional
    void insertRecords(String line,int count,String fileName,Session session){


        int batchSize = 50;
        //flushing and clearing the session. so, hibernate stores element is his session. clearing it

        if (count % batchSize == 0) {
            System.out.println(count);
            entityManager.flush();
            entityManager.clear();
        }

        // use comma as separator
        String[] rowList = line.split(separator);
        Deal deal = new Deal();
        try {
            if(validateRow(rowList)){
                 deal = createDealInstance(rowList, fileName);
                //dealService.create(deal);
                entityManager.merge(deal);
               // statelessSession.insert(deal);
            }
            else{
                // statelessSession.insert(new InvalidDeal(line,fileName));
                //invalidDealService.create(new InvalidDeal(line,fileName)); Taking high time 1
                entityManager.persist(new InvalidDeal(line,fileName));// taking time number 2
            }


        }
        catch (Exception innerEx) {
            // logger.info("Invalid row, now inserting the in the invalid table");
            entityManager.persist(new InvalidDeal(line,fileName));
            //statelessSession.insert(new InvalidDeal(line,fileName));
        }



    }

    boolean validateRow(String[] deal) {
        return deal.length == 4;
    }
    public  boolean checkByFileName(String fileName){
        List<Deal> deals = dealRepository.findDealByFileName(fileName);
        return  deals.size() >= 1;
    }

    Deal createDealInstance(String[] deal, String fileName) {
        Deal validDeal = new Deal();
        validDeal.setId(Integer.parseInt(deal[0]));
        validDeal.setFromCurrency(deal[1]);
        validDeal.setToCurrency(deal[2]);
        validDeal.setAmount(Long.parseLong(deal[3]));
        validDeal.setFileName(fileName);
        validDeal.setCreatedDate(new Timestamp(new Date().getTime()));
        return validDeal;
    }
}
