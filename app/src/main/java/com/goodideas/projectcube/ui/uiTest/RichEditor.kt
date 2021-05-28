package com.goodideas.projectcube.ui.uiTest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.goodideas.projectcube.R
import com.goodideas.projectcube.databinding.FragmentRichEditorBinding
import jp.wasabeef.richeditor.RichEditor
import timber.log.Timber


class RichEditor : Fragment() {
    lateinit var binding:FragmentRichEditorBinding
    lateinit var d:DisplayMetrics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rich_editor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        d = this.requireContext().resources.displayMetrics
        initUI()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) binding.editor.themeChange()

    }
    private fun initUI(){

        binding.addEditorVideo.setOnClickListener {
            val video = Intent(Intent.ACTION_PICK)
            video.setDataAndType(MediaStore.Video.Media.INTERNAL_CONTENT_URI, "video/*")
            startActivityForResult(video, 98)
        }
        binding.addEditorImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK)
            gallery.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(gallery, 99)
        }
        binding.sent.setOnClickListener {
            Timber.d(binding.editor.html)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                99 -> {
                    binding.editor.insertImage(
                        data?.data.toString(),
                        "image"
                    )
                }
                98 -> {
                    binding.editor.insertVideo(
                        data?.data.toString()
                    )
                }
            }
        }
    }
}



