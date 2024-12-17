package com.perullheim.homework

import android.util.Log.d
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore

object UserManager {

    private val TAG = UserManager::class.java.simpleName

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    fun subscribeToUserCount(path: String, completion: (count: Int) -> Unit) {
        db.collection(path).addSnapshotListener { querySnapshot, _ ->
            val documentCount = querySnapshot?.size() ?: 0
            completion(documentCount)
        }
    }

    fun addUser(user: User, completion: (message: String) -> Unit) {
        findUserBy(user.email, completion,
            elseFn = {
                db.collection("users-active").add(user)
                    .addOnSuccessListener {
                        val msg = "User added successfully"
                        d(TAG, msg)
                        completion(msg)
                    }
                    .addOnFailureListener { error ->
                        val errorMsg = error.message.toString()
                        d(TAG, errorMsg)
                        completion(errorMsg)
                    }
        }, onSuccess = {
            completion("User already exists")
            })

    }

    fun removeUser(email: String, completion: (message: String) -> Unit) {
        findUserBy(email, completion) { document ->
            moveUsersDataToRemoved(document)
            completion("User deleted successfully")
        }
    }

    fun updateUser(email: String, newUserData: User, completion: (message: String) -> Unit) {
        findUserBy(email, completion) { document ->
            db.collection("users-active").document(document.id).set(newUserData)
                .addOnSuccessListener {
                    completion("User updated successfully")
                }
                .addOnFailureListener { error ->
                    val errorMsg = error.message.toString()
                    d(TAG, errorMsg)
                    completion(errorMsg)
                }
        }
    }


    // ========= Helper Functions =========

    private fun findUserBy(
        email: String,
        completion: (message: String) -> Unit,
        elseFn: () -> Unit = { val msg = "User does not exist"; d(TAG, msg); completion(msg) },
        onSuccess: (querySnapshot: QueryDocumentSnapshot) -> Unit
    ) {
        db.collection("users-active").whereEqualTo("email", email).get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty)
                    querySnapshot.forEach { document ->
                        onSuccess(document)
                    }
                else
                    elseFn()
            }
            .addOnFailureListener { error ->
                val errorMsg = error.message.toString()
                d(TAG, errorMsg)
                completion(errorMsg)
            }
    }

    private fun moveUsersDataToRemoved(document: QueryDocumentSnapshot) {
        db.collection("users-removed").document(document.id).set(document.data)
            .addOnSuccessListener {
                d(TAG, "User transferred to 'removed-users' successfully")
                removeUserBy(document.id)
            }
    }

    private fun removeUserBy(id: String) {
        db.collection("users-active").document(id).delete()
            .addOnSuccessListener {
                d(TAG, "User removed successfully from 'users-active'")
            }
            .addOnFailureListener { error ->
                val errorMsg = error.message.toString()
                d(TAG, errorMsg)
            }
    }
}