package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by P200 on 2017-06-05.
 */
@Root(name = "item")
public class DTOGeoCode_Item {

    @Element(required = false)
    String address;

    @Path("addrdetail")
    @Element(required = false)
    String country;

    @Path("addrdetail")
    @Element(required = false)
    String sido;

    @Path("addrdetail")
    @Element(required = false)
    String sigugun;

    @Path("addrdetail")
    @Element(required = false)
    String dongmyun;

    @Path("addrdetail")
    @Element(required = false)
    String rest;

    @Element(required = false)
    String isRoadAddress;

    @Path("point")
    @Element(required = false)
    String x;

    @Path("point")
    @Element(required = false)
    String y;


    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getSido() {
        return sido;
    }

    public String getSigugun() {
        return sigugun;
    }

    public String getDongmyun() {
        return dongmyun;
    }

    public String getRest() {
        return rest;
    }

    public String getIsRoadAddress() {
        return isRoadAddress;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
