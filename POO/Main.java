import java.util.Scanner;

// ^^ Vamos fazer a Classe Cliente ^^

class Cliente {
    
    private String cpf;
    private String nome;
    private String dataNascimento;

    public Cliente(String cpf, String nome, String dataNascimento) {
        
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public String getDadosCompletos() {
        return "Nome: " + nome + " | CPF: " + cpf + " | Nascimento em: " + dataNascimento;
    }
}

// ^^ Vamos fazer a Classe Conta ^^

class Conta {
    
    private int numero;
    private String usuario;
    private String senha;
    private Cliente cliente;
    private double saldo;

    public Conta(int numero, String usuario, String senha, Cliente cliente, double saldoInicial) {
        
        this.numero = numero;
        this.usuario = usuario;
        this.senha = senha;
        this.cliente = cliente;
        this.saldo = saldoInicial;
        
    }
    
    public int getNumero() { return numero; }
    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }
    public Cliente getCliente() { return cliente; }
    public double getSaldo() { return saldo; }
    
    public void depositar(double valor) {
        
        if (valor > 0) saldo += valor;
    }
    
    public boolean sacar(double valor) {
        
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        
        return false;
    }
    
    public boolean transferir(double valor, Conta destino) {
        
        if (this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        
        return false;
    }
    
    public double investir(double valor, int tipo) {
        
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            double rendimento = 0.0;
            if (tipo == 1) rendimento = valor * 0.03;
            else if (tipo == 2) rendimento = valor * 0.05;
            saldo += valor + rendimento;
            return rendimento;
        }
        
        return -1;
    }
}

// ^^ Vamos fazer a Classe Agência ^^

class Agencia {
    
    private int numero;
    private String nome;
    
    // 2 contas por agência
    
    private Conta conta1;
    private Conta conta2;

    public Agencia(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }
    
    public int getNumero() { return numero; }
    public String getNome() { return nome; }
    
    public void adicionarConta(Conta conta) {
        if (conta1 == null) conta1 = conta;
        else if (conta2 == null) conta2 = conta;
    }
    
    public Conta buscarConta(int numeroConta) {
        
        if (conta1 != null && conta1.getNumero() == numeroConta) return conta1;
        if (conta2 != null && conta2.getNumero() == numeroConta) return conta2;
        return null;
    }
    
    public Conta autenticar(String usuario, String senha) {
        
        if (conta1 != null && conta1.getUsuario().equals(usuario) && conta1.getSenha().equals(senha)) return conta1;
        if (conta2 != null && conta2.getUsuario().equals(usuario) && conta2.getSenha().equals(senha)) return conta2;
        return null;
    }
    
    public Conta buscarPorUsuario(String usuario) {
        
        if (conta1 != null && conta1.getUsuario().equals(usuario)) return conta1;
        if (conta2 != null && conta2.getUsuario().equals(usuario)) return conta2;
        return null;
    }
}

// ^^ Vamos fazer a Classe Transferência ^^

class Transferencia {
    
    private Conta origem;
    private Conta destino;
    private double valor;

    public Transferencia(Conta origem, Conta destino, double valor) {
        
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    public boolean efetuar() {
        
        if (valor > 0 && origem.getSaldo() >= valor) {
            origem.sacar(valor);
            destino.depositar(valor);
            return true;
        }
        
        return false;
    }
}

// ^^ Vamos fazer a nossa Classe Main ^^

public class Main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        // Criando duas agências
        
        Agencia agencia1 = new Agencia(1, "Centro");
        Agencia agencia2 = new Agencia(2, "Bairro");

        // Vamos criar solicitar aos clientes
        
        Cliente cli1 = new Cliente("11111111111", "Estevam", "01/01/1990");
        Cliente cli2 = new Cliente("22222222222", "Maria", "02/02/1995");

        // Vamos criar contas (usuário e senha)
        
        Conta conta1 = new Conta(1, "estevam", "admin1", cli1, 1000.0);
        Conta conta2 = new Conta(2, "maria", "senha2", cli2, 800.0);

        // Adicionando contas nas agências
        
        agencia1.adicionarConta(conta1);
        agencia2.adicionarConta(conta2);

        // Matriz simples de agências
        
        Agencia[] agencias = {agencia1, agencia2};

        // Hora de fazer o login ^^ 
        
        System.out.println("Por favor, insira seu login: ");
        String login_cliente = sc.nextLine();
        System.out.println("Por favor, insira sua senha: ");
        String senha_cliente = sc.nextLine();

        Conta contaLogada = null;
        Agencia agenciaLogada = null;
        
        for (Agencia ag : agencias) {
            
            contaLogada = ag.autenticar(login_cliente, senha_cliente);
            if (contaLogada != null) {
                agenciaLogada = ag;
                break;
            }
        }

        if (contaLogada == null) {
            System.out.println("Acesso incorreto. Por favor, coloque seu login e senha corretamente.");
            return;
        }

        // ^^ Nosso Menu Principal ^^
        
        boolean rodar_codigo = true;

        while (rodar_codigo) {
            System.out.println("\nBem-vindo ao seu sistema bancário!");
            
            System.out.println("=== Seu Menu Principal ===");
            System.out.println("1. Mostrar Saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Saque");
            System.out.println("4. Fazer Pix");
            System.out.println("5. Investimentos");
            System.out.println("6. Transferência");
            System.out.println("7. Mostrar dados do cliente");
            System.out.println("8. Sair");

            System.out.print("Digite a opção desejada: ");
            
            int opcao_escolhida = 0;
            
            try {
                opcao_escolhida = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Opção inválida!");
                continue;
            }

            // Mostrar o Saldo da conta
            
            if (opcao_escolhida == 1) { 
                System.out.printf("O saldo atual é: R$ %.2f\n", contaLogada.getSaldo());
            
            // Mostrar o Depósito da conta

            } else if (opcao_escolhida == 2) { 
                System.out.print("Digite o valor a ser depositado: ");
                double deposito = 0;
                try {
                    deposito = Double.parseDouble(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Valor inválido para depósito.");
                    continue;
                }
                if (deposito > 0) {
                    contaLogada.depositar(deposito);
                    System.out.printf("Depósito realizado! Seu saldo atual é: R$ %.2f\n", contaLogada.getSaldo());
                } else {
                    System.out.println("Valor negado para depósito. Por favor, deposite um valor maior que zero.");
                }
            
            // Mostrar o Saque da conta

            } else if (opcao_escolhida == 3) { 
                System.out.print("Digite o valor a ser sacado: ");
                double saque = 0;
                try {
                    saque = Double.parseDouble(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Valor inválido para saque.");
                    continue;
                }
                if (contaLogada.sacar(saque)) {
                    System.out.printf("Saque realizado! Seu saldo atual é: R$ %.2f\n", contaLogada.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            // Mostrar o Pix da conta

            } else if (opcao_escolhida == 4) { 
                
                System.out.print("Digite o usuário destino do Pix: ");
                String usuarioPix = sc.nextLine();
                Conta contaPix = null;
                
                for (Agencia ag : agencias) {
                    contaPix = ag.buscarPorUsuario(usuarioPix);
                    if (contaPix != null) break;
                }
                
                if (contaPix == null) {
                    System.out.println("Conta Pix não encontrada.");
                    continue;
                }
                
                System.out.print("Digite o valor do Pix: ");
                double valorPix = 0;
                try {
                    valorPix = Double.parseDouble(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Valor inválido para Pix.");
                    continue;
                }
                
                if (contaLogada.transferir(valorPix, contaPix)) {
                    System.out.printf("Pix realizado! Seu saldo atual é: R$ %.2f\n", contaLogada.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            // Mostrar o Investimento da conta

            } else if (opcao_escolhida == 5) { 
                System.out.println("=== Menu de Investimentos ===");
                System.out.println("1. Poupança (3%)");
                System.out.println("2. Fundos de Renda Fixa (5%)");
                System.out.print("Digite a opção: ");
                int escolhaInvestimento = 0;
                try {
                    escolhaInvestimento = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Entrada inválida para investimento.");
                    continue;
                }
                System.out.print("Digite o valor a ser investido: ");
                double valorInvestimento = 0;
                try {
                    valorInvestimento = Double.parseDouble(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Valor inválido para investimento.");
                    continue;
                }
                double rendimento = contaLogada.investir(valorInvestimento, escolhaInvestimento);
                if (rendimento >= 0) {
                    System.out.printf("Investimento realizado! Rendimento: R$ %.2f. Saldo atual: R$ %.2f\n",
                            rendimento, contaLogada.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente ou valor inválido.");
                }

            // Mostrar o Transferência da conta

            } else if (opcao_escolhida == 6) { 
                
                System.out.print("Digite o número da conta de destino: ");
                int numeroContaDestino = 0;
                try {
                    numeroContaDestino = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Número de conta inválido.");
                    continue;
                }
                System.out.print("Digite o número da agência de destino: ");
                int numeroAgenciaDestino = 0;
                try {
                    numeroAgenciaDestino = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Número de agência inválido.");
                    continue;
                }
                System.out.print("Digite o valor a ser transferido: ");
                double valorTransferencia = 0;
                try {
                    valorTransferencia = Double.parseDouble(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Valor inválido para transferência.");
                    continue;
                }
                Agencia agenciaDestino = null;
                for (Agencia ag : agencias) {
                    if (ag.getNumero() == numeroAgenciaDestino) {
                        agenciaDestino = ag;
                        break;
                    }
                }
                if (agenciaDestino == null) {
                    System.out.println("Agência não encontrada.");
                    continue;
                }
                Conta contaDestino = agenciaDestino.buscarConta(numeroContaDestino);
                if (contaDestino == null) {
                    System.out.println("Conta não encontrada.");
                    continue;
                }
                Transferencia transferencia = new Transferencia(contaLogada, contaDestino, valorTransferencia);
                if (transferencia.efetuar()) {
                    System.out.printf("Transferência realizada! Seu saldo atual é: R$ %.2f\n",
                            contaLogada.getSaldo());
                } else {
                    System.out.println("Transferência não realizada. Saldo insuficiente ou valor inválido.");
                }

            // Mostrar o dados da conta


            } else if (opcao_escolhida == 7) { 
                
                System.out.println("=== Dados do Cliente ===");
                System.out.println(contaLogada.getCliente().getDadosCompletos());
                
            // Sair da conta

            } else if (opcao_escolhida == 8) { 
                System.out.println("Programa encerrado!");
                rodar_codigo = false;
            } else {
                System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 8.");
            }
        }
        sc.close();
    }
}