package com.perullheim.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.transition.Visibility
import com.perullheim.homework.databinding.FragmentRegisterBinding
import com.perullheim.homework.databinding.FragmentSearchBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // ========= Functions ========= \\

    private fun setupListeners() {
        with(binding) {
            btnAddUser.setOnClickListener {
                moveToSearch()
            }

            etSearchUser.addTextChangedListener {
                val enteredData = etSearchUser.text.toString()

                UserManager.searchForUser(enteredData)?.let { foundUser ->
                    displayUserData(user = foundUser)

                    btnAddUser.visibility = View.GONE
                    return@addTextChangedListener
                }

                tvUserInfo.text = getString(R.string.user_not_found)
                btnAddUser.visibility = View.VISIBLE
            }
        }
    }

    private fun displayUserData(user: User) {

        // FIXME --------------
        val birthday = Date(user.birthday.toLong())
        val formatInfo = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
        val formatedBirthday = formatInfo.format(birthday)

        val descText = user.desc ?: "Not Provided"

        val userInfoString = """
            ID: ${user.id}
            First Name: ${user.firstName}
            Last Name: ${user.lastName}
            Birthday: $formatedBirthday
            Address: ${user.address}
            Email: ${user.email}
            Description: $descText
        """.trimIndent()

        binding.tvUserInfo.text = userInfoString
    }

    private fun moveToSearch() {
        val registerFragment = RegisterFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main, registerFragment)
            .addToBackStack(null)
            .commit()
    }


}




