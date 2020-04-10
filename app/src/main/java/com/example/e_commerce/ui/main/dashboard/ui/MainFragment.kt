package com.example.e_commerce.ui.main.dashboard.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce.R
import com.example.e_commerce.databinding.MainFragmentBinding
import com.example.e_commerce.ui.main.dashboard.adapter.DashboardCategoryAdapter
import com.example.e_commerce.ui.main.dashboard.adapter.SliderAdapter
import com.example.e_commerce.ui.main.dashboard.model.Live
import com.example.e_commerce.ui.main.dashboard.model.SliderImagesModel
import com.example.e_commerce.ui.main.dashboard.viewmodel.DashboardViewModel
import com.example.e_commerce.ui.main.listener.NavigatationInterface
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.layout_custom_toolbar.*


class MainFragment : Fragment(), NavigatationInterface {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var sliderAdapter: SliderAdapter
    var callbackListener: NavigatationInterface? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    var images = ArrayList<Int>()
    var mRootView: View? = null
    lateinit var mDialog : Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (mRootView == null) {
            binding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false
            )
            binding.lifecycleOwner = this
            mRootView = binding.root
            viewModel = ViewModelProviders.of(activity!!).get(DashboardViewModel::class.java)
            binding.viewmodel = viewModel



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



            val handler = object : SliderAdapter.ViewPagerClick{
                override fun onClick(pos: Int) {
                    val bundle = Bundle()
                    bundle.putString("position",pos.toString())
                    findNavController().navigate(R.id.menudetailsFragment,bundle)
                }
            }

            sliderAdapter = SliderAdapter(context!!,listOfImages(),handler)
            binding.viewpager.adapter = sliderAdapter
            binding.indicator.setViewPager(binding.viewpager)


            firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)

           /* binding.viewpager.setOnClickListener {
                object : View.OnClickListener {
                    override fun onClick(p0: View?) {

                        Toast.makeText(context!!, viewModel.curruntVisble.value.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }*/


            binding.viewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    viewModel.curruntVisble.value = position

                }
            })

        } else {
            val parent = mRootView as ViewGroup
            parent.removeView(mRootView)
        }



        /*todo observer*/
        viewModel.curruntVisble.observe(viewLifecycleOwner, Observer {
            binding.viewpager.currentItem = it
        })

        //get latest menu from view model
        viewModel.getDashboardMenu()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {

            mDialog.dismiss()
            //bind your UI here
//            binding.dashboardMenuRv.layoutManager = GridLayoutManager(activity, 2)
//            binding.dashboardMenuRv.adapter = DashboardMenuAdapter(it.get(0).menu.toMutableList(),activity,this!!)

            /*todo category*/
            //bind your UI here
            binding.dashboardCategoryRv.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.dashboardCategoryRv.adapter =
                DashboardCategoryAdapter(it[0].category.toMutableList(), activity)

            setLiveData(it[0].live)
        })

        return mRootView!!
    }

    private fun setLiveData(live: List<Live>) {
        binding.casedCount.text = live[0].description
        binding.deathCount.text = live[1].description
    }


    private fun listOfImages(): List<SliderImagesModel> {
        val addImages = mutableListOf<SliderImagesModel>()
        addImages.add(
            SliderImagesModel(
                "Slider 1",
                "Description One",
                R.drawable.covid_slider1
            )
        )
        addImages.add(
            SliderImagesModel(
                "Slider 2",
                "Description Two",
                R.drawable.slider12
            )
        )
        addImages.add(
            SliderImagesModel(
                "Slider 3",
                "Description Three",
                R.drawable.slider23
            )
        )
        return addImages

    }

    override fun navigateTo(position: Int, comingFrom: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Menu_onclick")
        firebaseAnalytics.logEvent("menu", bundle)
        findNavController().navigate(R.id.action_dashboard_fragment_to_menudetailsFragment)
    }

    override fun onResume() {
        super.onResume()
        activity!!.txt_toolbar_title.text = getString(R.string.app_name)
    }
}
