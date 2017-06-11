package codersit.co.kr.jejugo.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BooHee on 2017-06-05.
 */

@Root(name = "list")
public class DTOArtcenterShowInfoService_List {
    @Element(required = false)
    private String p_SN;
    @Element(required = false)
    private String p_NM;
    @Element(required = false)
    private String p_IMG;
    @Element(required = false)
    private String p_YN;
    @Element(required = false)
    private String p_START_YMD;
    @Element(required = false)
    private String p_END_YMD;
    @Element(required = false)
    private String p_RNUTIME;
    @Element(required = false)
    private String p_VIEW_CD;
    @Element(required = false)
    private String p_PLAY_YN;
    @Element(required = false)
    private String p_INTRO;
    @Element(required = false)
    private String p_REG_DT;
    @Element(required = false)
    private String p_ORCHESTRA_YN;
    @Element(required = false)
    private String p_ART_YN;
    @Element(required = false)
    private String p_FREE_YN;
    @Element(required = false)
    private String p_RESE_YN;

    public String getP_SN() {
        return p_SN;
    }

    public String getP_NM() {
        return p_NM;
    }

    public String getP_IMG() {
        return p_IMG;
    }

    public String getP_YN() {
        return p_YN;
    }

    public String getP_START_YMD() {
        return p_START_YMD;
    }

    public String getP_END_YMD() {
        return p_END_YMD;
    }

    public String getP_RNUTIME() {
        return p_RNUTIME;
    }

    public String getP_VIEW_CD() {
        return p_VIEW_CD;
    }

    public String getP_PLAY_YN() {
        return p_PLAY_YN;
    }

    public String getP_INTRO() {
        return p_INTRO;
    }

    public String getP_REG_DT() {
        return p_REG_DT;
    }

    public String getP_ORCHESTRA_YN() {
        return p_ORCHESTRA_YN;
    }

    public String getP_ART_YN() {
        return p_ART_YN;
    }

    public String getP_FREE_YN() {
        return p_FREE_YN;
    }

    public String getP_RESE_YN() {
        return p_RESE_YN;
    }


}
