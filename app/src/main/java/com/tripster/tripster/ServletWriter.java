package com.tripster.tripster;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Aaron Zalewski
 */
public class ServletWriter {

    private String servletUrl="http://18.189.54.108:8080/TripsterServlet/Servlet";
    private String TAG = "SERVLET_WRITER";

    public ServletWriter(String servletUrl) {
        this.servletUrl = servletUrl;
    }

    public void writeTrackingNumber(final String trackingNumber) {
        String message = "FLIGHT_NUMBER=" + trackingNumber;
        writeToServlet(message);
    }

    public void writePhoneNumber(final String phoneNumber) {
        String message = "PHONE_NUMBER" + phoneNumber;
        writeToServlet(message);
    }

    private void writeToServlet(final String bufferedWriterMessage) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL servletURL = new URL(servletUrl);
                    Log.d(TAG, "About to connect to servlet!");
                    HttpURLConnection servletConnection = (HttpURLConnection) servletURL.openConnection();
                    servletConnection.setRequestMethod("POST");
                    servletConnection.setDoOutput(true); //not exactly sure what this does
                    servletConnection.setDoInput(true);


                    /*
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(servletConnection.getOutputStream()));
                    out.write(bufferedWriterMessage);
                    out.flush();
                    out.close();

                    // Code for receiving and updating view.
                    BufferedReader in = new BufferedReader(new InputStreamReader(servletConnection.getInputStream()));
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("response=" + response);
                    }
                    in.close();
                    */
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
