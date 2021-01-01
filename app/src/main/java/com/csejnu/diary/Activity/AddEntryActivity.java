package com.csejnu.diary.Activity;import android.annotation.SuppressLint;import android.os.Bundle;import android.view.View;import android.widget.AdapterView;import android.widget.ArrayAdapter;import android.widget.Button;import android.widget.EditText;import android.widget.Spinner;import android.widget.Toast;import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.widget.Toolbar;import com.csejnu.diary.Database.Database;import com.csejnu.diary.Model.Faculty;import com.csejnu.diary.R;import java.util.Arrays;import java.util.Calendar;import java.util.List;public class AddEntryActivity extends AppCompatActivity {    int count = 0;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_add_entry);        Toolbar toolbar = findViewById(R.id.toolbar);        setSupportActionBar(toolbar);        List<String> onushodList = Arrays.asList(getResources().getStringArray(R.array.onushodList));        List<String> biggan_onushod = Arrays.asList(getResources().getStringArray(R.array.biggan_onushod));        List<String> samajil_biggan_onushod = Arrays.asList(getResources().getStringArray(R.array.samajil_biggan_onushod));        List<String> kola_onushod = Arrays.asList(getResources().getStringArray(R.array.kola_onushod));        List<String> bussiness_studies_onushod = Arrays.asList(getResources().getStringArray(R.array.bussiness_studies_onushod));        List<String> aiin_onushod = Arrays.asList(getResources().getStringArray(R.array.aiin_onushod));        List<String> life_and_earth_science_onushod = Arrays.asList(getResources().getStringArray(R.array.life_and_earth_science_onushod));        List<String> doptor_somuh = Arrays.asList(getResources().getStringArray(R.array.doptor_somuh));        final Button submit = findViewById(R.id.submit);        Spinner spinner_dept = findViewById(R.id.spinner_dept);        Spinner spinner_title = findViewById(R.id.spinner_title);        ArrayAdapter<String> spinnerArrayAdapter_title = new ArrayAdapter<>(this,                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.titles));        spinner_title.setAdapter(spinnerArrayAdapter_title);        Spinner spinner_onushod = findViewById(R.id.spinner_onushod);        ArrayAdapter<String> spinnerArrayAdapter_onushod = new ArrayAdapter<>(this,                android.R.layout.simple_spinner_dropdown_item, onushodList);        spinner_onushod.setAdapter(spinnerArrayAdapter_onushod);        spinner_onushod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {                if (position == 0) {                    ArrayAdapter spinnerArrayAdapter_dept = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, biggan_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept);                } else if (position == 1) {                    ArrayAdapter spinnerArrayAdapter_dept2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, samajil_biggan_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept2);                } else if (position == 2) {                    ArrayAdapter spinnerArrayAdapter_dept3 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, kola_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept3);                } else if (position == 3) {                    ArrayAdapter spinnerArrayAdapter_dept4 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, bussiness_studies_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept4);                } else if (position == 4) {                    ArrayAdapter spinnerArrayAdapter_dept5 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, aiin_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept5);                } else if (position == 5) {                    ArrayAdapter spinnerArrayAdapter_dept6 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, life_and_earth_science_onushod);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept6);                } else if (position == 6) {                    ArrayAdapter spinnerArrayAdapter_dept7 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, doptor_somuh);                    spinner_dept.setAdapter(spinnerArrayAdapter_dept7);                }            }            public void onNothingSelected(AdapterView<?> parent) {                count = 0;            }        });        submit.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                String onusud_name = spinner_onushod.getSelectedItem().toString();                String dept_name = spinner_dept.getSelectedItem().toString();                String title_name = spinner_title.getSelectedItem().toString();                @SuppressLint("CutPasteId")                String name = ((EditText) findViewById(R.id.name)).getText().toString();                String number_home = ((EditText) findViewById(R.id.numeber_home)).getText().toString();                String number_office = ((EditText) findViewById(R.id.numeber_office)).getText().toString();                @SuppressLint("CutPasteId") String email = ((EditText) findViewById(R.id.address_name)).getText().toString();                String fax = ((EditText) findViewById(R.id.address_fax)).getText().toString();                if (onusud_name.equals("") || dept_name.equals("") || name.equals("") || title_name.equals("") || fax.length() < 5 || email.length() < 5) {                    Toast.makeText(AddEntryActivity.this, "Invalid Data, Check again...", Toast.LENGTH_SHORT).show();                } else if (number_home.length() < 11 || number_office.length() < 4) {                    Toast.makeText(AddEntryActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();                } else if (name.length() < 3) {                    Toast.makeText(AddEntryActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();                } else {                    String random_id = String.valueOf(Calendar.getInstance().getTimeInMillis());                    Faculty faculty = new Faculty(random_id, name, onusud_name, title_name, dept_name, number_home, number_office, email, fax);                    Database db = new Database(getApplicationContext());                    db.insertFaculty(faculty);                }            }        });    }}