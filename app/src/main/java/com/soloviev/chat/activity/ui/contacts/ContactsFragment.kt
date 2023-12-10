package com.soloviev.chat.activity.ui.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.soloviev.chat.activity.USERS_REF
import com.soloviev.chat.data.User
import com.soloviev.chat.databinding.FragmentContactsBinding
import timber.log.Timber


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactsViewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rvContacts.layoutManager= LinearLayoutManager(context)
        return root
    }

    override fun onResume() {
        super.onResume()
        val dataBase = Firebase.database
        val usersRef = dataBase.getReference(USERS_REF)
        usersRef.orderByChild("name") //filtering by name
            .equalTo("John")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Timber.d("Got nice snapshot!")
                    if (snapshot.exists()) {
                        val list = mutableListOf<User>()
                        for (dataSnapshot in snapshot.children) {
                            val user: User? = dataSnapshot.getValue(User::class.java)
                            user.let { list.add(user!!) }
                            Log.d("myTag", "User found: $user")
                        }
                        val adapter = ContactsAdapter()
                        adapter.users = list
                        binding.rvContacts.adapter = adapter

                        //adapter.notifyDataSetChanged()
                    } else {
                        Timber.e("Contacts snapshot doesn't exist")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}