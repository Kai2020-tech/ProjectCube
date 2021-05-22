package com.goodideas.projectcube.ui.CreatePost

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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
import retrofit2.http.Url
import timber.log.Timber

class CreatePostFragment : Fragment() {
    lateinit var binding: FragmentCreatePostBinding
    private val vm by viewModel<CreatePostViewModel>()
    private val updateVm by viewModel<UpdatePostViewModel>()
    var imageUri: Uri? = null
    var editData:SinglePostRes? = null

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
        updateVm.updatePostResult.observe(viewLifecycleOwner, Observer {
            when(it){
                ResponseStatus.SUCCESS -> findNavController().navigate(R.id.action_createPostFragment_to_articleListFragment)
                ResponseStatus.FAIL -> Toast.makeText(this.requireContext(), "fail, try again",Toast.LENGTH_SHORT).show()
                ResponseStatus.LOADING ->Toast.makeText(this.requireContext(), "loading",Toast.LENGTH_SHORT).show()
                ResponseStatus.BEFORE -> Timber.d("createpostfragment")
            }
        })
    }
    private fun checkEditOrCreate(){
        editData = arguments?.getParcelable("editArticle")
        editData?.let {
            val (_,post) = it
            binding.newPostTitle.setText(post?.title)
            binding.newPostContent.setText(post?.content)
            if (post?.image != "null"){
                binding.imgHolder.visibility = View.VISIBLE
                Glide.with(this.requireContext())
                    .load(imagePrefix + post?.image)
                    .into(binding.img)
                binding.removePhoto.setOnClickListener {
                    // TODO kenny remove photo from server, no need now
                    binding.imgHolder.visibility = View.GONE
                }
                binding.addImage.setOnClickListener {
                    binding.imgHolder.visibility = View.GONE
                }
            }
        }
    }
    private fun addView(url: Uri?){
        val imgView = ImageView(this.requireContext())
        imgView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        val createId = View.generateViewId()
        imgView.id = createId
        imgView.setImageURI(url)
        binding.scrollLayout.addView(imgView)
        vm.contentList.add(Pair(createId,url.toString()))

        val edView = EditText(this.requireContext())
        edView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        val secondId = View.generateViewId()
        edView.id = secondId
        edView.setBackgroundResource(android.R.color.transparent)
        vm.contentList.add(Pair(secondId,""))
        vm.contentList.forEachIndexed { index, pair ->
            if (pair.first == secondId) initContentTrack(edView,index)
        }

        binding.scrollLayout.addView(edView)
    }

    private fun initUI() {
        vm.contentList.add(Pair(binding.newPostContent.id,""))
        initContentTrack(binding.newPostContent,0)

        binding.tmpSent.setOnClickListener {
//            val t = binding.newPostTitle.text.toString()
//            val c = binding.newPostContent.text.toString()
//            if (!t.isBlank() && !c.isBlank()) {
//                if (editData == null){
//                    vm.createPost(
//                        MultipartBody.Part.createFormData("title", t),
//                        MultipartBody.Part.createFormData("content", c),
//                        imageUri
//                    )
//                } else {
//                    //todo kai @Part parameters can only be used with multipart encoding. (parameter #2)
//                    editData?.post?.id?.let { it1 ->
//                        updateVm.UpdatePost(
//                            it1,
//                            MultipartBody.Part.createFormData("title", t),
//                            MultipartBody.Part.createFormData("content", c),
//                            imageUri
//                        )
//                    }
//                }
//            } else {
//                Toast.makeText(this.requireContext(), "title and content must not be empty", Toast.LENGTH_SHORT).show()
//            }
            Timber.d("before")
            vm.contentList.forEachIndexed { index, pair ->
                Timber.d("each")
                if (pair.second.isBlank())  {
                    vm.contentList.removeAt(index)
                }
            }
            Timber.d(vm.contentList.toString())
            //todo kenny BUG FOUND, if click sent but stay in this page, since we remove pair form list, ui wouldn't update with data
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
            binding.addImage.isClickable = false
            binding.imgHolder.visibility = View.VISIBLE
            binding.insertPhoto.setOnClickListener {
                addView(imageUri)
                binding.imgHolder.visibility = View.GONE
                binding.addImage.isClickable = true
            }
        }
    }

    private fun initContentTrack(e:EditText, i:Int){
        e.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                vm.contentList[i]= Pair(binding.newPostContent.id ,s.toString())
                Timber.d(s.toString())
            }

        })
    }
    companion object {
        private const val pickImage = 100
    }
}