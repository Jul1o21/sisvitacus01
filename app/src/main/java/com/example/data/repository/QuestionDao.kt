package com.example.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<Question>

    @Insert
    suspend fun insertAll(vararg questions: Question)
}
