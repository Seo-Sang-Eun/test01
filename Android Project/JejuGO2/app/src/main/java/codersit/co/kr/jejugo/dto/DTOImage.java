package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;


/**
 * Created by P200 on 2017-06-05.
 */
@Root(name = "rss")
public class DTOImage {

    @Attribute(required = false)
    String version;

    @Path("channel")
    @Element(required = false)
    String title;

    @Path("channel")
    @Element(required = false)
    String link;

    @Path("channel")
    @Element(required = false)
    String description;

    @Path("channel")
    @Element(required = false)
    String lastBuildDate;

    @Path("channel")
    @Element(required = false)
    String total;

    @Path("channel")
    @Element(required = false)
    String start;

    @Path("channel")
    @Element(required = false)
    String display;

    @Path("channel")
    @ElementList (required = false,entry = "item", inline = true)
    ArrayList<DTOImage_Item> items;

    public ArrayList<DTOImage_Item> getItem() {
        return items;
    }
}
