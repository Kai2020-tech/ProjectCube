package com.goodideas.projectcube.ui.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.hideKeyboard
import com.goodideas.projectcube.databinding.FragmentRegisterBinding
import java.lang.Exception


class RegisterFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_register, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonOnClick()
    }

    private fun initButtonOnClick(){
        binding.registerButton.setOnClickListener {
            try {
                hideKeyboard(it)
                checkFormat()
            } catch (e:Exception) {

            } finally {
                // might move this to other place, for make sure email wouldn't repeat
                findNavController().navigate(R.id.action_registerFragment_to_articleListFragment)
            }
        }
    }

    private fun checkFormat(){
        val email = binding.emailText.editText?.text

        val password = binding.passwordText.editText?.text
        val confirm = binding.reconfirmPasswordText.editText?.text

        if (password == confirm && password != null && password.length >= 8){
            //TODO post to register
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