package com.example.e_commerce.ui.main.covid


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentRecyclerviewBinding
import com.example.e_commerce.ui.main.dashboard.adapter.DashboardCategoryAdapter
import kotlinx.android.synthetic.main.layout_custom_toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class FaqFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    lateinit var mDialog: Dialog

    private lateinit var viewModel: FaqViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(FaqViewModel::class.java)
        activity!!.txt_toolbar_title.text = "FAQ"

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

        viewModel.getFaq()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            binding.commonRecyclerview.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.commonRecyclerview.adapter =
                DashboardCategoryAdapter(it[0].category.toMutableList(), activity)
        })

        return binding.root
    }
}
