package com.goodideas.projectcube.ui.updatePost

import android.app.Activity
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
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.dto.posts.SinglePostRes
import com.goodideas.projectcube.databinding.FragmentUpdatePostBinding
import com.goodideas.projectcube.ui.CreatePost.CreatePostFragment
import com.goodideas.projectcube.ui.ReadArticle.imagePrefix
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UpdatePostFragment : Fragment() {
    lateinit var binding:FragmentUpdatePostBinding
    private val vm by viewModel<UpdatePostViewModel>()

    var imageUri: Uri? = null
    var editData: SinglePostRes? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update_post, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initUI()
        checkEditData()
    }

    private fun initObserver(){
        vm.updatePostResult.observe(viewLifecycleOwner, Observer {
            when(it){
                ResponseStatus.SUCCESS -> findNavController().navigate(R.id.action_updatePostFragment_to_articleListFragment)
                ResponseStatus.FAIL -> Toast.makeText(this.requireContext(), "fail, try again",
                    Toast.LENGTH_SHORT).show()
                ResponseStatus.LOADING -> Toast.makeText(this.requireContext(), "loading", Toast.LENGTH_SHORT).show()
                ResponseStatus.BEFORE -> Timber.d("createpostfragment")
            }
        })
    }
    //中:拿資料放到ui
    private fun checkEditData(){
        editData = arguments?.getParcelable("editArticle")
        editData?.let {
            val (_,post) = it
            binding.edPostTitle.setText(post?.title)

            initContentTrack(binding.edPostContent,0)

            binding.edPostContent.setText(post?.content)

            post?.image?.let { s->
                addView(s.toUri())

                Glide.with(this.requireContext())
                    .load(imagePrefix + post.image)
                    .into(view?.findViewById(vm.contentList[1].first) as ImageView)
            }
        }
    }

    //中:同createPostFragment
    private fun addView(url: Uri?){
        val imgView = ImageView(this.requireContext())
        imgView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        val createId = View.generateViewId()
        imgView.id = createId
        imgView.setImageURI(url)
        binding.edScrollLayout.addView(imgView)
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

        binding.edScrollLayout.addView(edView)
    }

    private fun initUI() {
        vm.contentList.add(Pair(binding.edPostContent.id,""))
        initContentTrack(binding.edPostContent,0)

        binding.edTmpSent.setOnClickListener {
            val t = binding.edPostTitle .text.toString()
            val c = binding.edPostContent.text.toString()
            if (!t.isBlank() && !c.isBlank()) {
                    //todo kai @Part parameters can only be used with multipart encoding. (parameter #2)
                    editData?.post?.id?.let { it1 ->
                       vm.updatePost(
                            it1,
                            MultipartBody.Part.createFormData("title", t),
                            MultipartBody.Part.createFormData("content", c),
                            imageUri
                        )
                    }
                    Timber.d("update post")
            } else {
                Toast.makeText(this.requireContext(), "title and content must not be empty", Toast.LENGTH_SHORT).show()
            }
            Timber.d(vm.contentList.toString())
        }

        binding.edAddImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage){
            imageUri = data?.data
            binding.img.setImageURI(imageUri)
            Timber.d(imageUri.toString())
            binding.edAddImage.isClickable = false
            binding.edImgHolder.visibility = View.VISIBLE
            binding.edInsertPhoto.setOnClickListener {
                addView(imageUri)
                binding.edImgHolder.visibility = View.GONE
                binding.edAddImage.isClickable = true
            }
        }
    }

    private fun initContentTrack(e:EditText, i:Int){
        e.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                vm.contentList[i]= Pair(binding.edPostContent.id ,s.toString())
                Timber.d(s.toString())
            }

        })
    }
    companion object {
        private const val pickImage = 100
    }
}