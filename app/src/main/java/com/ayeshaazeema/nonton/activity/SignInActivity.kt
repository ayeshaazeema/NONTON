package com.ayeshaazeema.nonton.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ayeshaazeema.nonton.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun getLaunchService(from: Context) = Intent(from, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()

        tv_forgot.setOnClickListener(this)
        tv_sign_up_sign_in.setOnClickListener(this)
        btn_sign_in.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.tv_forgot -> startActivity(ForgotPasswordActivity.getLaunchService(this))
            R.id.tv_sign_in_sign_up -> startActivity(
                SignUpActivity.getLaunchService(
                    this
                )
            )
            R.id.btn_sign_in -> loginEmailPass()
        }
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(MainActivity.getLaunchService(this))
        }
    }

    private fun loginEmailPass() {
        val email = et_email_sign_in.text.toString()
        val password = et_pass_sign_in.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Insert Complete Data", Toast.LENGTH_SHORT).show()

        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Sign In Success", Toast.LENGTH_SHORT).show()
                    startActivity(MainActivity.getLaunchService(this))
                    return@addOnCompleteListener
                } else {
                    Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                val progress = ProgressDialog(this,
                    R.style.Theme_AppCompat_Light_Dialog
                )
                progress.hide()
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
            }
    }
}