package com.example.fire_calculate.Interface

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fire_calculate.databinding.FragmentMainBinding
import com.google.android.material.textfield.TextInputLayout

interface Main_interface {
    fun getarea(x:String,y:String,z:String):Double
    fun getco2(x:Double):Double
    fun getnormaltube(x:Double):Int
    fun getmastertube(x:Double):Int
    fun showalldata(binding: FragmentMainBinding)
    fun enableErrorOrDisable(
        textInputLayout: TextInputLayout,
        flag: Boolean,
        warningTxt: String?
    )
    fun textChangeWatcher(
        textInputLayout: TextInputLayout,
        flag: Boolean,
        warningTxt: String?,result: ConstraintLayout
    ): android.text.TextWatcher

    fun changeToolbarTitle( title: String?, v: View)
}