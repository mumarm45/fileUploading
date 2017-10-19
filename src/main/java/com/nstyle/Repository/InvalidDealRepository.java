package com.nstyle.Repository;

import com.nstyle.Model.Deal;
import com.nstyle.Model.InvalidDeal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mumarm45 on 17/10/2017.
 */
public interface InvalidDealRepository extends CrudRepository<InvalidDeal,Long> {
    List<InvalidDeal> findAllByFileName(String name);
}
