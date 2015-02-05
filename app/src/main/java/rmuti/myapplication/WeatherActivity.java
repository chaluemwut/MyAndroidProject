package rmuti.myapplication;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class WeatherActivity extends ActionBarActivity {

    private final String api_call = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button but = (Button)findViewById(R.id.query);
        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("test","Click....");
                EditText qText = (EditText)findViewById(R.id.q_text);
                String qMessage = qText.getText().toString();
                try {
                    URL url = new URL(api_call+qMessage);
                    Scanner sc = new Scanner(url.openStream());
                    StringBuffer buf = new StringBuffer();
                    while(sc.hasNext()){
                        buf.append(sc.next());
                    }


                    JSONObject jsonObject = new JSONObject(buf.toString());
                    JSONObject sysObj = jsonObject.getJSONObject("sys");
                    String country = sysObj.getString("country");

                    TextView countryText = (TextView)findViewById(R.id.country_text);
                    countryText.setText(country);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
