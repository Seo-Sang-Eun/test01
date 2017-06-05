package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-04.
 */

@Root(name = "list")
public class DTOArtstreetService_List {

    @Element
    private String address;
    @Element
    private String category;
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

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getCeo() {
        return ceo;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }
}
