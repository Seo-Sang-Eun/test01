package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by P200 on 2017-06-11.
 */


@Root(name = "item")
public class DTOImage_Item {

    @Element(required = false)
    String title;

    @Element(required = false)
    String link;

    @Element(required = false)
    String thumbnail;

    @Element(required = false)
    String sizeheight;

    @Element(required = false)
    String sizewidth;

    public String getLink() {
        return link;
    }

    public String getSizewidth() {
        return sizewidth;
    }

    public String getSizeheight() {
        return sizeheight;
    }
}
