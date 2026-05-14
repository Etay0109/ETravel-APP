package com.example.etravelapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.etravelapp.LoginActivity
import com.example.etravelapp.databinding.FragmentProfileBinding
import com.example.etravelapp.databinding.ItemDocumentCardBinding
import com.example.etravelapp.utilities.SignalManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private lateinit var pickFileLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    uploadProfileImage(uri)
                }
            }

        pickFileLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    uploadUserDocument(uri)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showUserInfo()
        loadProfileImage()
        loadUserDocuments()

        binding.profileIMGAvatar.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.profileCARDUpload.setOnClickListener {
            pickFileLauncher.launch("*/*")
        }

        binding.profileBTNLogout.setOnClickListener {
            logout()
        }
    }

    private fun showUserInfo() {    // Displays the current user's name and email on the screen

        val user = auth.currentUser ?: return

        binding.profileLBLName.text = user.displayName ?: "User"
        binding.profileLBLEmail.text = user.email ?: ""
    }

    private fun loadProfileImage() {    // Loads the user's profile image from Firebase Storage

        val uid = auth.currentUser?.uid ?: return

        val imageRef = storage.reference.child("profile_images/$uid")

        imageRef.downloadUrl.addOnSuccessListener { uri ->

            if (_binding != null) {

                Glide.with(this)
                    .load(uri)
                    .into(binding.profileIMGAvatar)
            }
        }
    }

    private fun uploadProfileImage(imageUri: Uri) { // Uploads a new profile image to Firebase Storage

        val uid = auth.currentUser?.uid ?: return

        val imageRef = storage.reference.child("profile_images/$uid")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {

                SignalManager.getInstance()
                    .toast("Profile image updated")

                loadProfileImage()
            }
            .addOnFailureListener { e ->

                SignalManager.getInstance()
                    .toast("Image upload failed: ${e.message}")
            }
    }

    private fun getFileName(uri: Uri): String? {    // Gets the file name from the selected file

        var fileName: String? = null

        val cursor = requireContext().contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {

            val nameIndex =
                cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)

            if (cursor.moveToFirst() && nameIndex >= 0) {
                fileName = cursor.getString(nameIndex)
            }

            cursor.close()
        }

        return fileName
    }

    private fun uploadUserDocument(fileUri: Uri) {  // Uploads a document to Firebase Storage

        val uid = auth.currentUser?.uid ?: return

        val fileName = getFileName(fileUri) ?: "document"

        val fileRef = storage.reference
            .child("user_documents/$uid/$fileName")

        fileRef.putFile(fileUri)
            .addOnSuccessListener {

                SignalManager.getInstance()
                    .toast("File uploaded successfully")

                loadUserDocuments()
            }
            .addOnFailureListener { e ->

                SignalManager.getInstance()
                    .toast("Upload failed: ${e.message}")
            }
    }

    private fun loadUserDocuments() {   // Loads all user documents from Firebase Storage

        val uid = auth.currentUser?.uid ?: return

        val docsRef = storage.reference
            .child("user_documents/$uid")

        docsRef.listAll().addOnSuccessListener { result ->

            if (_binding == null) return@addOnSuccessListener

            binding.profileLAYFiles.removeAllViews()

            for (fileRef in result.items) {
                addDocumentCard(fileRef)
            }
        }
    }

    private fun addDocumentCard(fileRef: StorageReference) {    // Creates and displays a card for a single document

        if (_binding == null) return

        val itemBinding = ItemDocumentCardBinding.inflate(
            layoutInflater,
            binding.profileLAYFiles,
            false
        )

        itemBinding.docLBLName.text = fileRef.name

        itemBinding.docLBLName.setOnClickListener {

            fileRef.downloadUrl.addOnSuccessListener { uri ->

                val intent = Intent(Intent.ACTION_VIEW, uri)

                startActivity(intent)
            }
        }

        itemBinding.docBTNDelete.setOnClickListener {
            deleteFile(fileRef)
        }

        binding.profileLAYFiles.addView(itemBinding.root)
    }

    private fun deleteFile(fileRef: StorageReference) { // Deletes a document from Firebase Storage and updates the UI

        fileRef.delete()
            .addOnSuccessListener {

                SignalManager.getInstance()
                    .toast("File deleted successfully")

                loadUserDocuments()
            }
            .addOnFailureListener { e ->

                SignalManager.getInstance()
                    .toast("Error deleting file: ${e.message}")
            }
    }

    private fun logout() {  // Signs out the user and returns to the login screen

        auth.signOut()

        val intent =
            Intent(requireContext(), LoginActivity::class.java)

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}