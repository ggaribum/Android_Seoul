package onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_detailView;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_weather1;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 301 on 2017-09-17.
 */

public interface RetrofitService {
    @GET("/ImageEx")
    Call<GsonVO_detailView> loadAnswer();

    @GET("service/SecndSrtpdFrcstInfoService2/ForecastGrib")
    Call<GsonVO_weather1> loadWeather1(@Query("ServiceKey") String serviceKey, @Query("nx") String nx, @Query("ny") String ny, @Query("base_date") String base_date, @Query("base_time") String base_time, @Query("_type") String type);

}
