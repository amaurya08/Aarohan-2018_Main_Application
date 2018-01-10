package org.poornima.aarohan.aarohan2017.Pojo;

/**
 * Created by kuldeep on 09-01-2018.
 */

public class eventPojo {
    private String event_name,event_category,event_participation_category,event_type,event_detail,event_location,event_date,event_time,
            co_name,co_email,co_contact_no,event_map_coordinates_long,event_map_coordinates_latt,event_image_location;

    public eventPojo(String event_name, String event_category, String event_participation_category, String event_type, String event_detail, String event_location, String event_date, String event_time, String co_name, String co_email, String co_contact_no, String event_map_coordinates_long, String event_map_coordinates_latt, String event_image_location) {
        this.event_name = event_name;
        this.event_category = event_category;
        this.event_participation_category = event_participation_category;
        this.event_type = event_type;
        this.event_detail = event_detail;
        this.event_location = event_location;
        this.event_date = event_date;
        this.event_time = event_time;
        this.co_name = co_name;
        this.co_email = co_email;
        this.co_contact_no = co_contact_no;
        this.event_map_coordinates_long = event_map_coordinates_long;
        this.event_map_coordinates_latt = event_map_coordinates_latt;
        this.event_image_location = event_image_location;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_category() {
        return event_category;
    }

    public String getEvent_participation_category() {
        return event_participation_category;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getEvent_detail() {
        return event_detail;
    }

    public String getEvent_location() {
        return event_location;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public String getCo_name() {
        return co_name;
    }

    public String getCo_email() {
        return co_email;
    }

    public String getCo_contact_no() {
        return co_contact_no;
    }

    public String getEvent_map_coordinates_long() {
        return event_map_coordinates_long;
    }

    public String getEvent_map_coordinates_latt() {
        return event_map_coordinates_latt;
    }

    public String getEvent_image_location() {
        return event_image_location;
    }
}
