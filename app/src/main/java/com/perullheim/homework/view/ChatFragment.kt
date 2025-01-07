package com.perullheim.homework.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentChatBinding
import com.perullheim.homework.model.Message
import com.perullheim.homework.model.Sender
import java.time.LocalDateTime

class ChatFragment : Fragment() {

    private val messages = mutableListOf<Message>()

    private var chatAdapter = ChatAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSendMessage.setOnClickListener {
            messages.add(getNewMessage())
            chatAdapter.submitList(messages.toList())
        }
    }

    private fun getNewMessage(): Message {
        return Message(
            body = binding.etMessage.text.toString(),
            timestamp = LocalDateTime.now(),
            sender = if (messages.size % 2 == 1) Sender.SELF else Sender.PARTNER
        )
    }

    // =============== Binding =============== \\

    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}