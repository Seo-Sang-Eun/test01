package codersit.co.kr.jejugo.dao;


import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by P200 on 2017-06-03.
 */

public abstract class DAOClass {

    protected ICallback iCallback;
    public void setICallbackListener(ICallback iCallback)
    {
        this.iCallback = iCallback;
    }

    public abstract void getData();


}
