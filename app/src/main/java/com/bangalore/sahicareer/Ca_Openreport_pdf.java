package com.bangalore.sahicareer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bangalore.sahicareer.utils.Globalvariables;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ca_Openreport_pdf extends AppCompatActivity {

PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR); //will hide the title
        getSupportActionBar().show(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_ca__openreport_pdf);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainappcolor)));
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setLogo(R.drawable.com_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        pdfView=(PDFView)findViewById(R.id.pdfView);

        String test = Globalvariables.select_pdf;
        switch (test) {
            case "open_pdf_a":
                pdfView.fromAsset("ca_report_pdf_a_v1.pdf")
                        .load();
                break;
            case "open_pdf_b":
                pdfView.fromAsset("ca_report_pdf_b_v1.pdf")
                        .load();
                break;
            case "open_pdf_c":
                pdfView.fromAsset("ca_report_pdf_c_v1.pdf")
                        .load();
                break;
            case "open_pdf_d":
                pdfView.fromAsset("ca_report_pdf_d_v1.pdf")
                        .load();
                break;
            case "open_pdf_e":
                pdfView.fromAsset("ca_report_pdf_e_v1.pdf")
                        .load();
                break;
            case "open_pdf_f":
                pdfView.fromAsset("ca_report_pdf_f_v1.pdf")
                        .load();
                break;
        }
    }
}
