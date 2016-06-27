package IOHelper.Volley;

import android.content.Context;

import org.json.JSONObject;


public abstract class HttpHelperRequest implements IHttpExecute {
    protected abstract void onResponseListener(JSONObject jsonResponse);
    public abstract String getTAG();
    public void cancel(Context context) {
        VolleyHelper.getInstance(context).cancelRequest(getTAG());
    }




}
