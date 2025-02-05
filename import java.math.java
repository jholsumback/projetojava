import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.Arrays;
import java.time.Period;

// Classe de Pessoa
public class Pessoa {
    String nome;
    LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + dataNascimento);
    }
}

// Classe de Funcionário
public class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Salário: " + salario);
        System.out.println("Função: " + funcao);
    }
}

// Classe principal para executar as funções solicitadas
public class Main {
    public static void main(String[] args) {
        // Criando funcionários
        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2210.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 1), new BigDecimal("10820.02"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("21031.87"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2458.15"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1740.97"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4479.02"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3319.20"), "Gerente"),
            new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1767.54"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("3079.92"), "Gerente")
        );

        // Exibindo os dados de cada funcionário
        for (Funcionario f : funcionarios) {
            f.mostrarDados();
        }

        // Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            funcionariosPorFuncao
                .computeIfAbsent(f.funcao, k -> new ArrayList<>())
                .add(f);
        }

        // Exibindo funcionários por função
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("\nFunção: " + funcao);
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.println(" - " + f.nome);
            }
        }

        // Funcionários que fazem aniversário em Outubro e Dezembro
        System.out.println("Funcionários que fazem aniversário em Outubro e Dezembro:");
        for (Funcionario f : funcionarios) {
            int mesNascimento = f.dataNascimento.getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.println(" - " + f.nome + " (Data de Nascimento: " + f.dataNascimento + ")");
            }
        }

        // Encontrando o funcionário mais velho
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario f : funcionarios) {
            if (f.dataNascimento.isBefore(maisVelho.dataNascimento)) {
                maisVelho = f;
            }
        }

        // Calculando a idade
        int idade = Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
        System.out.println("\nFuncionário mais velho: ");
        System.out.println("Nome: " + maisVelho.nome);
        System.out.println("Idade: " + idade + " anos");

        // Ordenando a lista de funcionários por ordem alfabética
        Collections.sort(funcionarios, Comparator.comparing(f -> f.nome));
        System.out.println("\nLista de funcionários em ordem alfabética: ");
        for (Funcionario f : funcionarios) {
            System.out.println(" - " + f.nome);
        }

        // Calculando o total dos salários
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.salario);
        }
        System.out.println("\nTotal dos salários dos funcionários: R$ " + totalSalarios);

        // Calculando quantos salários mínimos cada funcionário ganha (salário mínimo R$1212)
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal quantidadeSalarios = f.salario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(" - " + f.nome + ": " + quantidadeSalarios + " salários mínimos");
        }
    }
}






