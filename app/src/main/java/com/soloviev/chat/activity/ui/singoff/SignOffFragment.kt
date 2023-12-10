package com.soloviev.chat.activity.ui.singoff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.soloviev.chat.activity.SplashActivity
import com.soloviev.chat.databinding.FragmentSignoffBinding

class SignOffFragment : Fragment() {

    private var _binding: FragmentSignoffBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signOffViewModel =
            ViewModelProvider(this).get(SignOffViewModel::class.java)

        _binding = FragmentSignoffBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textConfirmation
        signOffViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val confirmationButton: Button = binding.buttonConfirmation
        confirmationButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val login = Intent(activity, SplashActivity::class.java)
            startActivity(login)
            activity?.finish()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}