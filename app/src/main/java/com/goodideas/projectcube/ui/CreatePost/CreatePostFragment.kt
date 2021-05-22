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
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.databinding.FragmentCreatePostBinding
import com.goodideas.projectcube.ui.ReadArticle.imagePrefix
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.InputStream

//圖片ui之後會改位置
class CreatePostFragment : Fragment() {
    lateinit var binding: FragmentCreatePostBinding
    private val vm by viewModel<CreatePostViewModel>()
    var imageUri: Uri? = null
    var editData:SinglePostRes? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create_post, container, false
        )
//        this.requireActivity().actionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        checkEditOrCreate()
        initObserver()
    }

    private fun initObserver(){
        vm.createPostResult.observe(viewLifecycleOwner, Observer {
            when(it){
                ResponseStatus.SUCCESS -> findNavController().navigate(R.id.action_createPostFragment_to_articleListFragment)
                ResponseStatus.FAIL -> Toast.makeText(this.requireContext(), "fail, try again",Toast.LENGTH_SHORT).show()
                ResponseStatus.LOADING ->Toast.makeText(this.requireContext(), "loading",Toast.LENGTH_SHORT).show()
                ResponseStatus.BEFORE -> Timber.d("createpostfragment")
            }
        })
    }
    private fun checkEditOrCreate(){
        editData = arguments?.getParcelable<SinglePostRes>("editArticle")
        editData?.let {
            val (_,post) = it
            val data = post //?.get(0)
            binding.newPostTitle.setText(data?.title)
            binding.newPostContent.setText(data?.content)
            if (data?.image != "null"){
                binding.imgHolder.visibility = View.VISIBLE
                Glide.with(this.requireContext())
                    .load(imagePrefix + data?.image)
                    .into(binding.img)
                binding.removePhoto.setOnClickListener {
                    // TODO remove photo
                    binding.imgHolder.visibility = View.GONE
                }
                binding.addImage.setOnClickListener {
                    //todo this function need check add or not
                    binding.imgHolder.visibility = View.GONE
                }
            }
        }
    }

    private fun initUI() {
        binding.tmpSent.setOnClickListener {
            val t = binding.newPostTitle.text.toString()
            val c = binding.newPostContent.text.toString()
            if (!t.isBlank() && !c.isBlank()) {
                if (editData == null){
                    vm.createPost(
                        MultipartBody.Part.createFormData("title", t),
                        MultipartBody.Part.createFormData("content", c),
                        imageUri
                    )
                } else {
                    Timber.d("update")
                    //todo update
                }
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
            binding.imgHolder.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val pickImage = 100
    }
}