package codersit.co.kr.jejugo.dto;

/**
 * Created by BooHee on 2017-06-10.
 */

public class DTOPartnerStore {

    private String storeName;
    private String telNo;
    private String addr;

    public DTOPartnerStore(String storeName, String telNo, String addr)
    {

        this.storeName = storeName;
        this.telNo = telNo;
        this.addr = addr;
    }


    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getAddr() {
        return addr;
    }
}
