package com.example.e_commerce.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.MainActivityBinding
import com.example.e_commerce.ui.main.services.MyAlarmBroadcastReceiver
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_custom_toolbar.view.*
import java.util.*






class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: MainActivityBinding
    private lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var PRIVATE_MODE = 0
    private val NOTIFICATION_ON_OFF = "Notification_ON_OFF"
    lateinit var sharedPref: SharedPreferences
    var editor : SharedPreferences.Editor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.main_activity
        )
        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.actionBar.toolbar)
        toolbar = binding.actionBar.toolbar

        sharedPref = getSharedPreferences(NOTIFICATION_ON_OFF, PRIVATE_MODE)
        editor = sharedPref.edit()
        /*editor!!.putBoolean(NOTIFICATION_ON_OFF, true)
        editor!!.apply()*/

        if (sharedPref.getBoolean(NOTIFICATION_ON_OFF, false)) {
            try {
                val receiver = MyAlarmBroadcastReceiver()
                unregisterReceiver(receiver)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
            binding.navigationView.menu.findItem(R.id.notificationOnOff).actionView
                .findViewById<SwitchCompat>(R.id.on_off_switch).isChecked = false
            editor!!.putBoolean(NOTIFICATION_ON_OFF, false)
            editor!!.apply()
        } else {
            binding.navigationView.menu.findItem(R.id.notificationOnOff).actionView
                .findViewById<SwitchCompat>(R.id.on_off_switch).isChecked = true
            editor!!.putBoolean(NOTIFICATION_ON_OFF, true)
            editor!!.apply()

            val intent = Intent(this, MyAlarmBroadcastReceiver::class.java)
            val sender = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val am = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val recurring = (1*60*1000).toLong()  // in milliseconds
            am.setRepeating(
                AlarmManager.RTC,
                Calendar.getInstance().getTimeInMillis(),
                recurring,
                sender
            )

        }


        navController = Navigation.findNavController(
            this,
            R.id.my_nav_host_fragment
        )
        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.actionBar.toolbar.setNavigationIcon(R.drawable.ic_drawer_navmenu)

        /*todo navigation item click*/
        binding.navigationView.setNavigationItemSelectedListener(this)


        /*todo close drawer*/
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                binding.navigationView.setCheckedItem(R.id.nav_menu)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }


        binding.navigationView.menu.findItem(R.id.notificationOnOff).actionView
            .findViewById<SwitchCompat>(R.id.on_off_switch).setOnCheckedChangeListener { compoundButton, b ->

                if (b){
                    binding.navigationView.menu.findItem(R.id.notificationOnOff).actionView
                        .findViewById<SwitchCompat>(R.id.on_off_switch).isChecked = true
                    System.out.println("hello ON")
                    editor!!.putBoolean(NOTIFICATION_ON_OFF, true)
                    editor!!.apply()

                    val intent = Intent(this, MyAlarmBroadcastReceiver::class.java)
                    val sender = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                    val am = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val recurring = (1*60*1000).toLong()  // in milliseconds
                    am.setRepeating(
                        AlarmManager.RTC,
                        Calendar.getInstance().getTimeInMillis(),
                        recurring,
                        sender
                    )

                }else{
                    try {
                        val intent = Intent(this, MyAlarmBroadcastReceiver::class.java)
                        val sender = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                        val am = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        val recurring = (1*60*1000).toLong()  // in milliseconds
                        am.setRepeating(AlarmManager.RTC,Calendar.getInstance().getTimeInMillis(),recurring,sender)

                        am.cancel(sender)
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    }
                    binding.navigationView.menu.findItem(R.id.notificationOnOff).actionView
                        .findViewById<SwitchCompat>(R.id.on_off_switch).isChecked = false
                    System.out.println("hello OFF")
                    editor!!.putBoolean(NOTIFICATION_ON_OFF, false)
                    editor!!.apply()
                }
            }

    }


    /*todo handle navigation onclick*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

         if (item!!.getItemId() == R.id.seeOnMapFragment) {
            navController.navigate(R.id.action_dashboard_fragment_to_seeOnMapFragment)
            return true
        } else if (item!!.getItemId() == R.id.faqFragment) {
            navController.navigate(R.id.action_dashboard_fragment_to_faqFragment)
            return true
        }else if (item!!.getItemId() == R.id.shareFragment) {
             val sendIntent: Intent = Intent().apply {
                 action = Intent.ACTION_SEND
                 putExtra(Intent.EXTRA_TEXT, "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.")
                 type = "text/plain"
             }

             val shareIntent = Intent.createChooser(sendIntent, null)
             startActivity(shareIntent)
            return true
        }
        return false
    }
}
