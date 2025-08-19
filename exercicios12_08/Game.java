import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Game {
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private int classificacaoEtaria;

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final NumberFormat MOEDA = NumberFormat.getCurrencyInstance(PT_BR);

    // 🔹 Construtor principal (completo)
    public Game(String nome, double preco, String categoria, int classificacao) {
        setNome(nome); // usa os setters para validar
        setPreco(preco);
        setCategoria(categoria);
        setClassificacaoEtaria(classificacao);
    }

    // 🔹 Construtor só com nome e preço (categoria e classificação padrão)
    public Game(String nome, double preco) {
        this(nome, preco, "Indefinida", 0);
    }

    // 🔹 Construtor só com nome (preço = 0, categoria padrão, livre)
    public Game(String nome) {
        this(nome, 0.0, "Indefinida", 0);
    }

    // 🔹 Construtor vazio (para frameworks ou criação manual depois)
    public Game() {
        this("Sem nome", 0.0, "Indefinida", 0);
    }

    // ================= GETTERS =================
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public int getClassificacaoEtaria() { return classificacaoEtaria; }
    
    

    // ================= SETTERS COM VALIDAÇÃO =================
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do jogo não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Categoria inválida.");
        }
        this.categoria = categoria;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.preco = BigDecimal.valueOf(preco);
    }

    public void setClassificacaoEtaria(int idade) {
        int[] permitidas = {0, 3, 7, 10, 12, 14, 16, 18};
        boolean valida = false;
        for (int faixa : permitidas) {
            if (idade == faixa) {
                valida = true;
                break;
            }
        }
        if (!valida) {
            throw new IllegalArgumentException("Classificação etária inválida: " + idade);
        }
        this.classificacaoEtaria = idade;
    }

    // ================= OUTROS MÉTODOS =================
    public static void exibirInformacoes(Game jogo) {
        System.out.println("Nome do jogo: " + jogo.getNome());
        System.out.println("Categoria: " + jogo.getCategoria());
        System.out.println("Preço do jogo: " + MOEDA.format(jogo.getPreco()));
        System.out.println("Classificação etária: " + jogo.getClassificacaoEtaria() + "+");
        System.out.println("-----------------------------------");
    }

    @Override
    public String toString() {
        return nome + " | " + genero + " | " +
            MOEDA.format(preco) + " | " +
            classificacaoEtaria + "+";
}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(nome, game.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
