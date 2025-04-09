import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dados_clientes = new Scanner(System.in);

        // Parâmetros corretos de login e senha
        String login_verificado = "estevam";
        String senha_verificado = "admin1";

        // Saldo inicial do cliente
        double saldo_inicial_cliente = 1000.0;

        // Solicitar o login do cliente
        System.out.println("Por favor, insira seu login: ");
        String login_cliente = dados_clientes.nextLine();

        // Solicitar a senha do cliente
        System.out.println("Por favor, insira sua senha: ");
        String senha_cliente = dados_clientes.nextLine();

        // Verificar se o login e a senha estão corretos
        if (!login_cliente.equals(login_verificado) || !senha_cliente.equals(senha_verificado)) {
            System.out.println("Acesso incorreto. Por favor, coloque seu login e senha corretamente.");
            return; 
        }

        boolean rodar_codigo = true;

        while (rodar_codigo) {
            // Exibir o menu principal
            System.out.println("\nBem-vindo ao seu sistema bancário!");
            System.out.println("=== Seu Menu Principal ===");
            System.out.println("1. Mostrar Saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Saque");
            System.out.println("4. Fazer Pix");
            System.out.println("5. Investimentos");
            System.out.println("6. Sair");

            // Parâmetros corretos de Menu
            int saldo_verificado = 1;
            int deposito_verificado = 2;
            int saque_verificado = 3;
            int pix_verificado = 4;
            int investimento_verificado = 5;
            int sair_verificado = 6;

            System.out.print("Digite a opção desejada: ");
            int opcao_escolhida = dados_clientes.nextInt();
            dados_clientes.nextLine(); 

            // Verificar se digitou corretamente no menu escolhido
            if (opcao_escolhida != saldo_verificado && opcao_escolhida != deposito_verificado &&
                opcao_escolhida != saque_verificado && opcao_escolhida != pix_verificado &&
                opcao_escolhida != investimento_verificado && opcao_escolhida != sair_verificado) {
                System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 6.");
                continue; 
            }

            // Processar a opção escolhida
            if (opcao_escolhida == saldo_verificado) { // Mostrar Saldo
                System.out.printf("O saldo atual é: R$ %.2f\n", saldo_inicial_cliente);

            } else if (opcao_escolhida == deposito_verificado) { // Fazer Depósito
                System.out.print("Digite o valor a ser depositado: ");
                double deposito = 0;
                try {
                    deposito = dados_clientes.nextDouble();
                    dados_clientes.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Valor inválido para depósito.");
                    continue; 
                }
                if (deposito > 0) {
                    saldo_inicial_cliente += deposito;
                    System.out.printf("Depósito realizado com sucesso! O seu saldo atual é: R$ %.2f\n", saldo_inicial_cliente);
                } else {
                    System.out.println("Valor negado para depósito. Por favor, deposite um valor maior que zero.");
                }

            } else if (opcao_escolhida == saque_verificado) { // Fazer Saque
                System.out.print("Digite o valor a ser sacado: ");
                double saque = 0;
                try {
                    saque = dados_clientes.nextDouble();
                    dados_clientes.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Valor inválido para saque.");
                    continue; 
                }
                if (saque > 0 && saque <= saldo_inicial_cliente) {
                    saldo_inicial_cliente -= saque;
                    System.out.printf("Saque realizado com sucesso! O seu saldo atual é: R$ %.2f\n", saldo_inicial_cliente);
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            } else if (opcao_escolhida == pix_verificado) { // Fazer Pix
                System.out.print("Digite a chave PIX: ");
                String chavePix = dados_clientes.nextLine();
                System.out.print("Digite o valor do PIX: ");
                double valorPix = 0;
                try {
                    valorPix = dados_clientes.nextDouble();
                    dados_clientes.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Valor inválido para PIX.");
                    continue; 
                }
                if (valorPix > 0 && valorPix <= saldo_inicial_cliente) {
                    saldo_inicial_cliente -= valorPix;
                    System.out.printf("PIX realizado com sucesso para a chave %s! O seu saldo atual é: R$ %.2f\n", chavePix, saldo_inicial_cliente);
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            } else if (opcao_escolhida == investimento_verificado) { // Fazer Investimentos
                System.out.println("=== Menu de Investimentos ===");
                System.out.println("1. Poupança com Rendimento de 3%");
                System.out.println("2. Fundos de Renda Fixa com Rendimento de 5%");
                System.out.print("Digite a opção desejada: ");
                int escolha_Investimento = 0; 
                try {
                    escolha_Investimento = dados_clientes.nextInt();
                    dados_clientes.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Entrada inválida para investimento.");
                    continue; 
                }
                System.out.print("Digite o valor a ser investido: ");
                double valorInvestimento = 0;
                try {
                    valorInvestimento = dados_clientes.nextDouble();
                    dados_clientes.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Valor inválido para investimento.");
                    continue; 
                }
                if (valorInvestimento > 0 && valorInvestimento <= saldo_inicial_cliente) {
                    saldo_inicial_cliente -= valorInvestimento;
                    double rendimento = 0.0;
                    if (escolha_Investimento == 1) {
                        rendimento = valorInvestimento * 0.03;
                        System.out.printf("Investimento na Poupança realizado! O seu rendimento é: R$ %.2f\n", rendimento);
                    } else if (escolha_Investimento == 2) {
                        rendimento = valorInvestimento * 0.05;
                        System.out.printf("Investimento em Fundos de Renda Fixa realizado! O seu rendimento é: R$ %.2f\n", rendimento);
                    } else {
                        System.out.println("Opção de investimento inválida.");
                        saldo_inicial_cliente += valorInvestimento; 
                    }
                    System.out.printf("Após o investimento, o seu saldo é: R$ %.2f\n", saldo_inicial_cliente);
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            } else if (opcao_escolhida == sair_verificado) { // Sair
                System.out.println("Programa encerrado!");
                rodar_codigo = false;

            }
        }

        dados_clientes.close();
    }
}