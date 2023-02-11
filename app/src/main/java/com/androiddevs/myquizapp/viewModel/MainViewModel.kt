package com.androiddevs.myquizapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androiddevs.myquizapp.utils.Questions

class MainViewModel() : ViewModel() {



    var currentQuestion :MutableLiveData<Int> = MutableLiveData()
    var answerIndex =0
    var questionIndex =0
    val score : MutableLiveData<Int> = MutableLiveData()

    init {
        currentQuestion.value = 0
        score.value =0

    }
    fun getCurrentQuestion() = Questions.question[currentQuestion.value!!]

    fun updateAnswerIndex(index : Int){
        answerIndex = index
    }

    fun getResult(){
        currentQuestion.value =questionIndex
    }








}