package com.ayeshaazeema.nonton.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayeshaazeema.nonton.R
import com.ayeshaazeema.nonton.adapter.MovieAdapter
import com.ayeshaazeema.nonton.model.ResponseMovie
import com.ayeshaazeema.nonton.service.RetrofitConfig
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null

    companion object {
        fun getLaunchService(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        iv_profile_main.setOnClickListener(this)

        getMovie()

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser!!.uid)
        refUsers!!.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (p0 in snapshot.children) {
                    val photo = snapshot.child("photo").value.toString()

                    Glide.with(this@MainActivity).load(photo).into(iv_profile_main)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getMovie() {
        val apiKey = "efa3e25518b94d33a911e5c947b673ba"
        val lang = "en-US"

        val loading = ProgressDialog.show(this, "Request Data", "Loading...")
        RetrofitConfig.getInstance().getMovieData(apiKey, lang).enqueue(
            object : Callback<ResponseMovie> {

                override fun onResponse(
                    call: Call<ResponseMovie>,
                    response: Response<ResponseMovie>
                ) {
                    Log.d("Response", "Success" + response.body()?.results)
                    loading.dismiss()
                    if (response.isSuccessful) {
                        Log.e("TAG", "onResponse: ${response.body()?.results?.get(0)?.title}")
                        Toast.makeText(this@MainActivity, "Data Success!", Toast.LENGTH_SHORT)
                            .show()
                        val movieData = response.body()?.results
                        val movieAdapter = MovieAdapter(this@MainActivity, movieData)

                        rv_main.adapter = movieAdapter
                        rv_main.layoutManager = LinearLayoutManager(this@MainActivity)

                    } else {
                        Toast.makeText(this@MainActivity, "Data Failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.iv_profile_main -> startActivity(Intent(ProfileActivity.getLaunchService(this)))
        }
    }
}

