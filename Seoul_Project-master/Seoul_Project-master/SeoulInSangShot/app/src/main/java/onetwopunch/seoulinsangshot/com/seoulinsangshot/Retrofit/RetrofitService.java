package onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_detailView;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 301 on 2017-09-17.
 */

public interface RetrofitService {
    @GET("/ImageEx")
    Call<GsonVO_detailView> loadAnswer();

}
