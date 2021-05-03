package com.goodideas.projectcube.ui.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.Util.onOffNightMode
import com.goodideas.projectcube.databinding.FragmentStartBinding
import org.koin.android.ext.android.bind


class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_start, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtonOnClick()
        initNightModeSwitch()
        imageRoundCorner()

    }

    private fun imageRoundCorner(){
        Glide.with(this.requireContext())
            .load(R.drawable.goodideas)
            .transform(RoundedCorners(20))
            .into(binding.logoImg)
    }
    private fun initButtonOnClick(){
        binding.loginButton.setOnClickListener {
            hideKeyboard(it)
            loginConfirm()
            findNavController().navigate(R.id.action_startFragment_to_articleListFragment)
        }
        binding.skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_articleListFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_registerFragment)
        }
    }

    private fun loginConfirm(){
        //TODO post login request
    }

    //TODO for ui test, need remove before release
    private fun initNightModeSwitch(){
        binding.nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            onOffNightMode(isChecked)
        }
    }

}