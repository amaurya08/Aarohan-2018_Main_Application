package org.poornima.aarohan.aarohan2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutAarohanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_aarohan);
        String htmlText="%s";
        String mydata="<html><body style=\"text-align:justify\" >An operating system is a program that manages the computer\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        hardware. It also provides a basis forapplication programs and acts as an intermediary\n" +
                "        between the computer user and the computer hardware. An amazing aspect ofoperating systems\n" +
                "        is how varied they are in accomplishing these tasks. Mainframe operating systems are designed primarily\n" +
                "        tooptimize utilization of hardware. Personal computer (PC) operating systems support complex games,\n" +
                "        business applications, andeverything in between. Operating systems for handheld computers are designed t\n" +
                "        o provide an environment in which a user caneasily interface with the computer to execute programs. Thus,\n" +
                "        some operating systems are designed to be convenient, othersto be efficient, and others some combination of the two.\n" +
                "        Before we can explore the details of computer system operation, weneed to know something about system structure. We begi\n" +
                "        n by discussing the basic functions of system startup, I/0, and storage.We also describe the basic computer architecture\n" +
                "        that makes it possible to write a functional operating system. Because anoperating system is large and complex, it must be\n" +
                "        created piece by piece. Each of these pieces should be a well-delineatedportion of the system, with c<body></style></html>";
        WebView webView=findViewById(R.id.about);
        webView.loadData(String.format(htmlText,mydata),"text/html","utf-8");
    }
}
