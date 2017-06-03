package com.example.p200.getinfotest;

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
