package techoffice.com.smsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private Button sendSmsBtn;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PackageManager pm = this.getPackageManager();

        if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            Log.i(this.getClass().toString(), "Supported");
        } else {
            Log.i(this.getClass().toString(), "Not Supported");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.textView);
        sendSmsBtn = (Button) this.findViewById(R.id.sendSmsBtn);
        sendSmsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED ){
                    Log.i(this.getClass().toString(), "Permission granted");
                }else{
                    Log.i(this.getClass().toString(), "Permission denyed");
                }


                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        1);


                textView.setText("Updated by Send Sms Btn");
                Log.i(MainActivity.class.toString(), "Send Sms Btn");
                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("96221582", null, "Testing SMS Message", null, null);
                }catch(Exception e){
                    Log.e(this.getClass().toString(), e.getMessage(), e);
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i(this.getClass().toString(), "add  permission");

    }


}
