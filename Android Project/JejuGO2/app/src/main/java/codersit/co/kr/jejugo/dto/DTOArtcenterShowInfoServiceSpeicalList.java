package codersit.co.kr.jejugo.dto;

/**
 * Created by P200 on 2017-06-12.
 */

public class DTOArtcenterShowInfoServiceSpeicalList {
    String p_IMG;
    String p_NM;
    String p_START_YMD;
    String p_END_YMD;

    String p_IMG2;
    String p_NM2;
    String p_START_YMD2;
    String p_END_YMD2;


    public DTOArtcenterShowInfoServiceSpeicalList(String p_IMG, String p_NM, String p_START_YMD, String p_END_YMD,
                                                  String p_IMG2, String p_NM2, String p_START_YMD2, String p_END_YMD2) {
        this.p_IMG = p_IMG;
        this.p_NM = p_NM;
        this.p_START_YMD = p_START_YMD;
        this.p_END_YMD = p_END_YMD;

        this.p_IMG2 = p_IMG2;
        this.p_NM2 = p_NM2;
        this.p_START_YMD2 = p_START_YMD2;
        this.p_END_YMD2 = p_END_YMD2;
    }

    public String getP_IMG() {
        return p_IMG;
    }

    public String getP_NM() {
        return p_NM;
    }

    public String getP_START_YMD() {
        return p_START_YMD;
    }

    public String getP_END_YMD() {
        return p_END_YMD;
    }

    public String getP_IMG2() {
        return p_IMG2;
    }

    public String getP_NM2() {
        return p_NM2;
    }

    public String getP_START_YMD2() {
        return p_START_YMD2;
    }

    public String getP_END_YMD2() {
        return p_END_YMD2;
    }
}
