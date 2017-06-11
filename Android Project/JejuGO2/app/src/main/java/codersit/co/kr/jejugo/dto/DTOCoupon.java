package codersit.co.kr.jejugo.dto;

/**
 * Created by BooHee on 2017-06-11.
 */

public class DTOCoupon {
    private String name;
    private String startDate;
    private String endDate;
    private String couponNo;

    public DTOCoupon(String name, String startDate, String couponNo)
    {
        this.name = name;
        this.startDate = startDate;
        this.couponNo = couponNo;
        this.endDate = "발급일로부터 3일 이내";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }
}
