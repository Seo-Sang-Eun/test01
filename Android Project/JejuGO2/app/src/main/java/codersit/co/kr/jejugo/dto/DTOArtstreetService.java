package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by BooHee on 2017-06-04.
 */

@Root(name = "rfcOpenApi")
public class DTOArtstreetService {
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
    @ElementList (required = false)
    private ArrayList<DTOArtstreetService_List> data;

    public String getResultCode()
    {
        return resultCode;
    }

    public String getResultMsg()
    {
        return resultMsg;
    }

    public String getPageIndex()
    {
        return pageIndex;
    }

    public String getPageSize()
    {
        return pageSize;
    }

    public String getStartPage()
    {
        return startPage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public ArrayList<DTOArtstreetService_List> getData() {
        return data;
    }
}


