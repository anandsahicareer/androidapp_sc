package com.bangalore.sahicareer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bangalore.sahicareer.utils.Globalvariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Rb_Edit_Resume_Details_Page extends AppCompatActivity implements View.OnClickListener {

    EditText edit_cnt_first_name,edit_cnt_mobile_no,edit_cnt_emailid,edit_cnt_address,edit_cnt_city,
             edit_cnt_state,edit_cnt_country,edit_cnt_pincode;  //contact details edittexts
    EditText edit_summary;                                      // summary eidtext


    Button btn_cnt_clear,btn_cnt_submit,buttonLoadImage;                        //contact details buttons
    Button btn_summary_clear,btn_summary_submit;                //summary buttons

    TextView tv_summary_count;


    String str_cnt_fname,str_cnt_lname,str_cnt_mobileno,str_cnt_emailid,str_cnt_address,str_cnt_city,str_cnt_state,str_cnt_country,
            str_cnt_pincode;

    String str_summary;



    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private String TAG = Rb_Edit_Resume_Details_Page.class.getSimpleName();
    Dialog progree_dialog;

    private int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo;
    String ba1;
    private static int RESULT_LOAD_IMAGE = 1;

    private static final String AWS_KEY = "AKIAIAUOKNSL466FGEGQ";
    private static final String AWS_SECRET = "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";
    private static final String AWS_BUCKET = "wow-wow";

    Button selectButton;
    ImageView mImage;
    ProgressDialog pd;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        progree_dialog = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.remove_border_for_progress_dialog, null);
        progree_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progree_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        progree_dialog.setContentView(view);
        //*** Contact Details Intializations ***///

        if(Globalvariables.selected_edit_section_field.equals("contact_details")) {
            setContentView(R.layout.rb_section_edit_contact_details);
            ab.setTitle("Contact Details");
            
            edit_cnt_first_name=(EditText)findViewById(R.id.et_cntdetails_first_name);
            edit_cnt_mobile_no=(EditText)findViewById(R.id.et_cntdetails_mobileno);
            edit_cnt_emailid=(EditText)findViewById(R.id.et_cntdetails_emailid);
            edit_cnt_address=(EditText)findViewById(R.id.et_cntdetails_address);
            edit_cnt_city=(EditText)findViewById(R.id.et_cntdetails_city);
            edit_cnt_state=(EditText)findViewById(R.id.et_cntdetails_state);
            edit_cnt_country=(EditText)findViewById(R.id.et_cntdetails_country);
            edit_cnt_pincode=(EditText)findViewById(R.id.et_cntdetails_pincode);

            btn_cnt_clear=(Button)findViewById(R.id.bt_cntdetails_clear);
            btn_cnt_clear.setOnClickListener(this);
            btn_cnt_submit=(Button)findViewById(R.id.bt_cntdetails_submit);
            btn_cnt_submit.setOnClickListener(this);

            edit_cnt_mobile_no.setText(Globalvariables.mobileno);
            edit_cnt_emailid.setText(Globalvariables.email);
            imageView = (ImageView) findViewById(R.id.imgView);
            buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
            buttonLoadImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    SelectImage();
                }
            });

        }

        //*** Summary Details Intializations ***///

        else if(Globalvariables.selected_edit_section_field.equals("summary")){
            setContentView(R.layout.rb_section_edit_summary);
            ab.setTitle("Summary");
            edit_summary=(EditText)findViewById(R.id.et_summary);

            btn_summary_clear=(Button)findViewById(R.id.bt_summary_clear);
            btn_summary_clear.setOnClickListener(this);
            btn_summary_submit=(Button)findViewById(R.id.bt_summary_submit);
            btn_summary_submit.setOnClickListener(this);

            tv_summary_count=(TextView)findViewById(R.id.tv_summary_count);
            //edit_summary.setText(Globalvariables.rb_summary);
            edit_summary.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s)
                {
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {}
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                tv_summary_count.setText(String.valueOf(s.length()+"/500"));
                }
        });

        }

    }


    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        /*if(Globalvariables.selected_edit_section_field.equals("contact_details")) {}
        else if(Globalvariables.selected_edit_section_field.equals("summary")) {
            Globalvariables.rb_summary = edit_summary.getText().toString();
        }*/
        Rb_Edit_Resume_Details_Page.this.overridePendingTransition(0,0);
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_cntdetails_clear:
                edit_cnt_first_name.getText().clear();
                edit_cnt_mobile_no.getText().clear();
                edit_cnt_emailid.getText().clear();
                edit_cnt_address.getText().clear();
                edit_cnt_city.getText().clear();
                edit_cnt_state.getText().clear();
                edit_cnt_country.getText().clear();
                edit_cnt_pincode.getText().clear();
                break;

            case R.id.bt_cntdetails_submit:
            submit_contactdetails();
                break;

            case R.id.bt_summary_clear:
                edit_summary.getText().clear();
                break;

            case R.id.bt_summary_submit:
                submit_summary();
                break;

                default:
                    break;
        }

    }

    public void submit_contactdetails() {
        str_cnt_fname=edit_cnt_first_name.getText().toString();
        str_cnt_mobileno=edit_cnt_mobile_no.getText().toString();
        str_cnt_emailid=edit_cnt_emailid.getText().toString();
        str_cnt_address=edit_cnt_address.getText().toString();
        str_cnt_city=edit_cnt_city.getText().toString();
        str_cnt_state=edit_cnt_state.getText().toString();
        str_cnt_country=edit_cnt_country.getText().toString();
        str_cnt_pincode=edit_cnt_pincode.getText().toString();
        if(str_cnt_fname.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Name is Required.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            /*v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_mobileno.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Mobile No. is required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
           /* v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();

        }
        else if(str_cnt_mobileno.length()<10){
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Mobile No.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
           /* v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_emailid.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Email is required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
           /* v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(!str_cnt_emailid.matches(emailPattern)){
            Toast toast = Toast.makeText(getApplicationContext()," Invalid Email Id", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
            /*tv.setTextColor(Color.RED);
            tv.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_city.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "City is Required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
           /* v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_state.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "State is Required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            /*v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_country.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Country is Required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            /*v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else if(str_cnt_pincode.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Pin Code is Required.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            /*v1.setTextColor(Color.RED);
            v1.setTextSize(14);*/
            toast.show();
        }
        else{
            //upload();
            //String uploadImage = getStringImage(bitmap);
            progree_dialog.show();
            //new SubmitContactDetailsAPI().execute();
            /*Toast toast = Toast.makeText(getApplicationContext(),uploadImage, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setTextColor(Color.RED);
            tv.setTextSize(14);
            toast.show();*/
            //finish();
        }

    }


    public void submit_summary() {
        str_summary=edit_summary.getText().toString();
        if(str_summary.equals("")){
            /*edit_summary.setError("Enter Summary");
            edit_summary.requestFocus();
            return;*/
            Toast toast = Toast.makeText(getApplicationContext(), "Nothing to Save", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v1 = (TextView) toast.getView().findViewById(android.R.id.message);
            v1.setTextColor(Color.RED);
            v1.setTextSize(14);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
            /*tv.setTextColor(Color.RED);
            tv.setTextSize(14);*/
            toast.show();
            finish();
        }
    }

    public void onBackPressed() {
       /* if(Globalvariables.selected_edit_section_field.equals("contact_details")) {}
        else if(Globalvariables.selected_edit_section_field.equals("summary")) {
            Globalvariables.rb_summary = edit_summary.getText().toString();
        }*/
        Rb_Edit_Resume_Details_Page.this.overridePendingTransition(0,0);
        finish();
    }

    public class SubmitContactDetailsAPI extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://www.sahicareer.com/resume-builder/submit-contactdetails"); // here is your URL path

                ArrayList<String> contact = new ArrayList<String>();




               /* for (int i = 0; i < contact.size(); i++) {
                    try {
                        JSONcontacts.put("Count:" + String.valueOf(i + 1), contact.get(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/

                JSONObject EverythingJSON = new JSONObject();

                JSONObject JSONsoemthing = new JSONObject();
                EverythingJSON.put("something", JSONsoemthing);
                JSONsoemthing.put("dummy", "mobileApp");
                JSONsoemthing.put("dummy1", "android");


                JSONObject Jsoncontacts = new JSONObject();
                Jsoncontacts.put("device_type", "mobileApp");
                Jsoncontacts.put("user_id", Globalvariables.user_id);
                Jsoncontacts.put("templateId", "7895");
                Jsoncontacts.put("fullname", str_cnt_fname);
                Jsoncontacts.put("phone", str_cnt_mobileno);
                Jsoncontacts.put("email", str_cnt_emailid);
                Jsoncontacts.put("address", str_cnt_address);
                Jsoncontacts.put("city", str_cnt_city);
                Jsoncontacts.put("state", str_cnt_state);
                Jsoncontacts.put("country", str_cnt_country);
                Jsoncontacts.put("pincode", str_cnt_pincode);

                EverythingJSON.put("contact", Jsoncontacts);
                EverythingJSON.put("education", JSONsoemthing);

                Log.e("params",Jsoncontacts.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(EverythingJSON));

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


                if(status.equals("True")){

                    progree_dialog.dismiss();
                   /*Intent myIntent = new Intent(Rb_Edit_Resume_Details_Page.this, Resume_EditTemplateSections.class);
                   startActivity(myIntent);*/
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    /*TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);*/
                    toast.show();
                }
                else{
                    progree_dialog.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    /*TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(14);*/
                    toast.show();

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

    /*public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }*/
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            selectedImage = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                //displaying selected image to imageview

                Bitmap converetdImage = getResizedBitmap(bitmap, 100,100);
                imageView.setImageBitmap(converetdImage);
                //calling the method uploadBitmap to upload image

               /* ByteArrayOutputStream baos = new ByteArrayOutputStream();     //Encode image send it to server like string
                converetdImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);*/


                //uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));*/

        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void upload() {
        // Image location URL
        Log.e("path", "----------------" + picturePath);

        // Image
        Bitmap bm = BitmapFactory.decodeFile(picturePath);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        //ba1 = Base64.encodeBytes(ba);
        String encImage =Base64.encodeToString(ba, Base64.DEFAULT);
        String aa=encImage;

        Log.e("base64", "-----" + ba1);

        // Upload image to server
        //new uploadToServer().execute();
    }

    private class CatFactsActivity extends AsyncTask<Object, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Object... params) {
            JSONObject jsonResponse = null;
            int responseCode;

            try {
                URL requestUrl = new URL("http://catfacts-api.appspot.com/api/facts");
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestProperty("Accept-Encoding", "identity");
                connection.connect();
                responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Status Code: " + responseCode);
                } else {
                    InputStream stream = connection.getInputStream();
                    Reader reader = new InputStreamReader(stream);
                    int contentLength = connection.getContentLength();
                    if (contentLength > 0) {
                        char[] buffer = new char[contentLength ];
                        reader.read(buffer);
                        Log.d(TAG, String.valueOf(buffer));
                        jsonResponse = new JSONObject(String.valueOf(buffer));
                    } else {
                        String jsonData = isToString(stream);
                        jsonResponse = new JSONObject(jsonData);
                    }

                    Log.d(TAG, jsonResponse.toString(2));
                    connection.disconnect();
                }

            }
            catch (Exception e) {
                Log.e(TAG, "Exception: ", e);
            }


            return jsonResponse;
        }

        protected void onPostExecute(JSONObject data) {
            JSONObject mFactJSON = data;
        }

        protected String isToString(InputStream is) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[65536];

            try {
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
            } catch (IOException e) {
                Log.e(TAG, "Exception caught: ", e);
            }

            return buffer.toString();
        }
    }

    private void SelectImage() {
        final CharSequence[] options = {"Capture image", "Select from gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Rb_Edit_Resume_Details_Page.this);
        builder.setTitle("Select");
        builder.setCancelable(true);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
              if (options[item].equals("Capture image")) {

                      Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                      startActivityForResult(cameraIntent, 1);

              } else if (options[item].equals("Select from gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
              }
            }
        });
        builder.show();
    }


}
