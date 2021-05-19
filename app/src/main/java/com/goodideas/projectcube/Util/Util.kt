package com.goodideas.projectcube.Util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.ByteArrayOutputStream

fun hideKeyboard(view: View) {
    val imm = view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}

fun onOffNightMode(b: Boolean) {
    when (b) {
        true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun Fragment.compressImage(imageUri: Uri?): MultipartBody.Part? {
    return if (imageUri != null) {
        val imageStream = requireContext().contentResolver.openInputStream(imageUri)
        val degree = getImageDegree(requireContext(), imageUri)
        val bmp = BitmapFactory.decodeStream(imageStream)
        val rotatedBmp = rotateBitmap(bmp, degree)
        //將bitmap進行品質壓縮到byteArrayOut, compress Quality 100->15
        val baoS: ByteArrayOutputStream? = ByteArrayOutputStream()
        rotatedBmp.compress(Bitmap.CompressFormat.JPEG, 15, baoS)
        val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), baoS!!.toByteArray())
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