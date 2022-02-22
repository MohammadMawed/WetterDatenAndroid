package com.mohammadmawed.wetterdaten.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.mohammadmawed.wetterdaten.R

class SplashFragment : Fragment() {

    private lateinit var animationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({

            animationView = view.findViewById(R.id.splash)


            animationView.animate().setDuration(2000).startDelay = 500

            findNavController().navigate(R.id.action_splashFragment_to_wholeDataFragment)

        }, 5000)

        return view
    }
}