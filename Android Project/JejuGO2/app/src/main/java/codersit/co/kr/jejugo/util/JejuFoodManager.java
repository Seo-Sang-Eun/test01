package codersit.co.kr.jejugo.util;

import java.util.ArrayList;

import codersit.co.kr.jejugo.dao.DAOBestEating;
import codersit.co.kr.jejugo.dto.DTOBestEating;

/**
 * Created by BooHee on 2017-06-09.
 */

public class JejuFoodManager {

    private String LOG = "JejuFoodDataManager";

    public static DTOBestEating  staticDtoBestEating;

    public static void initData(){
        DAOBestEating daoBestEating = new DAOBestEating("1", "315");
        daoBestEating.setICallbackListener(iCallbackBestEating);

        daoBestEating.getData();
    }



    private static ICallback iCallbackBestEating = new ICallback() {
        @Override
        public void call(Object o) {
            staticDtoBestEating= (DTOBestEating) o;
        }
    };

}
