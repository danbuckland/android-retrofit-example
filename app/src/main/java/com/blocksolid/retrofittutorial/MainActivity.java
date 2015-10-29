package com.blocksolid.retrofittutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blocksolid.retrofittutorial.api.GitApi;
import com.blocksolid.retrofittutorial.model.GitModel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    Button searchBtn;
    TextView responseText;
    EditText editText;
    ProgressBar progressBar;
    String API = "https://api.github.com"; //BASE URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = (Button) findViewById(R.id.main_btn_lookup);
        responseText = (TextView) findViewById(R.id.main_text_response);
        editText = (EditText) findViewById(R.id.main_edit_username);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editText.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                //Retrofit section starts here...
                //Create an adapter for retrofit with base url
                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();

                //Create a service for adapter using the GET class
                GitApi git = restAdapter.create(GitApi.class);

                //Make a request to get a response
                //Get json object from GitHub server to the POJO/model class
                git.getFeed(user, new Callback<GitModel>() {
                    @Override
                    public void success(GitModel gitModel, Response response) {

                        //Display successful response results
                        //TODO use string resources instead
                        responseText.setText("GitHub Name: " + gitModel.getName()
                                + "\nWebsite: " + gitModel.getBlog()
                                + "\nCompany Name: " + gitModel.getCompany());

                        progressBar.setVisibility(View.INVISIBLE); //Disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // Display error message if the request fails
                        responseText.setText(error.getMessage());
                        progressBar.setVisibility(View.INVISIBLE); //Disable progressbar
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up searchBtn, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
