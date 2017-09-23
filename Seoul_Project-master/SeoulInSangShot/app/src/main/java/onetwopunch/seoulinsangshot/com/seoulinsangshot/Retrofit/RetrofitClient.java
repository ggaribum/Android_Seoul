package onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 301 on 2017-09-17.
 */

public class RetrofitClient {
    private static Retrofit retrofit =null;

    public static Retrofit getClient(String URL)
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
