package gg.gowa.com.ltjs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();

    private TextView tvRes;
    private double r; // 半径
    private double h; // 垂高
    private double j; // 角度
    private double a; // 间距
    private double t;
    private double w; // 宽度
    private double w2; //板宽

    private EditText bj;
    private EditText cg;
    private EditText jd;
    private EditText jj;
    private EditText kd;
    private EditText gbkd;
    private EditText tj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_do).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);

        bj = findViewById(R.id.bj);
        cg = findViewById(R.id.cg);
        jd = findViewById(R.id.jd);
        jj = findViewById(R.id.jj);
        kd = findViewById(R.id.kd);
        gbkd = findViewById(R.id.gbkd);
        tj   = findViewById(R.id.tj);
        tvRes = (TextView) findViewById(R.id.tv_res);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_do:
                try {
                    r = Float.parseFloat(bj.getText().toString());
                    h = Float.parseFloat(cg.getText().toString());
                    j = Float.parseFloat(jd.getText().toString());
                    a = Float.parseFloat(jj.getText().toString());
                    w = Float.parseFloat(kd.getText().toString());
                    w2 = Float.parseFloat(gbkd.getText().toString());
                    t = Float.parseFloat(tj.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(this, "输入不对", Toast.LENGTH_SHORT).show();
                    return;
                }
                doIt();
                break;
            case R.id.btn_reset:
                bj.setText("");
                cg.setText("");
                jd.setText("");
                jj.setText("");
                kd.setText("");
                gbkd.setText("");
                tvRes.setText("");
                tj.setText("");
                break;
        }
    }

    private void doIt() {
        Log.d(TAG, "doIt: " + r);
        // 外弧长
        double s1 = (r + a) * Math.PI * j / 180;
        double s2 = (r + a + w) * Math.PI * j / 180;

        // 内外斜长
        double o1 = Math.sqrt(s1 * s1 + h * h);
        double o2 = Math.sqrt(s2 * s2 + h * h);

        // 坡度角
//        double H = Math.asin((o1 * o1 + s1 * s1 - h * h) / (2 * o1 * s1));
        double H = Math.atan(h / s1);
        double H2 = Math.atan(h / s2);

        // 要去边长度
        double l = w2 / Math.tan(H);
        double l2 = w2 / Math.tan(H2);
        double at1 = o1 / (t+1);
        double at2 = o2 / (t+1);
        tvRes.setText(String.format("内侧板：%f\n外侧板：%f\n去边长度：%f（内）,%f（外）\n等分宽度：%f（内），%f（外）",
                o1, o2, l, l2,at1,at2));
    }
}
