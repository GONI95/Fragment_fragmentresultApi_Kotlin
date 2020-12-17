package com.example.fragment_fragmentresultapi_kotlin

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

// https://developer.android.com/training/basics/fragments/pass-data-between
// https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts
class MainFragment : Fragment(R.layout.fragment_main) {

    // 1. 암시적 인텐트 사용법(camera)
    // 기존에 사용하던 startActivityForResult가 이제 업데이트를 멈추고 Result Api가 나옴
    val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
        // getContent는 원하는 형태의 암시적인 인텐트를 아무거나 전달하면 그것에 대한 결과를 리턴
        view?.findViewById<ImageView>(R.id.imageView)?.setImageURI(it)
    }

    // 2. 명시적 인텐트 사용법(intent_button)
    val getStartActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){  ActivityResult ->

        if(ActivityResult?.resultCode == Activity.RESULT_OK){
            ActivityResult.data?.let{   intent ->
                intent.extras?.let{ bundle ->
                    println("result : ${bundle.getString("data", "null")}")
                    println("result : ${bundle.getString("data2", "null")}")
                    println("result : ${bundle.getString("data3", "null")}")
                    println("result : ${bundle.getString("data4", "null")}")
                }
            }// 해당 소스가 없어도 이동은 된다.
        }else{
            println("result : 전송 실패")
        }
    }


    val REQUEST_IMAGE_CAPTURE = 1
    /*

    // 3. 권한 요청 <- 이것에 특화됨3333333333333333333333333333333
    val takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result : ActivityResult ->


        if(result.resultCode == REQUEST_IMAGE_CAPTURE && result.resultCode == Activity.RESULT_OK)
        {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            view?.findViewById<ImageView>(R.id.imageView)?.setImageBitmap(imageBitmap)
        }
    }
     */



    /*
     // 3. 권한 요청 <- 이것에 특화됨
    val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            granted ->
        if (granted) {
            println("result : 권한 성공")
        }
    }
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 0. 반환위치 : subFragment / 화면 돌리면 초기화되버림(뷰모델, 세이브인스턴스 사용해야하나)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            setFragmentResult("MainToSub", bundleOf("text" to "나는 서브프래그먼트다"))
            findNavController().navigate(R.id.action_mainFragment_to_subFragment)
        }

        // 1. 반환위치 : mainFragment
        view.findViewById<Button>(R.id.camera).setOnClickListener {
            getContent.launch("image/*")
            // 데이터의 마임 타입을 정의하면 됨
        }

        // 2. 반환위치 : ResultActivity
        view.findViewById<Button>(R.id.intent_button).setOnClickListener {
            Intent(requireContext(), ResultActivity::class.java).apply {
                getStartActivityForResult.launch(this)
            }
        }


          // 3. 반환 위치 : MainFragment3333333333333333333333333333333
        view.findViewById<Button>(R.id.button2).setOnClickListener {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }

            //Intent(requireContext(), ResultActivity::class.java).apply {
            //   takePicture.launch(this)
           // }
            
            // <uses-permission android:name="android.permission.CAMERA"/> 들어가면 강제종료됨
            // takePicture.launch(intent)
            // https://developer.android.com/training/camera/photobasics?hl=ko#kotlin
            // https://ebbnflow.tistory.com/177
            // https://hwanine.github.io/android/camera/
        }



        /*
        // 3. 반환 위치 : MainFragment
        view.findViewById<Button>(R.id.button2).setOnClickListener {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
         */
    }
}