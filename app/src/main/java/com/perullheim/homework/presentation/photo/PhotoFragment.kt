package com.perullheim.homework.presentation.photo

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.perullheim.homework.databinding.FragmentPhotoBinding
import com.perullheim.homework.presentation.core.BaseFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID
import kotlin.properties.Delegates

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(FragmentPhotoBinding::inflate) {

    private var currentUri: Uri? by Delegates.observable(initialValue = null) { _, _, _ ->
        binding.imageView.setImageURI(currentUri)
    }

    override fun setup() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(PhotoFragmentDirections.actionPhotoFragmentToChooseFragment())
        }

        setFragmentResultListener(PHOTO_REQUEST_KEY) { _, bundle ->

            bundle.getParcelable<Bitmap>(CAMERA_RESULT_KEY)?.let { bitmap ->
                currentUri = getImageUri(bitmap)
            }

            bundle.getParcelable<Uri>(GALLERY_RESULT_KEY)?.let { uri ->
                currentUri = uri
            }
        }
    }

    private fun getImageUri(bitmap: Bitmap): Uri? {
        val imagesFolder = File(requireContext().cacheDir, "images")
        imagesFolder.mkdirs()
        val file = File(imagesFolder, "shared_image.png")

        try {
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        val authority = "com.perullheim.homework.fileprovider"
        return FileProvider.getUriForFile(requireContext(), authority, file)
    }

    companion object {
        const val PHOTO_REQUEST_KEY = "camera_request_key"
        const val CAMERA_RESULT_KEY = "camera_result_key"
        const val GALLERY_RESULT_KEY = "gallery_result_key"
    }
}