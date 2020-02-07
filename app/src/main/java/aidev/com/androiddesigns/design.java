package aidev.com.androiddesigns;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class design extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Initialiser> al;
    private Button next;
    Adapter adapter;
    String URL = "https://pascolan-config.s3.us-east-2.amazonaws.com/android/v1/prod/Category/hi/category.json";
    private ProgressBar mProgressBar;
    static int inc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        initialiser();
        dataSource();
    }

    private void dataSource() {

        MyTask tk= new MyTask();
        tk.execute(URL);


    }

    private void initialiser() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),""+adapter.getNamesMap().keySet(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        al = new ArrayList<Initialiser>();
    }

    private class MyTask extends AsyncTask<String, Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(20);

        }


        @Override
        protected void onPostExecute(Void androidAdapter) {

            mProgressBar.setProgress(100);
            mProgressBar.setVisibility(View.GONE);

        }

        @Override
        protected Void doInBackground(String... strings) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    mProgressBar.setProgress(80);


                    try {

                        JSONArray baseJsonResponse = new JSONArray(response);


                        for(int i = 0;i<baseJsonResponse.length();i++){

                            JSONObject jsonObject = baseJsonResponse.getJSONObject(i);

                            String name = "";
                            try{
                                name = jsonObject.getString("a");
                            }
                            catch (Exception e){
                                name = "other"+inc++;
                            }

                            Initialiser homeInitialiser = new Initialiser(name,jsonObject.getString("p"));
                            al.add(homeInitialiser);

                        }


                    } catch (JSONException e) {

                        Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
                    }




                    adapter = new Adapter(design.this,al);
                    recyclerView.setAdapter(adapter);

                }
            }

                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_SHORT).show();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);



            return null;
        }



    }

}
