package com.goodideas.projectcube.ui.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.Util.onOffNightMode
import com.goodideas.projectcube.databinding.FragmentStartBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding

    private val vm by viewModel<StartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start, container, false
        )
        vm.initLoginResult()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtonOnClick()
        initNightModeSwitch()
        imageRoundCorner()
        initObserver()
    }

    private fun imageRoundCorner() {
        Glide.with(this.requireContext())
            .load(R.drawable.goodideas)
            .transform(RoundedCorners(20))
            .into(binding.logoImg)

        Glide.with(this.requireContext())
            .load("https://i.imgur.com/8UI3aoa.gif")
            .into(binding.loading)
    }

    private fun initObserver(){
        vm.loginResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                ResponseStatus.SUCCESS -> {
                    binding.logoImg.visibility = View.GONE
                    findNavController().navigate(R.id.action_startFragment_to_articleListFragment)
                }
                ResponseStatus.FAIL -> {
                    binding.logoImg.visibility = View.GONE
                    Toast.makeText(
                        this.requireContext(),
                        "Login fail, please check email and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ResponseStatus.LOADING -> binding.loading.visibility = View.VISIBLE
                ResponseStatus.BEFORE -> Timber.d("startFragment")
            }
        })
    }

    private fun initButtonOnClick() {
        binding.loginButton.setOnClickListener {
            hideKeyboard(it)
            loginConfirm()
        }
        binding.skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_articleListFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_registerFragment)
        }
    }

    private fun loginConfirm() {
        // TODO for ui test, need remove before release
        val email = "test01@test.com"
        val pwd = "12345678"
        vm.login(email, pwd)
    }

    // TODO for ui test, need remove before release
    private fun initNightModeSwitch() {
        binding.nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            onOffNightMode(isChecked)
        }
    }

}