package com.orangecat.android.newsreportapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xwang17 on 11/26/15.
 */
public class Logger {



public static void makeRequest(JSONObject params) throws Exception
    {
        new LoggerAsyncTask().execute(params);

    }

 public static JSONObject logEvent (String eventName) throws JSONException {
     JSONObject json = new JSONObject();
     JSONArray arr = new JSONArray();
     JSONObject member = new JSONObject();
     member.put("value","S2Fma2E=");
     arr.put(member);
     try {
         json.put("records",arr);
     } catch (JSONException e) {
         e.printStackTrace();
     }
     return json;
 }

}
