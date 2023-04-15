package com.example.orderingsystem;

public class NotificationData {

    private Integer notif_id;
    private Integer customer_id;
    private String notif_subject;
    private String notif_context;
    private String notif_date;

    public NotificationData(Integer notif_id, Integer customer_id,String notif_subject, String notif_context, String notif_date) {
        this.notif_id = notif_id;
        this.customer_id = customer_id;
        this.notif_subject = notif_subject;
        this.notif_context = notif_context;
        this.notif_date = notif_date;
    }

    public Integer getNotif_id() {
        return notif_id;
    }

    public void setNotif_id(Integer notif_id) {
        this.notif_id = notif_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getNotif_context() {
        return notif_context;
    }

    public void setNotif_context(String notif_message) {
        this.notif_context = notif_message;
    }

    public String getNotif_subject() {
        return notif_subject;
    }

    public void setNotif_subject(String notif_subject) {
        this.notif_subject = notif_subject;
    }

    public String getNotif_date() {
        return notif_date;
    }

    public void setNotif_date(String notif_date) {
        this.notif_date = notif_date;
    }

}
