package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-09.
 */
@Root(name ="list")
public class DTOBestEating_List {

    @Element(required = false)
    private String adres;
    @Element(required = false)
    private String adstrd;
    @Element(required = false)
    private String bizcnd;
    @Element(required = false)
    private String bsnmNm;
    @Element(required = false)
    private String dataContent;
    @Element(required = false)
    private String dataSid;
    @Element(required = false)
    private String dataTitle;
    @Element(required = false)
    private String fileCnt;
    @Element(required = false)
    private String la;
    @Element(required = false)
    private String lo;
    @Element(required = false)
    private String menu;
    @Element(required = false)
    private String regDate;
    @Element (required = false)
    private String telNo;

    public String getAdres() {
        return adres;
    }

    public String getAdstrd() {
        return adstrd;
    }

    public String getBizcnd() {
        return bizcnd;
    }

    public String getBsnmNm() {
        return bsnmNm;
    }

    public String getDataContent() {
        return dataContent;
    }

    public String getDataSid() {
        return dataSid;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getFileCnt() {
        return fileCnt;
    }

    public String getLa() {
        return la;
    }

    public String getLo() {
        return lo;
    }

    public String getMenu() {
        return menu;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getTelNo() {
        return telNo;
    }
}
