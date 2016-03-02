package com.tty.retrofit_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tty.retrofit_okhttp.adapter.RepoAdapter;
import com.tty.retrofit_okhttp.api.GithubService;
import com.tty.retrofit_okhttp.model.Repo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.github.com";
    private List<Repo> mRepos = new ArrayList<>();
    private RepoAdapter mAdapter;
    private EditText mInputView;
    private Button mBtnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.repos_listView);

        mAdapter = new RepoAdapter(mRepos, MainActivity.this);
        listView.setAdapter(mAdapter);

        mInputView = (EditText) findViewById(R.id.et_name);
        mBtnGet = (Button) findViewById(R.id.btnGet);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final GithubService service = retrofit.create(GithubService.class);

        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.listRepos(mInputView.getText().toString().trim()).enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        if (response.isSuccess()) {
                            mRepos.clear();
                            mRepos.addAll(response.body());
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {

                    }
                });
            }
        });

    }
}
