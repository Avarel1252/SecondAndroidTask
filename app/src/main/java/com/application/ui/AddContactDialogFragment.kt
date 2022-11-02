package com.application.ui

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.fragment.app.DialogFragment
import com.application.databinding.FragmentAddContactBinding
import com.application.models.UserModel


class AddContactDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAddContactBinding
    private var userPhotoUri = Uri.parse("")
    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            userPhotoUri = uri
        }
    }

    companion object {
        const val TAG = "addContactDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false).apply {
            btnSave.setOnClickListener { returnUserInfo() }
            btnAddPhoto.setOnClickListener {
                addPhoto()
                binding.ivAccPhoto.setImageURI(userPhotoUri)
            }
            imgBtnBack.setOnClickListener { goBack() }
        }
        return binding.root
    }


    private fun returnUserInfo() {
        with(binding) {
            if (etUsername.text?.toString()?.isEmpty() == false) {
                (activity as MyContactsActivity).addUserToModel(
                    UserModel(
                        (activity as MyContactsActivity).getLastIdInModel() + 1,
                        userPhotoUri.toString(),
                        etUsername.text.toString(),
                        etCareer.text.toString(),
                        etEmail.text.toString(),
                        etPhone.text.toString(),
                        etAddress.text.toString(),
                        etDateBirth.text.toString()
                    )
                )
                dismiss()
            } else {
                tInUsername.error = "necessary field"
            }
        }
    }

    private fun addPhoto() {
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

    private fun goBack() {
        dismiss()
    }

}