package IOHelper.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created by Nam Tran on 06-Oct-15.
 */
public class RequestGetHeaderResponse extends Request<Map> {

    private final Response.Listener mListener;
    private String mBody;
    private Map mCustomHeaders;

    public RequestGetHeaderResponse(int method, String url, Map customHeaders, String body, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        mCustomHeaders = customHeaders;
        mBody = body;
        mListener = listener;

        if (method == Method.POST) {
            RetryPolicy policy = new DefaultRetryPolicy(5000, 0, 5);
            setRetryPolicy(policy);
        }
    }


    @Override
     protected Response parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.headers, HttpHeaderParser.parseCacheHeaders(response));
    }


    @Override
    protected void deliverResponse(Map response) {
        mListener.onResponse(response);
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
        if (mCustomHeaders != null) {
            return mCustomHeaders;
        }
        return super.getHeaders();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mBody.getBytes();
    }
}
