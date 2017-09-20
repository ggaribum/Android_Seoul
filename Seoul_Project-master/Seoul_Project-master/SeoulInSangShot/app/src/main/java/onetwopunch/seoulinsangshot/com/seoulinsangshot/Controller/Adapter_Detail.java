package onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Detail;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;

/**
 * Created by kwakgee on 2017. 9. 17..
 */

public class Adapter_Detail extends RecyclerView.Adapter<Adapter_Detail.ViewHolder>{

    ArrayList<Model_Detail> tempArr;
    Context adapContext;
    public Adapter_Detail(Context context,ArrayList<Model_Detail> tempArr) {
        this.adapContext=context;
        this.tempArr = tempArr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          Picasso.with(adapContext).load(tempArr.get(position).getImg()).into(holder.iv_detail_main);
        /* holder.iv_detail_main.setImageBitmap(); */ // handling...

    }

    @Override
    public int getItemCount() {
        return tempArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_detail_main;

        public ViewHolder(View itemView){
            super(itemView);

            iv_detail_main = (ImageView) itemView.findViewById(R.id.iv_detail_main);
        }
    }

}
