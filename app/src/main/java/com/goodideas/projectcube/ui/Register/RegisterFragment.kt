package com.goodideas.projectcube.ui.Register

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
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.databinding.FragmentRegisterBinding
import com.goodideas.projectcube.ui.Login.ResponseStatus
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.Exception


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding

    private val vm by viewModel<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )
        vm.initRegisterResult()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

        initObserver()
    }

    private fun initObserver(){
        vm.registerResult.observe(viewLifecycleOwner, Observer {
            when(it){
                ResponseStatus.SUCCESS -> {
                    binding.loading.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_articleListFragment)
                }
                ResponseStatus.FAIL -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(context, vm.registerMessage, Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.BEFORE -> Timber.d("RegisterFragment")
                ResponseStatus.LOADING -> binding.loading.visibility = View.VISIBLE

            }
        })


    }
    private fun initUI() {
        Glide.with(this.requireContext())
            .load("https://i.imgur.com/8UI3aoa.gif")
            .into(binding.loading)

        binding.registerButton.setOnClickListener {
            hideKeyboard(it)
            checkFormat()
        }
    }

    private fun checkFormat() {
        val email = binding.emailText.editText?.text

//        val password = binding.passwordText.editText?.text
        val password = "12345678"
//        val confirm = binding.reconfirmPasswordText.editText?.text
        val confirm = "12345678"

        if (password == confirm && password.isNotBlank() && password.length >= 8) {
            Timber.d("vm register")
            vm.register(
                name = "33",
                email.toString(),
                password,
                confirm
            )
        } else {
            // TODO ask user to change data
        }

        if (email != null) {
            //TODO post to register
        } else {
            // TODO ask user to change data
        }
    }

}