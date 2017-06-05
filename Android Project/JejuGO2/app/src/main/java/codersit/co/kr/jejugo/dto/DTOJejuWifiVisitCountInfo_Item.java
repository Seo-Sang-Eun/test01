package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by P200 on 2017-06-03.
 */

@Root(name = "item")
public class DTOJejuWifiVisitCountInfo_Item
{
    @Element
    private String visit_date;
    @Element
    private String ap_id;
    @Element
    private String address;
    @Element
    private String ap_group;
    @Element
    private String visit_count;

    public String getVisit_date() {
        return visit_date;
    }

    public String getAp_id() {
        return ap_id;
    }

    public String getAddress() {
        return address;
    }

    public String getAp_group() {
        return ap_group;
    }

    public String getVisit_count() {
        return visit_count;
    }
}
