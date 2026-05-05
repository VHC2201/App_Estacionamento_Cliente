package br.com.vinicius.gestaocliente;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

import br.com.vinicius.gestaocliente.databinding.ActivityPagamentoBinding;

public class PagamentoActivity extends AppCompatActivity {

    private ActivityPagamentoBinding binding;

    private String placaGlobal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        placaGlobal = getIntent().getStringExtra("PLACA_VEICULO");
        if (placaGlobal != null) {
            binding.txtPlacaPagamento.setText("Placa: " + placaGlobal);
        }

        Random random = new Random();
        int horas = random.nextInt(4);
        int minutos = random.nextInt(59) + 1;

        String tempoFormatado = String.format(Locale.getDefault(), "%dh %02dm", horas, minutos);
        binding.txtTempo.setText("Tempo de permanência: " + tempoFormatado);

        double valorCalculado = 5.0 + (horas * 8.0) + (minutos * 0.15);
        String valorFormatado = String.format(Locale.getDefault(), "Total: R$ %.2f", valorCalculado);
        binding.txtValor.setText(valorFormatado);

        binding.btnPix.setOnClickListener(v -> processarPagamento("PIX", placaGlobal));
        binding.btnCartao.setOnClickListener(v -> processarPagamento("Cartão de Crédito", placaGlobal));
        binding.btnDinheiro.setOnClickListener(v -> processarPagamento("Dinheiro", placaGlobal));
    }

    private void processarPagamento(String formaDePagamento, String placa) {
        Toast.makeText(this, "Pagamento via " + formaDePagamento + " aprovado!", Toast.LENGTH_LONG).show();

        android.net.Uri uriDeepLink = android.net.Uri.parse("appadmin://sincronizar?placa=" + placa);
        android.content.Intent intentSincronizacao = new android.content.Intent(android.content.Intent.ACTION_VIEW, uriDeepLink);

        intentSincronizacao.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intentSincronizacao);
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "ERRO: O AppAdmin não foi encontrado ou atualizado no emulador!", Toast.LENGTH_LONG).show();
        }

        finish();
    }
}