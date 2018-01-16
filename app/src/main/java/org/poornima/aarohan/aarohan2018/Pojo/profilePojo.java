package org.poornima.aarohan.aarohan2018.Pojo;

/**
 * Created by BURN on 1/5/2018.
 */

public class profilePojo {
    private String name,mailid,rid,college,mobileno;

    public profilePojo(String name, String mailid, String rid, String college, String mobileno) {
        this.name = name;
        this.mailid = mailid;
        this.rid = rid;
        this.college = college;
        this.mobileno = mobileno;
    }

    public String getName() {
        return name;
    }

    public String getMailid() {
        return mailid;
    }

    public String getRid() {
        return rid;
    }

    public String getCollege() {
        return college;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
