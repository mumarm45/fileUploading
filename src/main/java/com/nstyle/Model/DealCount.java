package com.nstyle.Model;

import javax.persistence.*;

/**
 * Created by mumarm45 on 19/10/2017.
 */
@Entity
public class DealCount {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

    @Column(unique = true)
    private String dealName;

    private long dealCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public long getDealCount() {
        return dealCount;
    }

    public void setDealCount(long dealCount) {
        this.dealCount = dealCount;
    }
}
