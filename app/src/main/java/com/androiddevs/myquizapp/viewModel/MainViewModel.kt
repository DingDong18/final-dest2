package com.androiddevs.myquizapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androiddevs.myquizapp.utils.Questions

class MainViewModel() : ViewModel() {



    var currentQuestion :MutableLiveData<Int> = MutableLiveData()
    var answerIndex =0
    var questionIndex =0
    var score =0
    val currentOption : MutableLiveData<Int> = MutableLiveData()

    init {
        currentQuestion.value = 0
        currentOption.value = currentQuestion.value

    }
    fun getCurrentQuestion() = Questions.question[currentQuestion.value!!]

    fun updateAnswerIndex(index : Int){
        answerIndex = index
    }

    fun getResult(){
        currentQuestion.value =questionIndex
    }








}