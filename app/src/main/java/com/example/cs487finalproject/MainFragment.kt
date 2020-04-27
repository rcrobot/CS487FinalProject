package com.example.cs487finalproject

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_main.*
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var queue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.memberLoginButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.newMemberButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.guestButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonGetSpaces).setOnClickListener(this)
        queue = Volley.newRequestQueue(activity?.applicationContext)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.memberLoginButton -> navController.navigate(R.id.action_mainFragment_to_loginFragment)
            R.id.newMemberButton -> navController.navigate(R.id.action_mainFragment_to_newMemberFragment)
            R.id.guestButton -> navController.navigate(R.id.action_mainFragment_to_reserveSpotFragment)
            R.id.buttonGetSpaces -> {
                val url = "https://noahquanrud.com/parking/lot-info?lot-id=1"
                lateinit var str: String
                val request = StringRequest(
                    Request.Method.GET,
                    url,
                    Response.Listener { response ->
                        str = "The following parking spots are available: \n" + response.substring(response.indexOf('[')+1,response.indexOf(']'))
                        textView.text = str
                    },
                    Response.ErrorListener {}
                )
                queue.add(request)
            }
        }
    }
}
