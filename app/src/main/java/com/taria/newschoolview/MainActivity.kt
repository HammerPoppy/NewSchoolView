package com.taria.newschoolview

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLogin = findViewById<TextView>(R.id.et_login)
        val etPassword = findViewById<TextView>(R.id.et_password)
        val bConfirm = findViewById<TextView>(R.id.b_confirm)

        bConfirm.setOnClickListener(View.OnClickListener {

            Log.i("debug", "login button pressed")

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "http://www.newschoolviewapi.somee.com/api/appdaybook"

            // Post parameters
            // Form fields and values
            val params = HashMap<String, String>()
            params["username"] = etLogin.text.toString()
            params["password"] = etPassword.text.toString()
            val jsonObject = JSONObject(params as Map<*, *>)

            Log.i("debug", "${jsonObject}")

            // Request a string response from the provided URL.
            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                { response ->
                    // Process the json
                    try {
                        Log.i("debug", "response: $response")

                        if (response.has("page")) {
                            Log.i("debug", "response has page property, starting new activity")

                            val intent = Intent(this, TableActivity::class.java).apply {
                                putExtra(EXTRA_MESSAGE, response.get("page").toString())
                            }

                            startActivity(intent)
                        }

                    } catch (e: Exception) {
                        Log.i("debug", "exception: $e, $response")
                    }

                }, {
                    // Error in request
                    Log.i("debug", "Volley error: $it, ${it.networkResponse.statusCode}")
                })

            // Add the request to the RequestQueue.
            queue.add(request)
        })

    }
}