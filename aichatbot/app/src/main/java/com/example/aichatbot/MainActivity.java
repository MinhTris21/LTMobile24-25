package com.example.aichatbot;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText userInput;
    private Button sendButton, voiceButton, settingsButton, quizButton;
    private RecyclerView chatRecyclerView;
    private MessageAdapter messageAdapter;
    private Chatbot chatbot;
    private ActivityResultLauncher<Intent> speechRecognizerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        voiceButton = findViewById(R.id.voiceButton);
        settingsButton = findViewById(R.id.settingsButton);
        quizButton = findViewById(R.id.quizButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        // Set up RecyclerView
        messageAdapter = new MessageAdapter();
        chatRecyclerView.setAdapter(messageAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize chatbot with context
        chatbot = new Chatbot(this);

        // Set up speech recognizer
        speechRecognizerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String spokenText = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                        userInput.setText(spokenText);
                        sendMessage();
                    }
                });

        // Button listeners
        sendButton.setOnClickListener(v -> sendMessage());
        voiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your message");
            speechRecognizerLauncher.launch(intent);
        });
        settingsButton.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        quizButton.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
    }

    private void sendMessage() {
        String input = userInput.getText().toString().trim();
        if (!input.isEmpty()) {
            String timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            messageAdapter.addMessage(new Message(input, true, timestamp));
            String response = chatbot.getResponse(input);
            messageAdapter.addMessage(new Message(response, false, timestamp));
            chatRecyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
            userInput.setText("");
        }
    }
}