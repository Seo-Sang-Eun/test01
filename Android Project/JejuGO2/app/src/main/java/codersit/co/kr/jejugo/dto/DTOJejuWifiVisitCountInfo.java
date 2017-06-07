package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by P200 on 2017-06-03.
 */


@Root(name = "response")
public class DTOJejuWifiVisitCountInfo {

    @Path("header")
    @Element
    private String resultCode;

    @Path("header")
    @Element
    private String resultMsg;

    @Path("body")
    @Element
    private String numOfRows;

    @Path("body")
    @Element
    private String pageNo;

    @Path("body")
    @Element
    private String totalCount;

    @Path("body")
    @ElementList (required=false)
    private ArrayList<DTOJejuWifiVisitCountInfo_Item> items;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public String getPageNo() {
        return pageNo;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public ArrayList<DTOJejuWifiVisitCountInfo_Item> getItems() {
        return items;
    }
}
