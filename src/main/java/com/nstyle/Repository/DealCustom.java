package com.nstyle.Repository;

import com.nstyle.Model.Deal;
import com.nstyle.Model.DealCountQuery;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Created by mumarm45 on 19/10/2017.
 */
@Repository
public interface DealCustom {

    @Query("SELECT new com.nstyle.Model.DealCountQuery(d.fromCurrency,count(distinct d.id)) FROM Deal " +
            "d WHERE LOWER(d.fileName) = LOWER(:fileName) group by d.fromCurrency")
    List<DealCountQuery> countByFileName(@Param("fileName") String fileName);



}
