package IOHelper.Volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.namtran.basichelper.R;

/**
 * Created by Nam Tran on 23-Dec-15.
 */
public class VolleyHelper {

    private static final String TAG = "Volley";

    /**
     * context
     */
    private Context mContext;
    private RequestQueue mRequestQueue;
    private static VolleyHelper mInstance;

    /**
     * @param context
     */
    private VolleyHelper(Context context) {
        // TODO: init component
        mRequestQueue = Volley.newRequestQueue(context);
        mContext = context;
    }

    public static synchronized VolleyHelper getInstance(Context context)
    {
        if (null == mInstance)
            mInstance = new VolleyHelper(context);
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * add request message to queue
     * @param request
     * @param tag: use in case cancellable request
     * @param <T>
     */
    private <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(tag);
        mInstance.getRequestQueue().add(request);
    }

    /**
     * add request message to queue
     * @param request
     * @param <T>
     */
    private <T> void addToRequestQueue(Request<T> request) {
        mInstance.getRequestQueue().add(request);
    }

    /**
     * cancel specific requests with given tag, if null: throw exception
     * @param tag
     */
    public void cancelRequest(String tag) {
        if(null == tag)
            throw new IllegalArgumentException("did you forgot set TAG for volley request?");
        mRequestQueue.cancelAll(tag);
    }

    public static interface Method {
        int POST = Request.Method.POST;
        int GET = Request.Method.GET;
    }

    public void JsonReQuest(int method, String url,Map<String,String> headers ,Object jsonRequest, final ResponseHandler responseHandler
            , final ResponseErrorHandler errorHandler, String tag, int timeout, int retryTimes)
    {
        MakeJsonRequest makeJsonRequest = new MakeJsonRequest(
                method,
                url,
                (JSONObject) jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO: handle response from server
                        responseHandler.handleJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String msg = null;
                        /** network response == null */
                        if(volleyError.getClass().equals(NoConnectionError.class)) {
                            msg = mContext.getString(R.string.http_err_no_connection);
                            /** network content == null */
                        } else if(volleyError.getClass().equals(NetworkError.class)) {
                            msg = mContext.getString(R.string.http_err_network_error);
                            /** connection || response timeout */
                        } else if(volleyError.getClass().equals(TimeoutError.class)) {
                            msg = mContext.getString(R.string.http_err_connection_timeout);
                            /** server error (error code: 5xx) */
                        } else if(volleyError.getClass().equals(ServerError.class)) {
                            msg = mContext.getString(R.string.http_err_server_error)
                                    + volleyError.networkResponse.statusCode;
                            /** response content cannot convert to Json */
                        } else if(volleyError.getClass().equals(ParseError.class)) {
                            msg = mContext.getString(R.string.http_err_resp_content_error);
                        } else { /** shouldn't reach to this code */
                            msg = "Lỗi, mã lỗi: 000";
                        }
                        errorHandler.handleError(volleyError, msg);
                    }
                }
        );
        makeJsonRequest.setShouldCache(false);
        if (null != headers)
        {
            makeJsonRequest.setHeader(headers);
        }
        else
        {
            headers = new HashMap<>();
            headers.put("Name", "Content-Type");
            headers.put("Value", "application-json");
            makeJsonRequest.setHeader(headers);
        }

        if (timeout >0)
        {
            makeJsonRequest.setRetryPolicy(new DefaultRetryPolicy(timeout,retryTimes,0));
        }
        if (null == tag)
        {
            this.addToRequestQueue(makeJsonRequest);
        }else {
            this.addToRequestQueue(makeJsonRequest,tag);
        }
    }

    public interface ResponseHandler {
        void handleJsonResponse(JSONObject jsonObject);
    }

    public interface ResponseErrorHandler {
        void handleError(VolleyError error, String msg);
    }
}
