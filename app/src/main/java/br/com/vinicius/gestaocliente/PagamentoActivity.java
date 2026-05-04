package br.com.vinicius.gestaocliente;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import br.com.vinicius.gestaocliente.databinding.ActivityPagamentoBinding;

public class PagamentoActivity extends AppCompatActivity {

    private ActivityPagamentoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String placaRecebida = getIntent().getStringExtra("PLACA_VEICULO");

        if (placaRecebida != null) {
            binding.txtPlacaPagamento.setText("Placa: " + placaRecebida);
        }

        binding.btnPagar.setOnClickListener(v -> {
            Toast.makeText(this, "Pagamento aprovado! Catraca liberada.", Toast.LENGTH_LONG).show();
            finish(); // Volta para a tela inicial
        });
    }
}