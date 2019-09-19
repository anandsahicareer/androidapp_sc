package com.bangalore.sahicareer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.utils.Globalvariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class EnterUserDetails extends AppCompatActivity implements View.OnClickListener {
EditText et_username,et_emailid;
Button bt_continue;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String str_username,str_useremailid;
    private String TAG = OtpVerification.class.getSimpleName();
    ProgressDialog dialogshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //enable full screen
        setContentView(R.layout.activity_enter_user_details);

        dialogshow = new ProgressDialog(EnterUserDetails.this);
        et_username=(EditText)findViewById(R.id.edit_user_name);
        et_emailid=(EditText)findViewById(R.id.edit_user_emailid);

        bt_continue=(Button)findViewById(R.id.bt_user_continue);
        bt_continue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bt_user_continue:
                str_username  = et_username.getText().toString();
                str_useremailid  = et_emailid.getText().toString();
                if(str_username.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
                    v1.setTextColor(Color.RED);
                    v1.setTextSize(14);
                    toast.show();
                }
                else if(str_useremailid.equals("")||str_useremailid.equals(null)){
                    Toast toast = Toast.makeText(getApplicationContext()," Email Id is Required", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.RED);
                    tv.setTextSize(14);
                    toast.show();
                }
                else if(!str_useremailid.matches(emailPattern)){
                    Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.RED);
                    tv.setTextSize(14);
                    toast.show();
                }
                else {

                    new SendAllUserdetailsAPI().execute();

                }
        break;
        default:
            break;
        }
    }

    public class SendAllUserdetailsAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){

            dialogshow.setMessage("Verifying details...");
            dialogshow.setCancelable(false);
            dialogshow.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialogshow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogshow.show();
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/send-userdata"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", str_username);
                postDataParams.put("mobile_number", Globalvariables.mobileno);
                postDataParams.put("device_id", Globalvariables.macaddress);
                postDataParams.put("device_type", "1");
                postDataParams.put("email", str_useremailid);


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("success");
                String message = jsonObject.getString("msg");

                if(status.equals("True"))
                {
                    String user_id = jsonObject.getString("user_id");
                    Globalvariables.user_id=user_id;
                    Intent aboutus = new Intent(EnterUserDetails.this, MainActivity.class);
                    startActivity(aboutus);
                    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);
                    toast.show();
                }

            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            dialogshow.dismiss();

        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            String aa=result.toString();

            return result.toString();

        }
    }
}
