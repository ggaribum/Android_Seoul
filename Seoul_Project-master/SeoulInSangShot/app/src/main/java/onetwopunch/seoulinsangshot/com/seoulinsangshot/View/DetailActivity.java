package onetwopunch.seoulinsangshot.com.seoulinsangshot.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller.Adapter_Detail;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Detail;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit.RetrofitClient;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Retrofit.RetrofitService;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.VO.GsonVO_detailView;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment.Fragment_Detail_Best;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment.Fragment_Detail_Comment;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment.Fragment_Detail_Info;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    Fragment_Detail_Info fragmentInfo;
    Fragment_Detail_Comment fragmentComment;
    Fragment_Detail_Best fragmentBest;

    Intent home;

    RecyclerView rv_detail;
    LinearLayoutManager manager;
    Adapter_Detail adapter;

    RetrofitClient client;
    RetrofitService service;
    String reqURL = "http://13.124.87.34:3000"; //디테일뷰를 위한 URL

    public static ArrayList<Model_Detail> detailMainList= new ArrayList<Model_Detail>();
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        home = new Intent(this, MainActivity.class);

        try {
            fragmentInfo = new Fragment_Detail_Info();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fragmentComment = new Fragment_Detail_Comment();
        fragmentBest = new Fragment_Detail_Best();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.detail_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.detail_tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //리사이클러뷰를 위한 리니어레이아웃 선언
        rv_detail = (RecyclerView) findViewById(R.id.rv_detail);
        manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        // 레트로핏을 통한 사진 로드함수 호출.
        loadData();

        //리사이클러뷰 어뎁터 연결

    }
    //레트로핏으로 통신하여 데이터를 detailMainList에 담아주기.
    public void loadData() {

        service = RetrofitClient.getClient(reqURL).create(RetrofitService.class);
        service.loadAnswer().enqueue(new Callback<GsonVO_detailView>() {

            @Override
            public void onResponse(Call<GsonVO_detailView> call, Response<GsonVO_detailView> response) {
                if (response.isSuccessful()) {
                    detailMainList = response.body().getDetailList();
                    //str=detailMainList.get(0).getArea();
                    //레트로핏으로 불러온 값을 프래그먼트에 뿌려줄 방법이 뭐가있을까...
                    //어뎁터를 리사이클러뷰에 연결
                    adapter = new Adapter_Detail(getApplicationContext(),detailMainList);
                    rv_detail.setLayoutManager(manager);
                    rv_detail.setAdapter(adapter);

                } else {int statusCode = response.code();}
            }
            @Override
            public void onFailure(Call<GsonVO_detailView> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_primary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(home);

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = Fragment.instantiate(getApplicationContext(), fragmentInfo.getClass().getName());
                    //fragement에 리스트전달
                    Bundle bundle= new Bundle();
                   // Log.v("Here",str);
                    bundle.putSerializable("List",detailMainList);
                    fragment.setArguments(bundle);

                    break;
                case 1:
                    fragment = Fragment.instantiate(getApplicationContext(), fragmentComment.getClass().getName());
                    break;
                case 2:
                    fragment = Fragment.instantiate(getApplicationContext(), fragmentBest.getClass().getName());
                    break;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "정보";
                case 1:
                    return "댓글";
                case 2:
                    return "뽐내기";
            }
            return null;
        }
    }
}
