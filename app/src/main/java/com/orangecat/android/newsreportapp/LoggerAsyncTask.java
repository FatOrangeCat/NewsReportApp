package com.orangecat.android.newsreportapp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by xwang17 on 11/26/15.
 */
public class LoggerAsyncTask extends AsyncTask<JSONObject, Void, String> {


    String inString, parameterPass;
    JSONObject jobject;

    @Override
    protected String doInBackground(JSONObject... jObject) {

        parameterPass = jObject.toString();
        Log.i("doInBackground", parameterPass);
        String url_select = "http://52.91.18.100:8082/topics/test";
        HttpResponse response;
        try {

            HttpPost httpPost = new HttpPost(url_select);
            HttpClient httpClient = new DefaultHttpClient();

//            httpPost.setEntity(new StringEntity(jObject.toString(), "UTF8"));
            httpPost.setEntity(new StringEntity("{\"records\":[{\"value\":\"S2Fma2E=\"}]}"));
            httpPost.setHeader("Content-type", "application/vnd.kafka.binary.v1+json");
            response = (HttpResponse) httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            if(entity != null) {
                InputStream in = response.getEntity().getContent();
                inString = in.toString();
                Log.i("InputStream", "" + in.toString() + response.getStatusLine() + parameterPass);
            }

        } catch (UnsupportedEncodingException e1) {
//            Log.e("UnsupportedEncodingException", e1.toString());
            e1.printStackTrace();
        } catch (ClientProtocolException e2) {
            Log.e("ClientProtocolException", e2.toString());
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            Log.e("IllegalStateException", e3.toString());
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.e("IOException", e4.toString());
            e4.printStackTrace();
        }
        return parameterPass;
    }
}
