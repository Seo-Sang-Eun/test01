package com.example.p200.getinfotest.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-05.
 */

@Root(name = "list")
public class DTOFestivalInquiryService_List {
    @Element(required = false)
    private String edate;
    @Element(required = false)
    private String host;
    @Element(required = false)
    private String host1;
    @Element(required = false)
    private String info;
    @Element(required = false)
    private String location;
    @Element(required = false)
    private String sdate;
    @Element(required = false)
    private String seq;
    @Element(required = false)
    private String title;



    @Element(required = false)
    private String tel;

    public String getEdate() {
        return edate;
    }

    public String getHost() {
        return host;
    }

    public String getHost1() {
        return host1;
    }

    public String getInfo() {
        return info;
    }

    public String getLocation() {
        return location;
    }

    public String getSdate() {
        return sdate;
    }

    public String getSeq() {
        return seq;
    }

    public String getTitle() {
        return title;
    }
    public String getTel() {
        return tel;
    }
}
