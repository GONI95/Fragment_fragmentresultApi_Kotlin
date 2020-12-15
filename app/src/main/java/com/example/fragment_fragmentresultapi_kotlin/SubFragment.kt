package com.example.fragment_fragmentresultapi_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController


class SubFragment : Fragment(R.layout.fragment_sub) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("MainToSub") { requestKey, bundle ->
            val result = bundle.getString("text", "null")
            Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()

        }
    }
}