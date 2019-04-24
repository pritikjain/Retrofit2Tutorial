package com.pretty.strawberry.retrofit2tutorial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pretty.strawberry.retrofit2tutorial.adapter.RecyclerviewAdapter;
import com.pretty.strawberry.retrofit2tutorial.model.Posts;
import com.pretty.strawberry.retrofit2tutorial.network.GetDataService;
import com.pretty.strawberry.retrofit2tutorial.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerviewAdapter adapter;
    private RecyclerView recyclerView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Posts>> call = service.getAllPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong... Please try later", Toast.LENGTH_SHORT);

            }
            });
        }

        /**Method to genrate List of data using RecyclerView with custom adapter */

        private void generateDataList(List<Posts> photoList)
        {
            recyclerView = findViewById(R.id.customRecyclerView);
            adapter = new RecyclerviewAdapter(this,photoList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }



}
