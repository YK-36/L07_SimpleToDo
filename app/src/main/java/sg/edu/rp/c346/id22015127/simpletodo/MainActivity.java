package sg.edu.rp.c346.id22015127.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    EditText todo;
    Button add;
    Button delete;
    Button clear;
    ListView todolist;
    ArrayList<String> altodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin = findViewById(R.id.spinner);
        todo = findViewById(R.id.Text);
        add = findViewById(R.id.buttonAddItem);
        delete = findViewById(R.id.buttonDeleteItem);
        clear = findViewById(R.id.buttonClearItem);
        todolist = findViewById(R.id.todoList);
        altodo = new ArrayList<String>();
        ArrayAdapter aatodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, altodo);
        todolist.setAdapter(aatodo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = todo.getText().toString();
                altodo.add(newTask);
                todolist.setAdapter(aatodo);
                aatodo.notifyDataSetChanged();
                todo.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(todo.getText().toString());
                if (pos+1 <= altodo.size()){
                    altodo.remove(pos);
                    todolist.setAdapter(aatodo);
                    aatodo.notifyDataSetChanged();
                    todo.setText("");
                }else {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    todo.setText("");
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altodo.clear();
                todo.setText("");
                todolist.setAdapter(aatodo);
                aatodo.notifyDataSetChanged();
                delete.setEnabled(false);
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        todo.setInputType(InputType.TYPE_CLASS_TEXT);
                        todo.setHint("Type in a new task here");
                        todo.setText("");
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        break;
                    case 1:
                        todo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        todo.setHint("Type in the index of the task to be removed");
                        add.setEnabled(false);
                        todo.setText("");
                        if (altodo.size()!=0){
                            delete.setEnabled(true);
                        }else {
                            delete.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}