package com.nareshit.registration;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.nareshit.registration.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String url="http://androindian.com/apps/example_app/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);


        binding.Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name",binding.Name.getText().toString().trim());
                    jsonObject.put("mobile",binding.Mobile.getText().toString().trim());
                    jsonObject.put("email",binding.Email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.Pass.getText().toString().trim());
                    jsonObject.put("baction","register_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //2
                RegisterUser registerUser=new RegisterUser();
                registerUser.execute(jsonObject.toString());
            }
        });
    }

    private class RegisterUser extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Content Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... param) {
            JSONObject object=Jsonfunctions.RegData(url,param[0]);
            Log.i("Result",object.toString());
            return object.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try {
                JSONObject object=new JSONObject(s.toString());

                String res1=object.getString("response");

                if(res1.equalsIgnoreCase("failed")){
                    String res2=object.getString("user");
                    Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();


                }else if(res1.equalsIgnoreCase("success")){
                    String res2=object.getString("user");
                    Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();

                }else {
                    String res2=object.getString("user");
                    Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /*@Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }*/
    }
}
