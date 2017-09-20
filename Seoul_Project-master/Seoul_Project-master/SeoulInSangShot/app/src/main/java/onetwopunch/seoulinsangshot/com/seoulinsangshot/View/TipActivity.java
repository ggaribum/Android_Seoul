package onetwopunch.seoulinsangshot.com.seoulinsangshot.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;

public class TipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        overridePendingTransition(R.anim.activity_open_translate_from_bottom, R.anim.activity_no_animation);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_close_translate_to_bottom);
    }
}
