package com.example.fire_calculate.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.fire_calculate.R
import com.example.fire_calculate.databinding.FragmentMainBinding
import com.example.fire_calculate.view_model.Main_viewmodel


class MainFragment : Fragment() {
lateinit var binding:FragmentMainBinding
    val model by viewModels<Main_viewmodel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentMainBinding.inflate(inflater,container,false)
        model.changeToolbarTitle("Home",binding.toolbarLayout)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wtext.addTextChangedListener (model.textChangeWatcher(binding.wlayout,false,"",binding.result))
        binding.Ltext.addTextChangedListener (model.textChangeWatcher(binding.llayout,false,"",binding.result))
        binding.htext.addTextChangedListener (model.textChangeWatcher(binding.hlayout,false,"",binding.result))

model.loading.observe(viewLifecycleOwner){
    if (it){
     binding.load.visibility=View.VISIBLE
     binding.result.visibility=View.GONE
    }else{
        binding.load.visibility=View.GONE
        binding.result.visibility=View.VISIBLE
    }
}
        model.area.observe(viewLifecycleOwner){
            model.CO2.value=model.getco2(it.toDouble()).toString()
        }
        model.CO2.observe(viewLifecycleOwner){
            model.normal.value=model.getnormaltube(it.toDouble()).toString()
        }
        model.normal.observe(viewLifecycleOwner){
            model.master.value=model.getmastertube(it.toDouble()).toString()
        }
        model.master.observe(viewLifecycleOwner){
            model.showalldata(binding)
        }
        binding.cal.setOnClickListener {
            model.loading.value=true
            if (!binding.wtext.text.isNullOrEmpty() && !binding.Ltext.text.isNullOrEmpty() && !binding.htext.text.isNullOrEmpty()) {
                model.area.value =
                    model.getarea(
                        binding.wtext.text.toString(),
                        binding.Ltext.text.toString(),
                        binding.htext.text.toString()
                    )
                        .toString()
            }else{
                if (binding.wtext.text.isNullOrEmpty()){
model.enableErrorOrDisable(binding.wlayout,true,"Please enter width")
                }
                if (binding.Ltext.text.isNullOrEmpty()){
                    model.enableErrorOrDisable(binding.llayout,true,"Please enter length")
                }
                if (binding.htext.text.isNullOrEmpty()){
                    model.enableErrorOrDisable(binding.hlayout,true,"Please enter height")
                }
            }

        }


    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {}
    }
}