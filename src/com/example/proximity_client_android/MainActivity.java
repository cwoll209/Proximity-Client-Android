package com.example.proximity_client_android;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

	Socket clientSocket;
	ObjectOutputStream objectstream = null;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.startbutton);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Thread t = new Thread(new ClientThread());
				Log.d("ClientActivity", "Received IP");
				t.start();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class ClientThread implements Runnable {

		private static final String tag = "Client";

		public ClientThread() {

		}

		@Override
		public void run() {
			System.out.println("trying to start client");
			try {
				clientSocket = new Socket("192.42.21.111", 12345);
				System.out.println("Received IP");
				objectstream = new ObjectOutputStream(clientSocket.getOutputStream());
			} catch (UnknownHostException e) {
				System.out.println("UnknownHost");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			}

			System.out.println("client started");

		}
	}

}
