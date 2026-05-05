package br.com.vinicius.gestaocliente;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

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

        Random random = new Random();
        int horas = random.nextInt(4);
        int minutos = random.nextInt(59) + 1;

        String tempoFormatado = String.format(Locale.getDefault(), "%dh %02dm", horas, minutos);
        binding.txtTempo.setText("Tempo de permanência: " + tempoFormatado);

        double valorCalculado = 5.0 + (horas * 8.0) + (minutos * 0.15);
        String valorFormatado = String.format(Locale.getDefault(), "Total: R$ %.2f", valorCalculado);
        binding.txtValor.setText(valorFormatado);

        binding.btnPix.setOnClickListener(v -> processarPagamento("PIX"));
        binding.btnCartao.setOnClickListener(v -> processarPagamento("Cartão de Crédito"));
        binding.btnDinheiro.setOnClickListener(v -> processarPagamento("Dinheiro"));
    }

    private void processarPagamento(String formaDePagamento) {
        String mensagem = "Pagamento via " + formaDePagamento + " aprovado! Catraca liberada.";
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();

        finish();
    }
}