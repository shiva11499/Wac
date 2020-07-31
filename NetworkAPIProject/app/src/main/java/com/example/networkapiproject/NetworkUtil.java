package com.example.networkapiproject;

import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUtil {
    private static final String GITHUB_BASE_URL = "https://api.github.com";
    private static final String GITHUB_SEARCH = "search";
    private static final String GITHUB_REPOSITORY = "repositories";

    private static final String PARAM_QUERY = "q";

    private NetworkUtil() {}

//    https://api.github.com/search/repositories?q=java

    public static URL buildSearchUrl(String query) {
        Uri buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendPath(GITHUB_SEARCH)
                .appendPath(GITHUB_REPOSITORY)
                .appendQueryParameter(PARAM_QUERY,query)
                .build();
        URL myurl = null;
        try {
            myurl = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return myurl;
    }


    public static String getResponseFromHttp(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream input = urlConnection.getInputStream();
            Scanner scanner = new Scanner(input);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static List<GithubRepository> parseGithubRepo(String repoJson) {
        List<GithubRepository> repositories = new ArrayList<>();
        if(TextUtils.isEmpty(repoJson)) return repositories;
        try {
            JSONObject root = new JSONObject(repoJson);
            JSONArray reposArray = root.getJSONArray("items");
            for(int i = 0; i < reposArray.length(); i++) {
                JSONObject repository = reposArray.getJSONObject(i);
                Integer id = repository.getInt("id");
                String name = repository.getString("name");
                String description = repository.getString("description");
                repositories.add(new GithubRepository(id,name,description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repositories;
    }

}


