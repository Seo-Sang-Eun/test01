package com.example.p200.getinfotest.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-06.
 */

@Root(name = "list")
public class DTOBestEating_List {

    @Element
    private String adres;
    @Element
    private String adstrd;
    @Element
    private String ceo;
    @Element
    private String img_url;
    @Element
    private String introduce;
    @Element
    private String name;
    @Element (required = false)
    private String telephone;

}
