package com.bignerdranch.android.geoquiz

import Question
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val questionBank = listOf(
//        EXERCISE 1C - ADDED hasCheated variable
        Question(R.string.question_australia, true, false, false),
        Question(R.string.question_oceans, true, false, false),
        Question(R.string.question_africa, false, false, false),
        Question(R.string.question_americas, false, false, false),
        Question(R.string.question_asia, true, false, false)
    )

    var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        if (currentIndex == 0) {
            currentIndex = questionBank.size - 1
        }
        else {
            currentIndex = (currentIndex - 1) % questionBank.size
        }
    }
}