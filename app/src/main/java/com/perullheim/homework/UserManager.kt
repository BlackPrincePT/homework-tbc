package com.perullheim.homework

import android.util.Log.d
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object UserManager {

    private val TAG = UserManager::class.java.simpleName

    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val currentUser: FirebaseUser?
        get() = auth.currentUser

    var currentUserData: User? = null

    fun login(
        email: String,
        password: String,
        completion: (isRegistered: Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful)
                checkUserData(completion)
            else {
                val errorMsg = task.exception?.message.toString()
                d(TAG, errorMsg)
                completion(false)
            }
        }
    }

    private fun checkUserData(completion: (isRegistered: Boolean) -> Unit) {
        val loggedInUser = currentUser

        if (loggedInUser == null) {
            d(TAG, "Couldn't check user data, because user is not logged in")
            return
        }

        db.collection("users").document(loggedInUser.uid).get()
            .addOnSuccessListener { result ->
                if (result.exists())
                    currentUserData = result.toObject(User::class.java)

                completion(result.exists())
            }
            .addOnFailureListener { error ->
                val errorMsg = error.message.toString()
                d(TAG, errorMsg)
            }
    }

    fun createUser(username: String, completion: () -> Unit) {
        val loggedInUser = currentUser
        val email = loggedInUser?.email

        if (loggedInUser == null) {
            d(TAG, "Couldn't create user, because user is not logged in")
            return
        }

        if (email == null) {
            d(TAG, "Couldn't create user, because user does not have an email")
            return
        }

        val newUser = User(loggedInUser.uid, email, username)

        db.collection("users").document(loggedInUser.uid).set(newUser)
            .addOnSuccessListener {
                currentUserData = newUser
                completion()
            }
            .addOnFailureListener { error ->
                val errorMsg = error.message.toString()
                d(TAG, errorMsg)
            }
    }

    fun register(
        email: String,
        password: String,
        completion: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful)
                completion()
            else {
                val errorMsg = task.exception?.message.toString()
                d(TAG, errorMsg)
            }
        }
    }

    fun logout(completion: () -> Unit) {
        try {
            auth.signOut()
            completion()
        } catch (error: Exception) {
            val errorMsg = error.message.toString()
            d(TAG, errorMsg)
        }

    }

    fun deleteAccount(completion: () -> Unit) {
        val loggedInUser = currentUser

        if (loggedInUser == null) {
            d(TAG, "Couldn't delete the account, because user is not logged in")
            return
        }

        db.collection("users").document(loggedInUser.uid).delete()
            .addOnSuccessListener {
                currentUser?.delete()?.addOnCompleteListener {
                    if (it.isSuccessful)
                        d(TAG, "User account & data has successfully been deleted")
                    else
                        d(TAG, "Deleted user data, but couldn't delete user account")

                    completion()
                }
            }
            .addOnFailureListener { error ->
                val errorMsg = error.message.toString()
                d(TAG, errorMsg)
            }
    }
}