package com.blocksolid.retrofittutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blocksolid.retrofittutorial.api.GitHubClient;
import com.blocksolid.retrofittutorial.api.ServiceGenerator;
import com.blocksolid.retrofittutorial.model.GitHubUser;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    Button searchBtn;
    TextView responseText;
    EditText editText;
    ProgressBar progressBar;
    GitHubClient gitHubClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a very simple REST adapter which points the GitHub API endpoint.
        gitHubClient = ServiceGenerator.createService(GitHubClient.class);

        searchBtn = (Button) findViewById(R.id.main_btn_lookup);
        responseText = (TextView) findViewById(R.id.main_text_response);
        editText = (EditText) findViewById(R.id.main_edit_username);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForUser();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchForUser();
                }
                return handled;
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

    public void searchForUser() {
        String user = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        //Make a request to get a response
        //Get json object from GitHub server to the POJO/model class

        final Call<GitHubUser> call = gitHubClient.getFeed(user);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Response<GitHubUser> response) {
                //Display successful response results
                GitHubUser gitModel = response.body();
                if (gitModel != null) {
                    responseText.setText(getString(R.string.main_response_text,
                            gitModel.getName(),
                            gitModel.getBlog(),
                            gitModel.getCompany()));
                } else {
                    responseText.setText("");
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.main_error_text),
                            Toast.LENGTH_SHORT).show();

                }
                //Hide progressbar when done
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                // Display error message if the request fails
                responseText.setText(""); //Error needs to be handled properly
                //Hide progressbar when done
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
