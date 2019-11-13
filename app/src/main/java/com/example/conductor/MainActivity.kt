package com.example.conductor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.conductor.controllers.PagerController
import com.example.conductor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, binding.mainContainer, savedInstanceState)
//        if (!router.hasRootController()) {
//            router.setRoot(RouterTransaction.with(HomeController()))
//        }

        router.pushController(
            RouterTransaction.with(PagerController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
