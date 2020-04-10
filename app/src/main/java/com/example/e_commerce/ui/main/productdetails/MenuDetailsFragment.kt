package com.example.e_commerce.ui.main.productdetails

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.e_commerce.R
import com.example.e_commerce.databinding.MenuDetailsFragmentBinding
import com.example.e_commerce.databinding.ViewpagerDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_custom_toolbar.*

class MenuDetailsFragment : Fragment(){

    private lateinit var binding: ViewpagerDetailsBinding
    private lateinit var viewModel: ProductDetailViewModel
    var position : String = "0"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.viewpager_details,container,false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(activity!!).get(ProductDetailViewModel::class.java)
        binding.viewmodel = viewModel

        val args =MenuDetailsFragmentArgs.fromBundle(arguments!!)
        position = args.position

        //viewModel.getImageDetails(position)

        if(position == "0"){

            Picasso.get()
                .load(R.drawable.slider_1)
                .into(binding.image)


            binding.description.text = "COVID-19 virus can be transmitted in areas with hot and humid climates\n" +
                    "\n" +
                    "From the evidence so far, the COVID-19 virus can be transmitted in ALL AREAS, including areas with hot and humid weather. Regardless of climate, adopt protective measures if you live in, or travel to an area reporting COVID-19. The best way to protect yourself against COVID-19 is by frequently cleaning your hands.\n" +
                    "Cold weather and snow CANNOT kill the new coronavirus.\n" +
                    "\n" +
                    "There is no reason to believe that cold weather can kill the new coronavirus or other diseases. The normal human body temperature remains around 36.5°C to 37°C, regardless of the external temperature or weather. The most effective way to protect yourself against the new coronavirus is by frequently cleaning your hands with alcohol-based hand rub or washing them with soap and water.Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)\n" +
                    "\n" +
                    "You can catch COVID-19, no matter how sunny or hot the weather is. Countries with hot weather have reported cases of COVID-19. To protect yourself, make sure you clean your hands frequently and thoroughly and avoid touching your eyes, mouth, and nose.  \n" +
                    "Drinking alcohol does not protect you against COVID-19 and can be dangerous\n" +
                    "\n" +
                    "Frequent or excessive alcohol consumption can increase your risk of health problems\n" +
                    "\n" +
                    "The new coronavirus CANNOT be transmitted through mosquito bites.\n" +
                    "\n" +
                    "To date there has been no information nor evidence to suggest that the new coronavirus could be transmitted by mosquitoes. The new coronavirus is a respiratory virus which spreads primarily through droplets generated when an infected person coughs or sneezes, or through droplets of saliva or discharge from the nose"

        }

        if(position == "1"){

            Picasso.get()
                .load(R.drawable.slider_1)
                .into(binding.image)


            binding.description.text = Html.fromHtml("<b><font color='black'>Corona first found in 1965.since  WHO reported that 5 patients had suffered from corona in China in 2003.\n" +
                    "Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.</font><b><br><br>\n\n" +
                    "Coronavirus disease (COVID-19) is an infectious disease caused by a new virus.\n" +
                    "The disease causes respiratory illness (like the flu) with symptoms such as a cough, fever, and in more severe cases, difficulty breathing. You can protect yourself by washing your hands frequently, avoiding touching your face, and avoiding close contact (1 meter or 3 feet) with people who are unwell.\n" +
                    " \n" +
                    "How it spreads\n" +
                    "Coronavirus disease spreads primarily through contact with an infected person when they cough or sneeze. It also spreads when a person touches a surface or object that has the virus on it, then touches their eyes, nose, or mouth.\n" +
                    "While circumstances prevent us from gathering in Seattle, we are committed to delivering virtually, to the widest possible audience, some of the content and connection you have come to expect from the top event in nutrition — brought to you this year, at no charge, by the ASN Foundation.  Keep ASN on your calendar for May 30-June 2 for the potential kick off of this ongoing virtual connection.\n" +
                    "While circumstances prevent us from gathering in Seattle, we are committed to delivering virtually, to the widest possible audience, some of the content and connection you have come to expect from the top event in nutrition — brought to you this year, at no charge, by the ASN Foundation.  Keep ASN on your calendar for May 30-June 2 for the potential kick off of this ongoing virtual connection.", Html.FROM_HTML_MODE_COMPACT)
        }

        if(position == "2"){

            Picasso.get()
                .load(R.drawable.slider_1)
                .into(binding.image)


            binding.description.text = "What is the recovery time for the coronavirus disease?\n" +
                    "\n" +
                    "Using available preliminary data, the median time from onset to clinical recovery for mild cases is approximately 2 weeks and is 3-6 weeks for patients with severe or critical disease.10 lakh  jobs,business at risk in post-corona India \n" +
                    "The worst-case scenario is for employees who don’t have a regular salary. In the tourism industry, for instance, this category includes people who either work on short-term contracts or even without them. This includes guides, of course, but also employees of parking contractors, cleaners working in shops, waiters in restaurants, suppliers of vegetables, meat and flowers to the hotels among others.Over lakhs of people face unemployment with lots of economic and business domain are faces the critical situation."
        }




       /* binding.btnBuyNow.setOnClickListener {
            val paymentActivity = Intent(context!!,PaymentActivity::class.java)
            val bundle = Bundle()
            bundle.putString("amount","100")
            bundle.putString("Currancy","IN")
            paymentActivity.putExtras(bundle)
            startActivity(paymentActivity)
        }*/

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.txt_toolbar_title.text = "Details"
    }
}