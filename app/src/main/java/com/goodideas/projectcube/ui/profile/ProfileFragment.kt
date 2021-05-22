package com.goodideas.projectcube.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentProfileBinding
import com.goodideas.projectcube.ui.CreatePost.CreatePostFragment
import com.goodideas.projectcube.ui.Login.userId
import com.goodideas.projectcube.ui.ReadArticle.imagePrefix
import com.goodideas.projectcube.ui.articleList.ArticleAdapter
import okhttp3.MultipartBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

//todo <-- 405 Method Not Allowed http://api.rrrui.site/api/profile/2 (94ms)
class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    private val vm by viewModel<ProfileViewModel>()
    lateinit var adapter:ProfilePostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile, container,false)

        vm.getUserProfile(userId)
        adapter = ProfilePostsAdapter(this.requireContext())
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()
    }
    private fun initUI(){
        binding.editProfile.setOnClickListener {
            val v = this.requireActivity().layoutInflater.inflate(R.layout.profile_update,null)
            val nameUpdate = v.findViewById<EditText>(R.id.dialog_name_edit)
            AlertDialog.Builder(this.requireContext())
                .setTitle("Update Profile")
                .setPositiveButton("confirm change"){ _,_ ->
                    if(nameUpdate.text.isNullOrBlank()) Toast.makeText(this.requireContext(),"name must not be empty",Toast.LENGTH_SHORT).show()
                    else vm.updateProfile(
                        //TODO kai need confirm multipart... key value pair "key"
                        MultipartBody.Part.createFormData("name",nameUpdate.text.toString()),
                    null
                    )
                }
                .setView(v)
                .show()
        }
        binding.userAvatar.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    private fun initObserver(){
        vm.userProfile.observe(viewLifecycleOwner, Observer {
            Timber.d(it.toString())
            adapter.submitList(it.posts?.data)
            Glide.with(this.requireContext())
                .load(imagePrefix + it.user?.avatar)
                .into(binding.userAvatar)
            binding.userName.text = it.user?.name
            binding.likeCount.text = it.user?.likeCount.toString()
            binding.dislikeCount.text = it.user?.dislikeCount.toString()
            binding.postCount.text = it.user?.postCount.toString()
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage){
            binding.userAvatar.setImageURI(data?.data)
            vm.updateProfile(null,data?.data)
        }
    }
    companion object {
        private const val pickImage = 100
    }
}