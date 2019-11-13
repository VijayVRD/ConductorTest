package com.example.conductor

import android.util.Log
import androidx.lifecycle.ViewModel

class ViewModelTest : ViewModel(){

    private val TAG = this::class.java.simpleName
    init {
        Log.d(TAG, "Init ViewModel")
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }
}