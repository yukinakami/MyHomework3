package com.example.myhomework3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class MainActivity2 extends AppCompatActivity implements Runnable
{
    private static final String TAG = "Net";
    Handler handler;
    Button button1;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1 = findViewById(R.id.btn1);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        Intent intent = getIntent();
        String Bv = intent.getStringExtra("url");
        text1.setText(Bv);

        handler = new Handler(Looper.myLooper()){
            public void handleMessage(@NonNull Message msg1)
            {
                //处理返回
                if(msg1.what==5){
                    String str = (String) msg1.obj;
                    Log.i(TAG,"handleMessage: str="+str);
                    text2.setText(str);
                }
                super.handleMessage(msg1);
            }
        };


        Log.i(TAG, "onCreat: start Thread");
        Thread t = new Thread(this);
        t.start();

        /*Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            //run......

        });
        t3.start();*/
    }




    @Override
    public void run() {
        Log.i(TAG,"run:线程已启动");

        //获取网络数据

        String html1 = "";
        try{
            /* url = new URL("https://www.bilibili.com/video/BV1E14y1D7GR/?spm_id_from=333.1007.tianma.1-1-1.click&vd_source=f9482fd80b9fd99a17edfcd84eedee4e");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            //输入流转换为字符串对象
            html1 = inputStream2String(in);
            Log.i(TAG,"run:html=" + html1); */

            Document doc = Jsoup.connect("https://www.bilibili.com/video/BV1HE411b7nj").get();
            Log.i(TAG,"doc="+doc);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //发送消息
        Message msg1 = handler.obtainMessage(5,html1);
        handler.sendMessage(msg1);
    }

    @SuppressLint("SuspiciousIndentation")
    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"utf-8");
        while(true)
        {
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(MainActivity2.this,SearchActivity.class);
        startActivity(intent);
    }

}