package com.androiddevs.myquizapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.myquizapp.R
import com.androiddevs.myquizapp.databinding.FragmentMainBinding
import com.androiddevs.myquizapp.model.Question
import com.androiddevs.myquizapp.utils.Questions.question
import com.androiddevs.myquizapp.viewModel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    lateinit var option: MutableList<String>
    lateinit var cQuestion: Question
    lateinit var viewModel: MainViewModel
    private var questionIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        Toast.makeText(requireContext()," qes = ${viewModel.questionIndex}",Toast.LENGTH_SHORT).show()



        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
                cQuestion = viewModel.getCurrentQuestion()
                option = cQuestion.options.toMutableList()
            option.shuffle()
               setValue()




        })

        binding.next.setOnClickListener {


            val checkRadioButton = binding.radioGroup.checkedRadioButtonId

            if(-1 != checkRadioButton){

                when(checkRadioButton){
                    R.id.radioButton -> viewModel.updateAnswerIndex(0)
                    R.id.radioButton2 -> viewModel.updateAnswerIndex(1)
                    R.id.radioButton3 -> viewModel.updateAnswerIndex(2)
                    R.id.radioButton4 -> viewModel.updateAnswerIndex(3)

                }
                val b = cQuestion.options[0]
                val c = option[viewModel.answerIndex]
                Toast.makeText(requireContext()," this is $b and $c",Toast.LENGTH_SHORT).show()
                if( option[viewModel.answerIndex]== cQuestion.options[0] ){
                    viewModel.questionIndex++
                    viewModel.getResult()
                    viewModel.score++
                    if(viewModel.questionIndex==3){
                        binding.next.text = "Finish"
                    }


                }
//               val b = currentQuestion.options[0]
//                val c = option[answerindex]
//                Toast.makeText(requireContext()," this is $b and $c",Toast.LENGTH_SHORT).show()
//                if(option[answerindex] == currentQuestion.options[0]){
//                    questionIndex++
//                    Toast.makeText(requireContext()," this is $questionIndex",Toast.LENGTH_SHORT).show()
//                    currentQuestion = question[questionIndex]
//
//                    binding.question.text = currentQuestion.text
//
//                    currentQuestion.options.toMutableList()
//                }
            }
        }

        return binding.root
    }


    fun setValue(){
        binding.question.text = cQuestion.text
        binding.radioButton.text = option[0]
        binding.radioButton2.text = option[1]
        binding.radioButton3.text = option[2]
        binding.radioButton4.text = option[3]

        //option.shuffle()


    }


}