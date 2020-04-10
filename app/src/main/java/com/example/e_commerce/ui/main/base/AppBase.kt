package com.example.e_commerce.ui.main.base

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.e_commerce.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.razorpay.Checkout

class AppBase : Application(){
    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        Checkout.preload(applicationContext)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Firebase", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.d("Firebase", token)
                //Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })




    }

    companion object AppContext{}
    fun getAppContext(): Context {
        return appContext!!
}
}