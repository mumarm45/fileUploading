package com.nstyle.Repository;

import com.nstyle.Model.Deal;
import com.nstyle.Model.DealCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by mumarm45 on 17/10/2017.
 */
public interface DealRepository extends CrudRepository<Deal,Long>,DealCustom {
    List<Deal> findDealByFileName(String name);
}
