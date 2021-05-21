package com.goodideas.projectcube.ui.profile

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.profile.UpdateProfileReq
import com.goodideas.projectcube.data.repo.profile.IProfileRepo
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.ByteArrayOutputStream

class ProfileViewModel(val repo: IProfileRepo, application: Application) :
    AndroidViewModel(application) {

    val app = application

    private val _userProfile: MutableLiveData<ProfileRes> = MutableLiveData<ProfileRes>()
    val userProfile: LiveData<ProfileRes>
        get() = _userProfile


    fun getUserProfile(userId: Int) {
        viewModelScope.launch {
            val response = repo.getUserProfile(userId)
            if (response.isSuccessful) {
                _userProfile.value = response.body()
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
            }
        }
    }

    fun updateProfile(name: MultipartBody.Part?, image: Uri?) {
        viewModelScope.launch {
            val imageBody = compressImage(image)
            val response = repo.updateProfile(UpdateProfileReq(name, imageBody))
            if (response.isSuccessful) {
                // TODO: 2021/5/21
            } else {
                // TODO: 2021/5/21
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