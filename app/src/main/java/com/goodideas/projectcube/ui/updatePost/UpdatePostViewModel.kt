package com.goodideas.projectcube.ui.updatePost

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodideas.projectcube.Util.ResponseStatus
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File

class UpdatePostViewModel(private val repo: IPostsRepo, application: Application) :
    AndroidViewModel(application) {

    val app = application

    val updatePostResult: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.BEFORE)


    //中:存view的id跟內容
    val contentList = mutableListOf<Pair<Int,String>>()


    private fun getPhoto(imageUri: Uri?): MultipartBody.Part? {
        val file = File(imageUri?.path ?: "")
        return if (file.exists()) {
            val requestFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestFile)
        } else null
    }


    fun updatePost(
        id: Int,
        t: MultipartBody.Part,
        c: MultipartBody.Part,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            val response = repo.updatePost(id, t, c, compressImage(imageUri))
            if (response.isSuccessful){
                updatePostResult.value = ResponseStatus.SUCCESS
            }else{
                updatePostResult.value = ResponseStatus.FAIL
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun compressImage(imageUri: Uri?): MultipartBody.Part? {
        return if (imageUri != null) {
            val imageStream = app.contentResolver.openInputStream(imageUri)
            val degree = getImageDegree(app, imageUri)
            val bmp = BitmapFactory.decodeStream(imageStream)
            val rotatedBmp = rotateBitmap(bmp, degree)
            //將bitmap進行品質壓縮到byteArrayOut, compress Quality 100->15
            val baoS: ByteArrayOutputStream? = ByteArrayOutputStream()
            rotatedBmp.compress(Bitmap.CompressFormat.JPEG, 15, baoS)
            val requestFile =
                RequestBody.create("image/jpg".toMediaTypeOrNull(), baoS!!.toByteArray())
            Timber.d("image requestFile Size ${requestFile.contentLength()}")
            MultipartBody.Part.createFormData("image", "sample.jpg", requestFile)

        } else null
    }

    // 取得原圖的EXIF ORIENTATION
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getImageDegree(context: Context, uri: Uri): Int {
        var degree = 0
        val input = context.contentResolver.openInputStream(uri)!!
        val exifInterface = ExifInterface(input)
        val orientation =
            exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
            ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
            ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
        }
        return degree
    }

    // 將原圖的ORIENTATION與bitmap傳入,回傳一個新的bitmap
    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true)
    }
}