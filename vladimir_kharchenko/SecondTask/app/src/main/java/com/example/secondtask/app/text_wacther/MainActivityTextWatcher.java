package com.example.secondtask.app.text_wacther;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vladimir_kharchenko on 22.02.14.
 */
public final class MainActivityTextWatcher implements TextWatcher {

    private EditText emailEditText;

    private EditText messageEditText;

    private Button enabledButton;

    public MainActivityTextWatcher(EditText emailEditText, EditText messageEditText,
                                   Button enabledButton) {
        this.emailEditText = emailEditText;
        this.messageEditText = messageEditText;
        this.enabledButton = enabledButton;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isValidEmail(emailEditText.getText()) && messageEditText.length() != 0) {
            enabledButton.setEnabled(true);
        } else {
            enabledButton.setEnabled(false);
        }
    }

    public boolean isValidEmail(CharSequence email){
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}
