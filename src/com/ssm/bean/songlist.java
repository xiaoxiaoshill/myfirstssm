package com.ssm.bean;

import java.util.Date;

public class songlist {
    private Integer listid;

    private Integer userid;

    private String slistname;

    private Integer sjurisdiction;

    private Date sctime;

    private String sdescribe;

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSlistname() {
        return slistname;
    }

    public void setSlistname(String slistname) {
        this.slistname = slistname;
    }

    public Integer getSjurisdiction() {
        return sjurisdiction;
    }

    public void setSjurisdiction(Integer sjurisdiction) {
        this.sjurisdiction = sjurisdiction;
    }

    public Date getSctime() {
        return sctime;
    }

    public void setSctime(Date sctime) {
        this.sctime = sctime;
    }

    public String getSdescribe() {
        return sdescribe;
    }

    public void setSdescribe(String sdescribe) {
        this.sdescribe = sdescribe;
    }
}