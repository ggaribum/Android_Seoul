package onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Detail;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Weather1;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit.RetrofitClient;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit.RetrofitService;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_detailView;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_weather1;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.TipActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kwakgee on 2017. 9. 17..
 */

public class Fragment_Detail_Info extends Fragment {

    FloatingActionButton fb;//지도보기 버튼
    FloatingActionButton fb2;//팁 다이얼로그 버튼
    TextView areaTv;//지역 텍스트뷰
    TextView infoTv;//정보 텍스트뷰
    ImageView weather1;
    ImageView weather2;
    ImageView weather3;

    //디테일정보관련
    ArrayList<Model_Detail> detailMainList;
    String area;
    String subway;
    String bus;
    String smartph;

    //시간관련
    String Date[];
    String baseDateArr[];
    String baseDate;
    String baseTimeArr[];
    String baseTime;

    //레트로핏 통신관련
    static String reqURL_Info ="http://13.124.87.34:3000";
    static String reqURL_Weather = "http://newsky2.kma.go.kr/";
    String serviceKey = URLDecoder.decode("V46Xl9KDTxcFmWFQKiAsxp9XNgTkiYEbdmyBArTL5%2BIeh73QsZ%2BhPB2PS9QEx7bOhzZcaDDyqLLg1hrlD5bKEw%3D%3D", "UTF-8");
    String weather_PTY;
    String weather_SKY;

    public GsonVO_weather1 repoList;
    public static ArrayList<Model_Weather1> dataList = new ArrayList<Model_Weather1>();
    public static ArrayList<Model_Weather1> arr = new ArrayList<Model_Weather1>();


    //디코딩 예외처리
    public Fragment_Detail_Info() throws UnsupportedEncodingException {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);

        infoTv = (TextView) rootView.findViewById(R.id.infoTextView);
        areaTv = (TextView) rootView.findViewById(R.id.areaTextView);
        weather1=(ImageView)rootView.findViewById(R.id.weather1);
        weather2=(ImageView)rootView.findViewById(R.id.weather2);
        weather3=(ImageView)rootView.findViewById(R.id.weather2);


        loadDataInfo();

        fb = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fb2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0,0?q=47.6,-122.3(우리집)");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TipActivity.class);
                startActivity(intent);

            }
        });
        return rootView;

    }


    //////////사진 정보 호출///////////////////////////////////////////////////

    public void loadDataInfo()
    {
        RetrofitService service = RetrofitClient.getClient(reqURL_Info).create(RetrofitService.class);
        service.loadAnswer().enqueue(new Callback<GsonVO_detailView>() {

            @Override
            public void onResponse(Call<GsonVO_detailView> call, Response<GsonVO_detailView> response) {
                if (response.isSuccessful()) {

                    detailMainList = response.body().getDetailList();
                    ////////////////☆★☆★☆★DetailActivity랑 fragment에 어떤 파라미터가 올지 몰라서 임시로 그냥 함.☆★☆★☆★//////////////////
                    area=detailMainList.get(0).getArea();
                    smartph=detailMainList.get(0).getSmartph();
                    //detailMainList.get(0).getAppInfo();//앱정보
                    subway=detailMainList.get(0).getSubway();
                    bus=detailMainList.get(0).getBus();
                    //detailMainList.get(0).getDDareung();//따릉이정보
                    //NX=detailMainList.get(0).getNX;   //nx정보
                    //NY=detailMainList.get(0).getNY;   //ny정보
                    infoTv.setText("SmartPhone : "+smartph+"\n\n"+"AppInfo : "+"앱 정보"+"\n\n"+"TransPortaion : "+subway+" , "+bus+"\n\n"+"DDareungE : "+"따릉이 위치"+"\n\n");
                    areaTv.setText("────────"+"\n\n"+area+"\n\n"+"─────");

                    loadDataWeather1("60","127"); //실시간 날씨 정보 불러오기(초단기API)


                } else {int statusCode = response.code();}
            }
            @Override
            public void onFailure(Call<GsonVO_detailView> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    //////////////////////초단기 날씨 API 호출////////////////////////////////
    public void loadDataWeather1(String NX,String NY) {
        Retrofit client = new Retrofit.Builder().baseUrl(reqURL_Weather).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitService service = client.create(RetrofitService.class);

        //이거 굳이 안쓰고 바로 파라미터로 접근해도 됨.
        String nx=NX;
        String ny=NY;

        //현재시간 계산해서 파라미터로 넘겨줌.
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

        Date = formatDate.split(" ");
        baseDateArr=Date[0].split("/");
        baseTimeArr=Date[1].split(":");
        baseDate=baseDateArr[0]+baseDateArr[1]+baseDateArr[2];
        baseTime=baseTimeArr[0]+baseTimeArr[1];


        Call<GsonVO_weather1> call = service.loadWeather1(serviceKey, nx, ny, baseDate, baseTime, "json");
        call.enqueue(new Callback<GsonVO_weather1>() {
            @Override
            public void onResponse(Call<GsonVO_weather1> call, Response<GsonVO_weather1> response) {
                if (response.isSuccessful()) {
                    repoList = response.body();
                    dataList = repoList.getResponse().getBody().getItems().getItem();

                    for (int i = 0; i < dataList.size(); i++) {
                        String baseDate = dataList.get(i).getBaseDate();
                        String baseTime = dataList.get(i).getBaseTime();
                        String category = dataList.get(i).getCategory();
                        String nx = dataList.get(i).getNx();
                        String ny = dataList.get(i).getNy();
                        String obsrValue = dataList.get(i).getObsrValue();
                        if (category.equals("PTY")) {
                            arr.add(0, new Model_Weather1(baseDate, baseTime, category, nx, ny, obsrValue));
                        }
                        if (category.equals("SKY")) {
                            arr.add(1, new Model_Weather1(baseDate, baseTime, category, nx, ny, obsrValue));
                        }
                    }
                    switch (arr.get(0).getObsrValue())  //강수형태
                    {
                        case "0":
                            weather_PTY = "nomal"; //없음
                            break;
                        case "1":
                            weather_PTY = "rain"; //비
                            break;
                        case "2":
                            weather_PTY = "rain_snow"; //비+눈=진눈깨비
                            break;
                        case "3":
                            weather_PTY = "snow"; //눈
                    }
                    switch (arr.get(1).getObsrValue())  //하늘상태
                    {
                        case "1":
                            weather_SKY = "sunny"; //맑음
                            break;
                        case "2":
                            weather_SKY = "little_cloudy"; //구름조금
                            break;
                        case "3":
                            weather_SKY = "cloudy"; //구름많음
                            break;
                        case "4":
                            weather_SKY = "gray"; //흐림
                    }

                    //강수가 '비' 이면
                    if(weather_PTY.equals("rain"))
                    {
                        //weather1.setImageResource(R.drawable.rain);
                    }
                    //강수가 '눈' or '진눈깨비' 이면
                    else if(weather_PTY.equals("snow")||weather_PTY.equals("rain_snow"))
                    {
                        //weather1.setImageResource(R.drawable.snow);
                    }
                    //강수가 '비'도 '눈'도 '진눈깨비도 '아니고, 하늘상태가 '흐린상태' 라면 == 강수가 '없음' 이고 흐리다면
                    else if( (weather_PTY.equals("nomal"))&&(weather_SKY.equals("lttle_cloudy")||weather_SKY.equals("cloudy")||weather_SKY.equals("gray") ) )
                    {
                        //weather1.setImageResource(R.drawable.gray);
                    }
                    else if(weather_SKY.equals("sunny"))
                    {
                        //weather1.setImageResource(R.drawable.sunny);
                    }

                    //infoTv.setText("현재 강수상태 :" + weather_PTY + "\n" + "현재 하늘상태" + weather_SKY);

                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<GsonVO_weather1> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////

}

