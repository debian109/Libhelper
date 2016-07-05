package namtran.helperutil;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import SystemUtil.TelephonyInfo;
import namtran.helperutil.BasicActivity.BaseActivity;

/**
 * Created by Nam Tran on 04-Mar-16.
 */
public class GetImeiPhone extends BaseActivity {
    @Override
    protected Fragment initFragment() {
        return null;
    }

    @Override
    protected View initContentView() {
        return getView(R.layout.getimei_layout);
    }

    TextView txtGetImei1;
    TextView txtGetImei2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtGetImei1 = (TextView) findViewById(R.id.txtGetImei1);
        txtGetImei2 = (TextView) findViewById(R.id.txtGetImei2);

        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);

        String imsiSIM1 = telephonyInfo.getImsiSIM1();
        String imsiSIM2 = telephonyInfo.getImsiSIM2();

        boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
        boolean isSIM2Ready = telephonyInfo.isSIM2Ready();

        boolean isDualSIM = telephonyInfo.isDualSIM();
        txtGetImei1.setText(" IME1 : " + imsiSIM1 + "\n" +
                " IME2 : " + imsiSIM2 + "\n" +
                " IS DUAL SIM : " + isDualSIM + "\n" +
                " IS SIM1 READY : " + isSIM1Ready + "\n" +
                " IS SIM2 READY : " + isSIM2Ready + "\n");

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        /*txtGetImei2.setText("Single or Dula Sim "+manager.getPhoneCount() + "/" + "Defualt device ID "+manager.getDeviceId()
                + "/" + "Single 1 "+manager.getDeviceId(0) + "/" + "Single 2 " + manager.getDeviceId(1) );*/
        txtGetImei2.setText("" + manager.getLine1Number());
    }
}
