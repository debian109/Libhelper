package IOHelper.RequestHttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * A very easy library class to get response from Server using Volley, OKHTTP, Android default libraries.
 * <br><br>
 * 			In this, we have two constructors for 2 method types. <br>
 * 						1. POST<br>
 * 						2. GET<br><br>
 * 
 * 				Use constructor with 5 parameters for POST.<br>
 * 				Use constructor with 4 parameters for GET.
 * 
 *  Required jar files : volley.jar, OKhttp.jar 
 * 
 * @author Sreenu Krishnamaneni
 *
 */

@SuppressWarnings("deprecation")
public class ServerHelper {

	public static final int TYPE_NORMAL = 1;
	public static final int TYPE_VOLLEY = 2;
	public static final int TYPE_OKHTTP = 3;

	private Context context;
	private ProgressDialog mDialog;
	private String progressMessage;
	private String url;
	private int type;
	private boolean isPOST;
	private Map<String, String> params;


	/**
	 * This is the method used for POST request.
	 * @param context - Current activity context.
	 * @param url - URL of the target.
	 * @param params - Parameters in the form of HashMap<Key, Value>.
	 * @param progressMessage - Progress message to be shown.
	 * @param type - Type could be TYPE_NORMAL or TYPE_VOLLEY or TYPE_NORMAL
	 * @author Sreenu Krishnamaneni
	 * 
	 */
	public ServerHelper(Context context, String url, Map<String, String> params, String progressMessage, int type){
		// TODO Auto-generated constructor stub
		this.context = context;
		this.url = url;
		this.progressMessage = progressMessage;
		this.params = params;
		this.type = type;
		isPOST = true;

		if(progressMessage!=null&&progressMessage.trim().length()>1){
			showProgress(this.progressMessage);
		}

		if(isNetworkAvailable(context)){
			initiateRequest();
		}else{
			dismissProgress();
			showToast("Internet is required");
		}
	}

	/**
	 * This is the method used for GET request.
	 * @param context - Current activity context.
	 * @param url - URL of the target.
	 * @param progressMessage - Progress message to be shown.
	 * @param type - Type could be TYPE_NORMAL or TYPE_VOLLEY or TYPE_OKHTTP
	 * @author Sreenu Krishnamaneni
	 * 
	 */
	public ServerHelper(Context context, String url, String progressMessage, int type){
		// TODO Auto-generated constructor stub
		this.context = context;
		this.url = url;
		this.progressMessage = progressMessage;
		this.type = type;
		isPOST = false;

		if(progressMessage!=null&&progressMessage.trim().length()>1){
			showProgress(this.progressMessage);
		}

		if(isNetworkAvailable(context)){
			initiateRequest();
		}else{
			dismissProgress();
			showToast("Internet is required");
		}
	}

	private void initiateRequest() {
		// TODO Auto-generated method stub
		switch (type) {
		case TYPE_NORMAL:
			requestNormal();
			break;

		case TYPE_VOLLEY: 
			if(isPOST)
				requestVolleyPost();
			else
				requestVolleyGet();
			break;

		default:
			break;
		}
	}

	private void requestNormal(){
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>(){
			
			@Override
			protected String doInBackground(Void... pa) {
				// TODO Auto-generated method stub
				String response = "";
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				if(isPOST){
					for (String key : params.keySet()) {
						nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
					}
				}
				if(url.startsWith("https://"))
					response = sendHttpsRequest(nameValuePairs);
				else
					response = sendHttpRequest(nameValuePairs);
				return response;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				dismissProgress();
				handleResponse(result);
			};

		}.execute();
	}
	
	private String sendHttpRequest(List<NameValuePair> nameValuePairs) {
		// TODO Auto-generated method stub
		String inputLine;
		String response = "";
		try{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);

			URL url1 = new URL(url);
			HttpURLConnection request = (HttpURLConnection) url1.openConnection();

			request.setUseCaches(false);
			request.setDoOutput(true);
			request.setDoInput(true);

			request.setRequestMethod("POST");
			OutputStream post = request.getOutputStream();
			entity.writeTo(post);
			post.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response += inputLine;
			}
			post.close();
			in.close();
		} catch (Exception e) {
			handleError(e);
		}

		return response;
	}

	private String sendHttpsRequest(List<NameValuePair> nameValuePairs){
		// TODO Auto-generated method stub
		String inputLine;
		String response = "";
		try{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);

			URL url1 = new URL(url);
			HttpsURLConnection request = (HttpsURLConnection) url1.openConnection();

			request.setUseCaches(false);
			request.setDoOutput(true);
			request.setDoInput(true);

			request.setRequestMethod("POST");
			OutputStream post = request.getOutputStream();
			entity.writeTo(post);
			post.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response += inputLine;
			}
			post.close();
			in.close();
		} catch (Exception e) {
			handleError(e);
		}

		return response;
	}

	private void requestVolleyGet() {
		// TODO Auto-generated method stub
		RequestQueue mRequestQueue = Volley.newRequestQueue(context);

		StringRequest mRequest = new StringRequest(Method.GET, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				dismissProgress();
				handleResponse(response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				dismissProgress();
				handleError(error);
			}
		});
		mRequest.setShouldCache(true);
		mRequestQueue.add(mRequest);
	}

	private void requestVolleyPost() {
		// TODO Auto-generated method stub
		RequestQueue mRequestQueue = Volley.newRequestQueue(context);

		StringRequest mRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				dismissProgress();
				handleResponse(response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				dismissProgress();
				handleError(error);
			}
		}){
			@Override
			public Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Log.e("Params", params.toString());
				return params;
			}
		};

		mRequest.setShouldCache(true);
		mRequestQueue.add(mRequest);
	}

	/**
	 * Method to be overridden to handle the error.
	 * @author Sreenu Krishnamaneni
	 * 
	 */
	protected void handleError(Exception error){};

	/**
	 * Method to be overridden to handle the response.
	 * @author Sreenu Krishnamaneni
	 * 
	 */
	protected void handleResponse(String response){};

	public void showProgress(String message){
		// TODO Auto-generated method stub
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(message);
		mDialog.show();
	}

	public void showToast(String msg){
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public void dismissProgress(){
		// TODO Auto-generated method stub
		if(mDialog!=null&&mDialog.isShowing())
			mDialog.dismiss();
	}
	


	private static int TYPE_WIFI = 1;
	private static int TYPE_MOBILE = 2;
	private static int TYPE_NOT_CONNECTED = 0;

	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int getConnectivityStatus(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
				return TYPE_WIFI;

			if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				return TYPE_MOBILE;
		} 
		return TYPE_NOT_CONNECTED;
	}

	public static String getConnectivityStatusString(Context context) {

		int conn = getConnectivityStatus(context);
		String status = null;
		if (conn == TYPE_WIFI) {
			status = "Wifi enabled";
		} else if (conn == TYPE_MOBILE) {
			status = "Mobile data enabled";
		} else if (conn == TYPE_NOT_CONNECTED) {
			status = "Not connected to Internet";
		}
		return status;
	}

	public static String mNewtWorkState = "No network available";
	public static String mTimeOutConnection = "Connection timed out Please Try again later";

}
