package com.perullheim.homework.presentation.choose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentChooseBinding
import com.perullheim.homework.presentation.core.BaseBottomSheetDialogFragment
import com.perullheim.homework.presentation.photo.PhotoFragment.Companion.PHOTO_REQUEST_KEY
import com.perullheim.homework.presentation.photo.PhotoFragment.Companion.CAMERA_RESULT_KEY
import com.perullheim.homework.presentation.photo.PhotoFragment.Companion.GALLERY_RESULT_KEY

class ChooseFragment :
    BaseBottomSheetDialogFragment<FragmentChooseBinding>(FragmentChooseBinding::inflate) {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted)
                openCameraLauncher.launch(null)
        }

    private val openCameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                val resultBundle = bundleOf(CAMERA_RESULT_KEY to it)
                setResult(resultBundle)
            }
        }

    private val getImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val resultBundle = bundleOf(GALLERY_RESULT_KEY to it)
                setResult(resultBundle)
            }
        }

    override fun setup() = with(binding) {
        btnCamera.setOnClickListener { checkAndRequestCameraPermission() }
        btnGallery.setOnClickListener { getImageLauncher.launch("image/*") }
    }

    private fun checkAndRequestCameraPermission() {
        val cameraPermission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)

        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            openCameraLauncher.launch(null)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun setResult(result: Bundle) {
        setFragmentResult(PHOTO_REQUEST_KEY, result)
        findNavController().navigateUp()
    }
}