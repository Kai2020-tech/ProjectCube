package com.goodideas.projectcube.ui.uiTest

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentCPostVolOneBinding
import com.goodideas.projectcube.ui.CreatePost.CreatePostViewModel
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.InputStream

class CPostVolOne : Fragment() {
    lateinit var binding:FragmentCPostVolOneBinding
    private val vm by viewModel<CreatePostViewModel>()
    var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_c_post_vol_one, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.oneSent.setOnClickListener {
            val t = binding.onePostTitle.text.toString()
            val c = binding.onePostContent.text.toString()
            if (t.isNotBlank() && c.isNotBlank()) {
                vm.createPost(
                    MultipartBody.Part.createFormData("title", t),
                    MultipartBody.Part.createFormData("content", c),
                    imageUri
                )
            } else {
                Toast.makeText(this.requireContext(), "title and content must not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.oneAddImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage){
            imageUri = data?.data
            binding.oneImg.setImageURI(imageUri)
            Timber.d(imageUri.toString())
        }
    }

    companion object {
        private const val pickImage = 100
    }
}