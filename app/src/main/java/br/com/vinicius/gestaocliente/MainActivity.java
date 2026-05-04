package br.com.vinicius.gestaocliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;

import br.com.vinicius.gestaocliente.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBuscarPlaca.setOnClickListener(v -> {
            String placaDigitada = binding.edtPlacaBusca.getText().toString()
                    .toUpperCase().replaceAll("[^A-Z0-9]", "");

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null && getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            if (placaDigitada.isEmpty()) {
                binding.edtPlacaBusca.setError("Digite a sua placa!");
                binding.txtResultado.setText("");
                return;
            }

            if (placaDigitada.equals("ABC1234")) {
                binding.txtResultado.setText("✅ Veículo encontrado no pátio!");
                binding.txtResultado.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

                Intent intent = new Intent(MainActivity.this, PagamentoActivity.class);
                intent.putExtra("PLACA_VEICULO", placaDigitada);
                startActivity(intent);

                binding.edtPlacaBusca.setText("");
            } else {
                binding.txtResultado.setText("❌ Veículo não encontrado.\n\nVerifique se digitou corretamente.");
                binding.txtResultado.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });
    }
}