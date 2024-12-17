package com.example.artnebulo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeBookActivity extends AppCompatActivity {
    private EditText editTextBookName, editTextBookAuthor, editTextBookId;
    private Button buttonEdit;
    private DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_book);

        editTextBookName = findViewById(R.id.editTextName);
        editTextBookAuthor = findViewById(R.id.editTextAuthor);
        editTextBookId = findViewById(R.id.id_id);
        buttonEdit = findViewById(R.id.edit);
        databaseHelper = new DataBaseHelper(this);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBookInDatabase();
            }
        });
    }

    private void updateBookInDatabase() {
        String bookName = editTextBookName.getText().toString().trim();
        String bookAuthor = editTextBookAuthor.getText().toString().trim();
        String bookIdInput = editTextBookId.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long updateResult = databaseHelper.changeBook(bookName, bookAuthor, bookIdInput);
        if (updateResult > 0) {
            Toast.makeText(this, "Книга успешно изменена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ChangeBookActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка при изменении книги", Toast.LENGTH_SHORT).show();
        }
    }
}