package com.androiddevs.myquizapp.utils

import com.androiddevs.myquizapp.model.Question

object Questions {


      val question = mutableListOf<Question>(
         Question(text ="What is the capital of France?", listOf("Paris","London","Berlin","Chicago")),
         Question(text ="Who wrote the famous novel \"To Kill a Mockingbird\"?", listOf("Harper Lee","William Faulkner","F.Scott Fitzgerald","F. Scott Fitzgerald")),
         Question(text ="What is the largest ocean in the world?", listOf("Pacific Ocean","Atlantic Ocean","Southern Ocean","Indian Ocean")),
         Question(text ="Who painted the famous artwork \"The Starry Night\"?", listOf(" Vincent van Gogh","Pablo Picasso","Claude Monet","Rembrandt"))

     )


}