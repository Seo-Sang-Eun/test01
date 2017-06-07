package com.example.p200.getinfotest.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by BooHee on 2017-06-06.
 */

@Root(name = "rfcOpenApi")
public class DTOBestEating {
    @Path("header")
    @Element
    private String resultCode;

    @Path("header")
    @Element
    private String resultMsg;

    @Path("body")
    @Element
    private String pageIndex;

    @Path("body")
    @Element
    private String pageSize;

    @Path("body")
    @Element
    private String startPage;

    @Path("body")
    @Element
    private String totalCount;

    @Path("body")
    @ElementList(required = false)
    private ArrayList<DTOBestEating_List> data;
}
