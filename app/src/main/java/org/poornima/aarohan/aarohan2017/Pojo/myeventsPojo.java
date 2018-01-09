package org.poornima.aarohan.aarohan2017.Pojo;

/**
 * Created by Bhoomika on 08-01-2018.
 */

public class myeventsPojo {
    private String eventname,eventtime,eventdate,maplongi,maplati;

    public myeventsPojo(String eventname, String eventtime, String eventdate, String maplongi, String maplati) {
        this.eventname = eventname;
        this.eventtime = eventtime;
        this.eventdate = eventdate;
        this.maplongi = maplongi;
        this.maplati = maplati;
    }

    public String getEventname() {
        return eventname;
    }

    public String getEventtime() {
        return eventtime;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getMaplongi() {
        return maplongi;
    }

    public String getMaplati() {
        return maplati;
    }
}
