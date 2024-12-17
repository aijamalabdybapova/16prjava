package com.example.artnebulo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteBookActivity extends AppCompatActivity {
    private EditText editTextBookId;
    private Button buttonDelete;
    private DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        buttonDelete = findViewById(R.id.delete);
        editTextBookId = findViewById(R.id.editTextAuthor);
        databaseHelper = new DataBaseHelper(this);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBookFromDatabase();
            }
        });
    }

    private void deleteBookFromDatabase() {
        String bookIdInput = editTextBookId.getText().toString().trim();
        if (bookIdInput.isEmpty()) {
            Toast.makeText(this, "Введите ID", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int bookId = Integer.parseInt(bookIdInput);
            long deletionResult = databaseHelper.deleteBookById(bookId);
            if (deletionResult > 0) {
                Toast.makeText(this, "Книга успешно удалена", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteBookActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Ошибка при удалении книги", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректный ID", Toast.LENGTH_SHORT).show();
        }
    }
}