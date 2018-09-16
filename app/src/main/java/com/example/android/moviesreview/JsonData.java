package com.example.android.moviesreview;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*Json data controller*/
public class JsonData {
    final private String RESULT = "results";
    final private String MOVIE_NAME = "display_title";
    final private String LINK = "link";
    final private String ARTICLE_URL = "url";
    final private String REVIEWER = "byline";
    final private String PUBLICATION = "publication_date";
    final private String SUMMARY = "summary_short";
    final private String MPAA_KEY = "mpaa_rating";
    private JSONArray mainInfo(String json){
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        try {
            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray(RESULT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
    public String[] getNames(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] moviesNames = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                moviesNames[i] = movieInfo.optString(MOVIE_NAME);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return moviesNames;
    }
    public String[] getReviewers(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] reviewersNames = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                reviewersNames[i] = movieInfo.optString(REVIEWER);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return reviewersNames;
    }
    public String[] getUrls(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] urls = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                String holdLink = movieInfo.optString(LINK);
                JSONObject forLink = new JSONObject(holdLink);
                urls[i] = forLink.optString(ARTICLE_URL);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return urls;
    }
    public String[] getSummaries(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] summaries = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                summaries[i] = movieInfo.optString(SUMMARY);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return summaries;
    }
    public String[] getPublication(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] publications = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                publications[i] = movieInfo.optString(PUBLICATION);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return publications;
    }
    public String[] getMPAAs(String json){
        JSONArray jsonArray = mainInfo(json);
        String[] mpaa = new String[jsonArray.length()];
        try{
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject movieInfo = jsonArray.getJSONObject(i);
                mpaa[i] = movieInfo.optString(MPAA_KEY);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return mpaa;
    }
}

