package com.example.fragment_fragmentresultapi_kotlin

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

// https://developer.android.com/training/basics/fragments/pass-data-between
class MainFragment : Fragment(R.layout.fragment_main) {

    // 암시적 인텐트 사용법
    // 기존에 사용하던 startActivityForResult가 이제 업데이트를 멈추고 Result Api가 나옴
    val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        // getContent는 원하는 형태의 암시적인 인텐트를 아무거나 전달하면 그것에 대한 결과를 리턴
        view?.findViewById<ImageView>(R.id.imageView)?.setImageURI(it)
    }

    // 명시적 인텐트 사용법
    val getStartActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){  ActivityResult ->

        ActivityResult.data?.let{   intent ->
            intent.extras?.let{ bundle ->
               println("result : ${bundle.getString("data", "null")}")
                println("result : ${bundle.getString("data2", "null")}")
                println("result : ${bundle.getString("data3", "null")}")
                println("result : ${bundle.getString("data4", "null")}")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // subFragment
        view.findViewById<Button>(R.id.button).setOnClickListener {
            setFragmentResult("MainToSub", bundleOf("text" to "나는 서브프래그먼트다"))
            findNavController().navigate(R.id.action_mainFragment_to_subFragment)
        }

        // mainFragment
        view.findViewById<Button>(R.id.camera).setOnClickListener {
            getContent.launch("image/*")
            // 데이터의 마임 타입을 정의하면 됨
        }

        // subactivity
        view.findViewById<Button>(R.id.intent_button).setOnClickListener {
            Intent(requireContext(), ResultActivity::class.java).apply {
                getStartActivityForResult.launch(this)
            }
        }
    }
}