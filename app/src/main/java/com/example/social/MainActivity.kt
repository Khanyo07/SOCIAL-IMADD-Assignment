package com.example.social

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Find views by ID
        // Reference: IIE Module Manual (2024) - Android Development Basics, p. 45

        val etTime = findViewById<EditText>(R.id.etTime)
        val btnGet = findViewById<Button>(R.id.btnGet)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)

        // 2. Set click listener for "Get Suggestion"
        // Reference: IIE Module Manual (2024) - Event Handling in Android, p. 67

        btnGet.setOnClickListener {
            // Get user input
            val input = etTime.text.toString().trim()

            // Check if empty
            if (input.isEmpty()) {
                tvSuggestion.text = "Please enter a time of day."
                return@setOnClickListener
            }

            // Get suggestion using our function
            val suggestion = getSuggestionForTime(input)
            if (suggestion != null) {
                tvSuggestion.text = suggestion
            } else {
                // Show helpful error
                tvSuggestion.text = "Oops! \"$input\" is not recognised. Try: Morning, Mid-morning, Afternoon, Afternoon Snack Time, Dinner, After Dinner, or Night."
            }
        }

        // 3. Set click listener for "Reset"
        btnReset.setOnClickListener {
            etTime.text.clear()
            tvSuggestion.text = "Your social spark will appear here"
        }
    }

    // Function that returns the suggestion for a given time, or null if not valid
    // Reference: IIE Module Manual (2024) - Kotlin Functions and Control Flow, p. 89
    // This function checks what time the user entered and gives back a suggestion
    // It returns null if the time doesn't match any of our options
    private fun getSuggestionForTime(time: String): String? {

        // Reference: IIE Module Manual (2024) - When Expressions in Kotlin, p. 102
        // Using when statement to match different times of day

        return when (time.lowercase()) {
            "morning" -> "Send a \"Good morning\" text to a family member."
            "mid-morning" -> "Reach out to a colleague with a quick \"Thank you.\""
            "afternoon" -> "Share a funny meme or interesting link with a friend."
            "afternoon snack time" -> "Send a quick \"thinking of you\" message."
            "dinner" -> "Call a friend or relative for a 5-minute catch-up."
            "after dinner" -> "Leave a thoughtful comment on a friend's post."
            "night" -> "Leave a thoughtful comment on a friend's post."
            else -> null
        }
    }
}