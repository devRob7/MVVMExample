package com.lazyandroid.mvvmexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.lazyandroid.mvvmexample.databinding.ActivityMainBinding
import com.lazyandroid.mvvmexample.viewmodel.ClientViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ClientViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClientViewModel::class.java)
        setContentView(binding.root)
    }
}


fun replaceFragment(activity: FragmentActivity,newFragment:Fragment){
    val fm = activity.supportFragmentManager
    val t =fm.beginTransaction()
    t.replace(R.id.mainFragment,newFragment)
    t.commit()
}



//val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//val navController = findNavController(R.id.nav_host_fragment)
//// Passing each menu ID as a set of Ids because each
//// menu should be considered as top level destinations.
//val appBarConfiguration = AppBarConfiguration(setOf(
//    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//setupActionBarWithNavController(navController, appBarConfiguration)
//navView.setupWithNavController(navController)