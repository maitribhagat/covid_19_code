package com.example.e_commerce.ui.main.covid


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentRecyclerviewBinding
import com.example.e_commerce.databinding.OrderAdapterBinding
import com.example.e_commerce.databinding.SeeonmapFragmentBinding
import com.example.e_commerce.ui.main.base.CommonGenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_custom_toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class SeeOnMapFragment : Fragment() {

    private lateinit var binding: SeeonmapFragmentBinding
    lateinit var mDialog : Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.seeonmap_fragment, container, false)
        activity!!.txt_toolbar_title.text = "See On Map"

        mDialog = Dialog(activity!!)

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDialog.window!!.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog.setCancelable(false)
        mDialog.setContentView(R.layout.layout_progress_dialog)
        mDialog.show()


        binding.mapWebView.settings.javaScriptEnabled = true
        binding.mapWebView.webViewClient = MyBrowser()
        binding.mapWebView.zoomIn()
        binding.mapWebView.zoomOut()

        // Set web view chrome client
        binding.mapWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress > 90) {
                    mDialog.dismiss()
                }
            }
        }

        binding.mapWebView.settings.domStorageEnabled = true
        binding.mapWebView.settings.loadsImagesAutomatically = true
        binding.mapWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding.mapWebView.loadUrl("https://www.google.com/covid19-map/")


        return binding.root

    }


    private inner class MyBrowser : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            mDialog.show()

            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {

            return true
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            mDialog.dismiss()
        }
    }
}
