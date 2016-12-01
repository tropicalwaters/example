package com.city.Model;

public class CityEvent {

    public static final int BRIEF = 190;

    private String date;

    private String aday;

    private String startTime;

    private String endTime;

    private String notes;

    private int eventId;

    private String eventName;

    private String category;

    private String pageUrl;

    private String picUrl;

    private String description;

    private String ageLower;

    private String ageUpper;

    private String price;

    private int companyId;

    private String company;

    private String location;

    private String address;

    private String phone;

    private int order;

    public CityEvent(String date, String aday, String startTime, String endTime, String notes, String company, String eventName, String category, String pageUrl, String picUrl, String description, String ageLower, String ageUpper, String price, String location, String address, String phone, int eventId, int companyId, int order) {
        this.date = date;
        this.aday = aday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.company = company;
        this.eventName = eventName;
        this.category = category;
        this.pageUrl = pageUrl;
        this.picUrl = picUrl;
        this.description = description;
        this.ageLower = ageLower;
        this.ageUpper = ageUpper;
        this.price = price;
        this.location = location;
        this.address = address;
        this.phone = phone;
        this.eventId = eventId;
        this.companyId = companyId;
        this.order = order;
    }

    // gets
    public int getEventId() {
        return this.eventId;
    }

    public String getCityKey() {
        return "boise";
        //return this.cityKey != null ? this.cityKey.toLowerCase(): this.cityKey;
    }

    public String getDate() {
        return this.date;
    }

    public String getDay() {
        return this.aday;
    }

    public int getOrder() {
        return this.order;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public String getCompany() {
        return this.company;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getType() {
        return this.category;
    }

    public String getAgeLower() {
        return ageLower;
    }

    public String getAgeUpper() {
        return this.ageUpper;
    }

    public String getPrice() {
        return this.price;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getDescription() {
        String res = "";
        if(this.description != null && this.description.length() > BRIEF) {
            res = this.description.substring(0, BRIEF);
            if(res.charAt(BRIEF - 1) != ' ') {
                int i = res.length();
                boolean found = false;
                while(i > 1 && found == false) {
                    if(res.charAt(i - 1) == ' ') {
                        found = true;
                    }
                    i--;
                }
                res = this.description.substring(0, i) + "...";
            }
        } else {
            res = this.description;
        }
        return res;
    }

}