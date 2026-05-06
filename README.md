#  Sistema de Estacionamento - Módulo Cliente

Este é o aplicativo voltado para o motorista (cliente) do Sistema de Gestão de Estacionamento. Seu objetivo é permitir que o usuário consulte o status do seu veículo no pátio e realize a simulação do pagamento do ticket para liberação da catraca.

## ️ Tecnologias Utilizadas
* **Android / Java:** Desenvolvimento nativo com interface fluida.
* **ViewBinding:** Interação segura e direta com os elementos de interface (XML).
* **Regex (Expressões Regulares):** Validação de formato padrão de placas veiculares.
* **Intent / URI IPC:** Comunicação inter-processos para envio de dados ao aplicativo do Gestor.

##  Como funciona a Interligação (Deep Links)
Para que o pagamento feito no celular do cliente reflita no painel do administrador sem a necessidade de um servidor em nuvem (API REST), este projeto utiliza comunicação local nativa do Android via **Deep Links**.

Após a aprovação simulada do pagamento (seja via PIX, Cartão ou Dinheiro), o App Cliente aciona a classe `Intent` do Android e dispara uma URL profunda contendo a placa do veículo (`appadmin://sincronizar?placa=PLACA_AQUI`). Como o App Gestor está programado para ouvir essa exata URL, o próprio sistema operacional faz a ponte, fechando o módulo do cliente e abrindo o módulo do gestor já com os dados sincronizados.

##  Como executar corretamente (Aviso Importante)
Este aplicativo é metade de um ecossistema. Para que o fechamento da vaga e a comunicação funcionem, **os dois aplicativos (Gestor e Cliente) devem estar rodando juntos no mesmo emulador.**

1. Certifique-se de que o **App Gestor** já foi instalado e aberto pelo menos uma vez no seu dispositivo.
2. Abra este aplicativo (**Cliente**).
3. Na busca, digite uma placa válida (ex: `ABC-1234`).
4. Selecione a forma de pagamento e finalize. O aplicativo do Cliente fechará automaticamente e passará o controle (e os dados da placa) para o aplicativo do Gestor.

---
*Desenvolvido por Vinicius Cordeiro.*