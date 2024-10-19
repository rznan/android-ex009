package br.com.renan.ex009;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renan.ex009.model.StorageUnit;

/*
* @author: renan santos carvalho
*/
public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Spinner spConversionType;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etInput = findViewById(R.id.etInput);
        spConversionType = findViewById(R.id.spConversionType);
        tvResult = findViewById(R.id.tvResult);

        populateSpinner();
        findViewById(R.id.btnConvert).setOnClickListener(e -> convertViewValue());

    }

    private void convertViewValue() {
        String storageunitDescription = (String) spConversionType.getSelectedItem();
        StorageUnit storageUnit = StorageUnit.getStorageUnitByDescription(storageunitDescription);
        if(storageUnit == null) {
            return;
        }

        long bitValue = Long.parseLong(etInput.getText().toString());
        tvResult.setText(String.valueOf(storageUnit.convertBits(bitValue)));
    }


    private void populateSpinner() {
        List<String> list = Arrays.stream(StorageUnit.values()).map(StorageUnit::getDescription).collect(Collectors.toList());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spConversionType.setAdapter(adapter);
    }
}