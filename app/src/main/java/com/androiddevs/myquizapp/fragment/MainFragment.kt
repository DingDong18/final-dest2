package com.androiddevs.myquizapp.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.androiddevs.myquizapp.R
import com.androiddevs.myquizapp.databinding.FragmentMainBinding
import com.androiddevs.myquizapp.model.Question
import com.androiddevs.myquizapp.viewModel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var progressBar: ProgressBar
    lateinit var option: MutableList<String>
    lateinit var cQuestion: Question
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        progressBar = binding.progressBar
        progressBar.max = 100




        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            cQuestion = viewModel.getCurrentQuestion()
            option = cQuestion.options.toMutableList()
            addAnimation()
            option.shuffle()
            setValue()

        })


        viewModel.score.observe(viewLifecycleOwner, Observer {
            progressBar.progress = (progressBar.progress + 25)


        })

        binding.finish.setOnClickListener {
            val checkRadioButton = binding.radioGroup.checkedRadioButtonId
            if (-1 != checkRadioButton) {
                updateAnswerIndex(checkRadioButton)
                if (option[viewModel.answerIndex] == cQuestion.options[0]) {
                    resetRadioButtons()
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.mainFragment, true)
                        .build()
                    findNavController().navigate(
                        //SetUpFragmentDirections.actionSetUpFragmentToRunFragment(),
                        R.id.action_mainFragment_to_finshFragment,
                        savedInstanceState,
                        navOptions
                    )
                } else {
                    wrongAnswerSelected(checkRadioButton)
                }
            }
        }

        binding.next.setOnClickListener {

            val checkRadioButton = binding.radioGroup.checkedRadioButtonId

            if (-1 != checkRadioButton) {

                updateAnswerIndex(checkRadioButton)

                if (option[viewModel.answerIndex] == cQuestion.options[0]) {
                    resetRadioButtons()
                    viewModel.questionIndex++
                    viewModel.getResult()
                    viewModel.score.value = viewModel.score.value?.plus(1)
                    if (viewModel.questionIndex == 3) {
                        binding.next.visibility = View.GONE
                        binding.finish.isVisible = true
                    }
                } else {
                    wrongAnswerSelected(checkRadioButton)
                }

            }
        }

        return binding.root
    }





    fun setValue() {
        binding.question.text = cQuestion.text
        binding.radioButton.text = option[0]
        binding.radioButton2.text = option[1]
        binding.radioButton3.text = option[2]
        binding.radioButton4.text = option[3]

    }



    fun addAnimation() {
        val fadeIn = ObjectAnimator.ofFloat(binding.question, "alpha", 0f, 1f)
        fadeIn.duration = 500
        fadeIn.interpolator = AccelerateInterpolator()
        fadeIn.start()

        val scaleX = ObjectAnimator.ofFloat(binding.question, "scaleX", 0f, 1f)
        scaleX.duration = 500
        scaleX.interpolator = OvershootInterpolator()
        scaleX.start()

        val scaleY = ObjectAnimator.ofFloat(binding.question, "scaleY", 0f, 1f)
        scaleY.duration = 500
        scaleY.interpolator = OvershootInterpolator()
        scaleY.start()

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeIn, scaleX, scaleY)
        animatorSet.start()
    }




    fun resetRadioButtons() {
        val radioGroup = binding.radioGroup
        for (i in 0 until radioGroup.childCount) {
            val radioButton = radioGroup.getChildAt(i) as RadioButton
            radioButton.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.textColorOnSecondary
                )
            )
        }
    }



    fun wrongAnswerSelected(checkRadioButton: Int) {
        when (checkRadioButton) {
            R.id.radioButton -> binding.radioButton.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            R.id.radioButton2 -> binding.radioButton2.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            R.id.radioButton3 -> binding.radioButton3.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
            R.id.radioButton4 -> binding.radioButton4.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )

        }
    }



    fun updateAnswerIndex(checkRadioButton: Int) {
        when (checkRadioButton) {
            R.id.radioButton -> viewModel.updateAnswerIndex(0)
            R.id.radioButton2 -> viewModel.updateAnswerIndex(1)
            R.id.radioButton3 -> viewModel.updateAnswerIndex(2)
            R.id.radioButton4 -> viewModel.updateAnswerIndex(3)

        }
    }


}