package com.example.fragment_fragmentresultapi_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController


class SubFragment : Fragment(R.layout.fragment_sub){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("MainToSub")
        { requestKey, bundle ->
            var resultText = bundle.getString("text", "null")

            println(resultText)
            view.findViewById<TextView>(R.id.textView).text = resultText
        }
    }
}