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
import android.widget.TextView;

import java.util.ArrayList;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Detail;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.MainActivity;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.SelectActivity;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.View.TipActivity;

/**
 * Created by kwakgee on 2017. 9. 17..
 */

public class Fragment_Detail_Info extends Fragment {

    FloatingActionButton fb;//지도보기 버튼
    FloatingActionButton fb2;//팁 다이얼로그 버튼
    TextView infoTv;
    ArrayList<Model_Detail> detailMainList;
    String area;
    String subway;
    String bus;
    String smartph;

    double x;//위도
    double y;//경도

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);
        //detailMainList=new ArrayList<Model_Detail>();
        //detailMainList=(ArrayList<Model_Detail>)getArguments().get("List");
       // area=detailMainList.get(0).getArea();

        //지역명 textView에 달기
        infoTv=(TextView)rootView.findViewById(R.id.infoTextView);
       // infoTv.setText(area);

        fb=(FloatingActionButton)rootView.findViewById(R.id.fab);
        fb2=(FloatingActionButton)rootView.findViewById(R.id.fab2);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0,0?q=47.6,-122.3(우리집)");
                Intent it = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), TipActivity.class);
                startActivity(intent);

            }
        });
        return rootView;

    }
}
