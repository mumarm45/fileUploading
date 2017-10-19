package com.nstyle.Repository;

import com.nstyle.Model.Deal;
import com.nstyle.Model.DealCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mumarm45 on 19/10/2017.
 */
@Repository
public interface DealCountRepository extends JpaRepository<DealCount,Long> {

}
