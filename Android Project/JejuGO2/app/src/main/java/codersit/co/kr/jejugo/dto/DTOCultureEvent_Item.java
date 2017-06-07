package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-05.
 */
@Root(name = "item")
public class DTOCultureEvent_Item {
    @Element(required = false)
    private String IChair;
    @Element(required = false)
    private String IContents;
    @Element(required = false)
    private String ICtime;
    @Element(required = false)
    private String IHelp;
    @Element(required = false)
    private String ILocation;
    @Element(required = false)
    private String IPeriodText;
    @Element(required = false)
    private String ISubject;
    @Element(required = false)
    private String MType;
    @Element(required = false)
    private String PAdddate;
    @Element(required = false)
    private String PSn;

    public String getIChair() {
        return IChair;
    }

    public String getIContents() {
        return IContents;
    }

    public String getICtime() {
        return ICtime;
    }

    public String getIHelp() {
        return IHelp;
    }

    public String getILocation() {
        return ILocation;
    }

    public String getIPeriodText() {
        return IPeriodText;
    }

    public String getISubject() {
        return ISubject;
    }

    public String getMType() {
        return MType;
    }

    public String getPAdddate() {
        return PAdddate;
    }

    public String getPSn() {
        return PSn;
    }
}
