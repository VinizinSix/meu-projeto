import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private String nome;
    private String email;
    private BigDecimal saldo;
    private List<Game> bibliotecaJogos; // associação: Cliente tem vários Games

    // 🔹 Construtor completo
    public Cliente(String nome, String email, double saldoInicial) {
        setNome(nome);
        setEmail(email);
        setSaldo(saldoInicial);
        this.bibliotecaJogos = new ArrayList<>();
    }

    // 🔹 Construtor sem saldo (saldo começa em 0)
    public Cliente(String nome, String email) {
        this(nome, email, 0.0);
    }
    public void adicionarSaldo(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(valor);
        } else {
            throw new IllegalArgumentException("Valor inválido para adicionar saldo.");
        }
    }

    // 🔹 Construtor só com nome (email padrão e saldo = 0)
    public Cliente(String nome) {
        this(nome, "sememail@dominio.com", 0.0);
    }

    // 🔹 Construtor vazio (útil para frameworks ou inicialização manual)
    public Cliente() {
        this("Sem nome", "sememail@dominio.com", 0.0);
    }

    // ================= GETTERS =================
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public BigDecimal getSaldo() { return saldo; }
    public List<Game> getBibliotecaJogos() { return bibliotecaJogos; }

    // ================= SETTERS COM VALIDAÇÃO =================
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }
        this.saldo = BigDecimal.valueOf(saldo);
    }

    // ================= MÉTODOS =================
    public void adicionarSaldo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para adicionar deve ser positivo.");
        }
        this.saldo = this.saldo.add(BigDecimal.valueOf(valor));
    }

    public void comprarJogo(Game jogo) {
        if (jogo.getPreco().compareTo(saldo) > 0) {
            throw new IllegalStateException("Saldo insuficiente para comprar o jogo: " + jogo.getNome());
        }
        if (bibliotecaJogos.contains(jogo)) {
            throw new IllegalStateException("Jogo já está na biblioteca: " + jogo.getNome());
        }
        bibliotecaJogos.add(jogo);
        saldo = saldo.subtract(jogo.getPreco());
        System.out.println("Compra realizada com sucesso: " + jogo.getNome());
    }

    public void listarBiblioteca() {
        System.out.println("📚 Biblioteca de " + nome + ":");
        if (bibliotecaJogos.isEmpty()) {
            System.out.println("Nenhum jogo comprado ainda.");
        } else {
            for (Game g : bibliotecaJogos) {
                System.out.println("- " + g);
            }
        }
    }

    @Override
    public String toString() {
        return nome + " (" + email + ") — Saldo: R$ " + saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
