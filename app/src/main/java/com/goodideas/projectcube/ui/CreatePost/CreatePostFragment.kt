package com.goodideas.projectcube.ui.CreatePost

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
import com.goodideas.projectcube.databinding.FragmentCreatePostBinding
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.InputStream

class CreatePostFragment : Fragment() {
    lateinit var binding: FragmentCreatePostBinding
    private val vm by viewModel<CreatePostViewModel>()
    var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
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
            if (!t.isBlank() && !c.isBlank()) {
                vm.createPost(
                    MultipartBody.Part.createFormData("title", t),
                    MultipartBody.Part.createFormData("content", c),
                    imageUri
                )
            } else {
                Toast.makeText(this.requireContext(), "title and content must not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.addImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage){
            imageUri = data?.data
            binding.img.setImageURI(imageUri)
            Timber.d(imageUri.toString())
        }
    }

    private fun uriToInputStream(uri:Uri): InputStream? {
        return this.requireContext().contentResolver.openInputStream(uri)
    }

    companion object {
        private const val pickImage = 100
    }
}