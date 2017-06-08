package codersit.co.kr.jejugo.dto;

/**
 * Created by P200 on 2017-06-07.
 */

public class DTOStampPlace {

    private String gpsX;
    private String gpsY;
    private String placeName;

    public DTOStampPlace(String gpsX, String gpsY, String placeName) {
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.placeName = placeName;
    }

    public String getGpsX() {
        return gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public String getPlaceName() {
        return placeName;
    }

}
