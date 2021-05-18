package com.goodideas.projectcube.ui.CreatePost

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentCreatePostBinding
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : Fragment() {
    lateinit var binding: FragmentCreatePostBinding
    private val vm by viewModel<CreatePostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_post, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.tmpSent.setOnClickListener {
            val t = binding.newPostTitle.text.toString()
            val c = binding.newPostContent.text.toString()
            var image: Uri? = null
            if (!t.isBlank() && !c.isBlank()) {
                vm.createPost(
                    MultipartBody.Part.createFormData("title", t),
                    MultipartBody.Part.createFormData("content", c),
                    image
                )
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "title and content must not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}