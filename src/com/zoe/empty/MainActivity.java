package com.zoe.empty;

import java.io.File;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {
	public static final String BAIDU = "http://www.baidu.com/";

	public static final String JSON_TEST = "http://echo.jsontest.com/key/value/one/two";

	public static final String POST_TEST = "http://ave.bolyartech.com/params.php";

	public static final String GSON_TEST = "http://validate.jsontest.com/?json={'key':'value'}";

	private Button enter;
	private TextView result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		enter = (Button) findViewById(R.id.enter);
		result = (TextView) findViewById(R.id.tv_result);

		enter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				postclick();
			}
		});
	}

	private void get(String url) {
		final long startTime = System.currentTimeMillis();
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				super.onSuccess(statusCode, headers, responseBody);
				Log.d("---", "耗时:" + (System.currentTimeMillis() - startTime)
						+ "ms");
				result.setText(statusCode + "\n" + new String(responseBody));
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				super.onFailure(statusCode, headers, responseBody, error);
				Log.d("---", "耗时:" + (System.currentTimeMillis() - startTime)
						+ "ms");
				result.setText(statusCode + "\n" + new String(responseBody));
			}
		});
	}

	public void postclick() {
		final long startTime = System.currentTimeMillis();
		try {
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			params.put("param1", "12");
			params.put("param2", "13");
			client.post(POST_TEST, params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					super.onSuccess(statusCode, headers, responseBody);
					Log.d("---", "耗时:" + (System.currentTimeMillis() - startTime)
							+ "ms");
					result.setText(statusCode + "\n" + new String(responseBody));
				}
			});

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void btnupfile(View view) {

		AsyncHttpClient client = new AsyncHttpClient();

		RequestParams params = new RequestParams();

		try {
			File file = new File("/sdcard/1.jpg");
			System.out.println(file);
			params.put("pic", file);

			client.post("http://url", params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					super.onSuccess(statusCode, headers, responseBody);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
