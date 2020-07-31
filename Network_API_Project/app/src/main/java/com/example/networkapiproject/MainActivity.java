package com.example.networkapiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.networkapiproject.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = binding.etSearchTerm.getText().toString();
                if (TextUtils.isEmpty(search)) return;
                URL searchurl = NetworkUtil.buildSearchUrl(search);
                new GithubQueryTask().execute(searchurl);
            }
        });
    }


    public class GithubQueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchURL = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtil.getResponseFromHttp(searchURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if(githubSearchResults != null && !githubSearchResults.equals("")) {
                DisplayRepo(githubSearchResults);
            }
        }
    }

    private void DisplayRepo(String json) {
        List<GithubRepository> repositories = NetworkUtil.parseGithubRepo(json);
        StringBuilder sb = new StringBuilder();
        for(GithubRepository repository : repositories) {
            sb.append("Id: ").append(repository.getId())
                    .append("\n")
                    .append("Name: " ).append(repository.getName())
                    .append("\n")
                    .append("Description: ").append(repository.getDescription())
                    .append("\n\n");
        }
        binding.tvOutput.setText(sb.toString());
    }

}