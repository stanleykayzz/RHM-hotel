package rhm.com.converter;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
/**
 * Created by stanley on 04/09/2017.
 */

public class GetServerData {
    private StringRequest stringRequest;
    private static RequestQueue requestQueue;

    public static void updateReloadClientConnection(Context c, String token) {
        requestQueue = Volley.newRequestQueue(c);
        StringRequest request = new StringRequest(Request.Method.GET, "https://residencedeshautsdemenaye.fr/api/client/reloadToken?token="+token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }
}
