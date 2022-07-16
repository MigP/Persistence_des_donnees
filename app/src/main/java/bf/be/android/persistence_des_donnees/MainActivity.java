package bf.be.android.persistence_des_donnees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {

    private EditText typedMessage;
    private Button saveBtn, showBtn, deleteBtn;
    private TextView savedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typedMessage = findViewById(R.id.typedMessage);
        saveBtn = findViewById(R.id.saveBtn);
        showBtn = findViewById(R.id.showBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        savedMessage = findViewById(R.id.savedMessage);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveMessage() throws IOException {
        File file = new File(getFilesDir(), "info.dat");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(typedMessage.getText().toString());
        fileWriter.close();
    }

    private void showMessage() throws IOException {
        File file2 = new File(getFilesDir(), "info.dat");
        FileReader fileReader = new FileReader(file2);
        CharBuffer charBuffer = CharBuffer.allocate(24);
        fileReader.read(charBuffer);
        fileReader.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charBuffer.length(); i++) {
            sb.append(charBuffer.get(i));
        }
        savedMessage.setText(sb.toString());
    }

    private void deleteMessage() throws IOException {
        File file = new File(getFilesDir(), "info.dat");
        file.delete();
        savedMessage.setText("");
        showMessage();
    }
}