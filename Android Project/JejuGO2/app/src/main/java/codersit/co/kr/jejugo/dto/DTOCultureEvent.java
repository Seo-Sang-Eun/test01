package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by BooHee on 2017-06-05.
 */
@Root(name = "response")
public class DTOCultureEvent {
    @Path("header")
    @Element
    private String resultCode;

    @Path("header")
    @Element
    private String resultMsg;

    @Path("body")
    @ElementList(required = false)
    private ArrayList<DTOCultureEvent_Item> items;

    @Path("body")
    @Element
    private String numOfRows;

    @Path("body")
    @Element
    private String pageNo;

    @Path("body")
    @Element
    private String totalCount;


    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public ArrayList<DTOCultureEvent_Item> getItems() {
        return items;
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
}
