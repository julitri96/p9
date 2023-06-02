package com.example.pertemuan9restapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pertemuan9restapi.model.request.DataMahasiswa
import com.example.pertemuan9restapi.model.response.ResponseDataMahasiswa
import com.example.pertemuan9restapi.model.response.ResponseDetailMahasiswa
import com.example.pertemuan9restapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMahasiswa : ViewModel() {
    private val getDataMahasiswa = MutableLiveData<List<DataMahasiswa>?>()
    private val getDetailMahasiswa = MutableLiveData<ResponseDetailMahasiswa?>() // ini

    fun getDataMahasiswa() : MutableLiveData<List<DataMahasiswa>?>{
        return getDataMahasiswa
    }

    // Kamu tadi pake function untuk return valuenya, makannya jadi error. Seharusnya yang dipake value yang udah
    // di deklarasiin di atas pada viewmodel
    fun getDetailDataMasiswa() : MutableLiveData<ResponseDetailMahasiswa?>{
        return getDetailMahasiswa
    }

    fun showDataMahasiswa(){
        ApiClient.instance.getDataMahasiswa().enqueue(object : Callback<ResponseDataMahasiswa>{
            override fun onResponse(
                call: Call<ResponseDataMahasiswa>,
                response: Response<ResponseDataMahasiswa>
            ) {
               if (response.isSuccessful){
                   getDataMahasiswa.postValue(response.body()?.data)
               }else{
                   getDataMahasiswa.postValue(null)
               }
            }

            override fun onFailure(call: Call<ResponseDataMahasiswa>, t: Throwable) {
                getDataMahasiswa.postValue(null)
            }

        })
    }

    fun getDetailData(nim : String){
        ApiClient.instance.getDetailMahasiswa(nim).enqueue(object : Callback<ResponseDetailMahasiswa>{
            override fun onResponse(
                call: Call<ResponseDetailMahasiswa>,
                response: Response<ResponseDetailMahasiswa>
            ) {
                if (response.isSuccessful){
                    getDetailMahasiswa.postValue(response.body())
                }else{
                    getDetailMahasiswa.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDetailMahasiswa>, t: Throwable) {
                getDetailMahasiswa.postValue(null)
            }

        })
    }
}