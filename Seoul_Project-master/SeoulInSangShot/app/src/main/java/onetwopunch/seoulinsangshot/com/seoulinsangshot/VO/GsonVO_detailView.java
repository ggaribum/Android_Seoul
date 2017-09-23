package onetwopunch.seoulinsangshot.com.seoulinsangshot.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Detail;

/**
 * Created by 301 on 2017-09-17.
 */

public class GsonVO_detailView {


    @SerializedName("list")
    @Expose

    private ArrayList<Model_Detail> detailList= null;

    public ArrayList<Model_Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<Model_Detail> detailList) {
        this.detailList = detailList;
    }
}
