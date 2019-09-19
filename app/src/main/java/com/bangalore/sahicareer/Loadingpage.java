package com.bangalore.sahicareer;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class Loadingpage extends AppCompatActivity {
    boolean connected = false;
   String macaddress;
    private int REQUEST_CODE = 1;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private String TAG = Loadingpage.class.getSimpleName();
    String app_version_name;
    int app_version_Code;
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //enable full screen
        setContentView(R.layout.activity_loadingpage);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            //we are connected to a network
            connected = true;

        }
        else {
            connected = false;
        }

        if(connected==true)
        {

      /*      //checking wether the permission is already granted
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){

// permission is already granted
// here you can directly access contacts
                macaddress = getMacAddr();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent myIntent = new Intent(Loadingpage.this, RegistrationPage.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }
                }, 250);

            }else{

//persmission is not granted yet
//Asking for permission
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE);

            }*/
            if(Build.VERSION.SDK_INT < 23){
                //your code here
                macaddress = getMacAddr();
                Globalvariables.macaddress=macaddress;


                        new CheckingDeviceMacaddressAPI().execute();


            }else {

                requestContactPermission();
            }

        }
        else{
            /*Toast toast = Toast.makeText(Loadingpage.this, "No Internet Connection... Please check your connection", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            v1.setTextColor(Color.BLACK);
            v1.setTextSize(15);
            toast.show();*/

            Intent assessment = new Intent(Loadingpage.this, NoInternetConnection.class);
            startActivity(assessment);
        }
    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);

                }
                return res1.toString();

            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE){

//checking if the permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

//permission is granted,do your operation
                macaddress = getMacAddr();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent myIntent = new Intent(Loadingpage.this, RegistrationPage.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }
                }, 250);

            }else{

// permission not granted
//Display your message to let the user know that he requires permission to access the app
                Toast.makeText(this,"You denied the permission Some Functions are not working...!",Toast.LENGTH_LONG).show();

                macaddress = getMacAddr();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent myIntent = new Intent(Loadingpage.this, RegistrationPage.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }
                }, 250);


            }
        }
    }*/

    private void requestContactPermission() {

        int hasContactPermission =ActivityCompat.checkSelfPermission(Loadingpage.this,Manifest.permission.RECEIVE_SMS);

        if(hasContactPermission != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(Loadingpage.this, new String[]   {Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
        }else {
            macaddress = getMacAddr();
            Globalvariables.macaddress=macaddress;


                    new CheckingDeviceMacaddressAPI().execute();

            //Toast.makeText(AddContactsActivity.this, "Contact Permission is already granted", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[]           permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                // Check if the only required permission has been granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Permission", "Contact permission has now been granted. Showing result.");
                    //Toast.makeText(this,"Contact Permission is Granted",Toast.LENGTH_SHORT).show();

                    macaddress = getMacAddr();
                    Globalvariables.macaddress=macaddress;


                    new CheckingDeviceMacaddressAPI().execute();

                } else {
                    Log.i("Permission", "Contact permission was NOT granted.");
                }
                break;
        }
    }

    public class CheckingDeviceMacaddressAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/enter-device"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("device_type", "1");
                postDataParams.put("device_id", macaddress);


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


                if(status.equals("newuser")){

                    myIntent = new Intent(Loadingpage.this, LauncherSlideScreen.class);
                    startActivity(myIntent);
                    //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                }
                else{
                    String login_status = jsonObject.getString("login_status");
                    Globalvariables.login_status=login_status;
                    String current_page = jsonObject.getString("current_page");
                    Globalvariables.current_page=current_page;
                    String user_status = jsonObject.getString("user_status");
                    Globalvariables.user_status=user_status;
                    String user_name = jsonObject.getString("username");
                    Globalvariables.username=user_name;
                    String user_id = jsonObject.getString("user_id");
                    Globalvariables.user_id=user_id;
                    String user_email=jsonObject.getString("useremail");
                    Globalvariables.email=user_email;
                    String user_mobileno=jsonObject.getString("userphone");
                    Globalvariables.mobileno=user_mobileno;


                    if(login_status.equals("0")){
                        myIntent = new Intent(Loadingpage.this, LauncherSlideScreen.class);
                        startActivity(myIntent);
                        //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }
                    else{
                        myIntent = new Intent(Loadingpage.this, MainActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }

                }

            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                //"Json parsing error: " + e.getMessage(),
                                "Might be server Down Please after sometime!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

        }
    }

    public class CheckingAppVersionAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/mobile/enter-device"); // here is your URL path



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("device_type", "1");
                postDataParams.put("device_id", macaddress);


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


                if(status.equals("newuser")){

                    myIntent = new Intent(Loadingpage.this, LauncherSlideScreen.class);
                    startActivity(myIntent);
                    //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                }
                else{
                    String login_status = jsonObject.getString("login_status");
                    Globalvariables.login_status=login_status;
                    String current_page = jsonObject.getString("current_page");
                    Globalvariables.current_page=current_page;
                    String user_status = jsonObject.getString("user_status");
                    Globalvariables.user_status=user_status;
                    String user_name = jsonObject.getString("username");
                    Globalvariables.username=user_name;
                    String user_id = jsonObject.getString("user_id");
                    Globalvariables.user_id=user_id;
                    String user_email=jsonObject.getString("useremail");
                    Globalvariables.email=user_email;
                    String user_mobileno=jsonObject.getString("userphone");
                    Globalvariables.mobileno=user_mobileno;


                    if(login_status.equals("0")){
                        myIntent = new Intent(Loadingpage.this, LauncherSlideScreen.class);
                        startActivity(myIntent);
                        //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }
                    else{
                        myIntent = new Intent(Loadingpage.this, MainActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    }

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

        }
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
