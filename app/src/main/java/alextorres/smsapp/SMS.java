package alextorres.smsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends Activity {

    private Button btnSendSMS;
    private EditText txtPhoneNo;
    private EditText txtMessage;

    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);


        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
                Toast.makeText(SMS.this, "MessageSent", Toast.LENGTH_SHORT);
                }
        });

    }

    public void sendSMS()
    {
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(txtPhoneNo.getText().toString(), null, txtMessage.getText().toString(), null, null);
            Toast.makeText(getApplicationContext(), "message sent", Toast.LENGTH_LONG).show();
        }

        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "message failed", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toMessages(View view){
        Intent intent = new Intent(this, SmsRecieve.class);
        startActivity(intent);
    }
}
