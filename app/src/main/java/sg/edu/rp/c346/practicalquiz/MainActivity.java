package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Spinner spClass;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAge = findViewById(R.id.editTextAge);
        etName = findViewById(R.id.editTextName);
        spClass = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.button);



    }


    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        int intAge = Integer.parseInt(etAge.getText().toString());
        int selectedPosition = spClass.getSelectedItemPosition();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name", strName);
        prefEdit.putInt("age", intAge );
        prefEdit.putInt("spinner", selectedPosition);

        prefEdit.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("name", null);
        int intAge = prefs.getInt("age", 0);
        int spinner = prefs.getInt("spinner", 0);

        etName.setText(strName);
        etAge.setText(Integer.toString(intAge));
        spClass.setSelection(spinner);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT );
                toast.show();
            }
        });

    }
}
