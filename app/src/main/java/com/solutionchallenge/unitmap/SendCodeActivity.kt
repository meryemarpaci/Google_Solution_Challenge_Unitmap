package com.solutionchallenge.unitmap

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.core.View
import com.solutionchallenge.unitmap.databinding.ActivitySendCodeBinding
import java.util.concurrent.TimeUnit

class SendCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendCodeBinding
    private lateinit var OTP: String
    private lateinit var auth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_code)
        binding = ActivitySendCodeBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()


        OTP = intent.getStringExtra("OTP").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        phoneNumber = intent.getStringExtra("phoneNumber")!!

        binding.buttonResendCode.setOnClickListener {
            resendVerificationCode()

        }

    }

    fun buttonJoinUs(view: View){
        startActivity(Intent(this,HomeActivity::class.java))

        val typedOTP = binding.editTextVerificate.text.toString()

        if (typedOTP.isNotEmpty()) {
            if (typedOTP.length == 6) {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    OTP, typedOTP
                )
                signInWithPhoneAuthCredential(credential)

            } else {
                Toast.makeText(this, "Please enter correct code", Toast.LENGTH_SHORT).show()

            }
        } else {
            Toast.makeText(this, "Please enter code", Toast.LENGTH_SHORT).show()
        }

    }

    private fun resendVerificationCode() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            Log.w(ContentValues.TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Log.d("TAG", "onVerificationFailed : ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
                Log.d("TAG", "onVerificationFailed : ${e.toString()}")
            }


        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            //Save verification ID and resending token so we can use them later
            OTP = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    sendToMain()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d(
                        ContentValues.TAG,
                        "signing phone auth credential : ${task.exception.toString()}"
                    )
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun sendToMain() {
        startActivity(Intent(this, HomeFragment::class.java))
    }

}