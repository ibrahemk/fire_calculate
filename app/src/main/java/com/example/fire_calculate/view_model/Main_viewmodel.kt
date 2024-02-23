package com.example.fire_calculate.view_model

import android.text.Editable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fire_calculate.Interface.Main_interface
import com.example.fire_calculate.R
import com.example.fire_calculate.databinding.FragmentMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Main_viewmodel@Inject constructor(): ViewModel(),Main_interface {
    var area: MutableLiveData<String> = MutableLiveData()
     var CO2:MutableLiveData<String> = MutableLiveData()
     var master:MutableLiveData<String> = MutableLiveData()
     var normal:MutableLiveData<String> = MutableLiveData()
     var loading:MutableLiveData<Boolean> = MutableLiveData()

    override fun getarea(x: String, y: String, z: String): Double {
        loading.value=false
        return (x.toDouble()*y.toDouble()*z.toDouble())
    }

    override fun getco2(x: Double): Double {
     return if (x>55){
          x*1.33
      }else{
         x*1.66
      }
    }

    override fun getnormaltube(x: Double): Int  {
        val tube= (x/45).toString()
        return if(tube.toDoubleOrNull()!=null){
            (tube.toDouble()+1).toInt()
        }else{
            (tube.toInt())
        }
    }

    override fun getmastertube(x: Double): Int {
        val tube= (x/6).toString()

        return if(tube.toDoubleOrNull()!=null){
            (tube.toDouble()+1).toInt()
        }else{
            (tube.toInt())
        }
    }

    override fun showalldata(binding:FragmentMainBinding) {
        if (area.value!=null){ binding.areat.text = area.value }
        if (CO2.value!=null){ binding.cot.text = CO2.value }
        if (master.value!=null){ binding.mastert.text = master.value }
        if (normal.value!=null&&master.value!=null){ binding.normalt.text = ((normal.value!!.toInt()-master.value!!.toInt()).toString()) }
    }
    override  fun enableErrorOrDisable(
        textInputLayout: TextInputLayout,
        flag: Boolean,
        warningTxt: String?
    ) {
        if (flag&&!textInputLayout.isErrorEnabled) {
            textInputLayout.isErrorEnabled = flag
            textInputLayout.error = warningTxt
        }else if (!flag){
            textInputLayout.isErrorEnabled = flag
            textInputLayout.error = ""
        }
    }


    override  fun textChangeWatcher(
        textInputLayout: TextInputLayout,
        flag: Boolean,
        warningTxt: String?
    ,result: ConstraintLayout
    ): android.text.TextWatcher {
        return object : android.text.TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                enableErrorOrDisable(textInputLayout, flag, warningTxt)
                result.visibility=View.GONE

            }

            override fun afterTextChanged(s: Editable) {}
        }
    }
    override fun changeToolbarTitle( title: String?, v: View) {
        val toolbar = v.findViewById<View>(R.id.toolbar) as MaterialToolbar
        toolbar.title = title

    }

}