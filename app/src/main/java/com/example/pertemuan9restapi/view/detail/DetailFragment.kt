package com.example.pertemuan9restapi.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pertemuan9restapi.R
import com.example.pertemuan9restapi.databinding.FragmentDetailBinding
import com.example.pertemuan9restapi.viewmodel.ViewModelMahasiswa

class DetailFragment : Fragment() {
    lateinit var viewModel: ViewModelMahasiswa
    lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nim = arguments?.getString(  "nim")

        viewModel = ViewModelProvider(requireActivity()).get(ViewModelMahasiswa::class.java)
        viewModel.getDetailDataMasiswa().observe(viewLifecycleOwner){
            if (it != null){
                // Tadi salah karena tipe datanya masih any, seharusnya String.
                binding.txtnim.text = it.data?.nIM
                binding.txtnama.text = it.data?.nama
                binding.txtTelepon.text = it.data?.telepon
            }else{
                Toast.makeText(context, "Data tidak ditentukan", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getDetailData(nim!!)

    }


}