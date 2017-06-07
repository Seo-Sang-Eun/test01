package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by P200 on 2017-06-05.
 */
@Root(name = "result")
public class DTOGeoCode {

    @Element
    String userquery;

    @Element
    String total;

//    @Path("items")
    @ElementList(required = false)
    private ArrayList<DTOGeoCode_Item> items;

    public String getUserquery() {
        return userquery;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<DTOGeoCode_Item> getItem() {
        return items;
    }
}
