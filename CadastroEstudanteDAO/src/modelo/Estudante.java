package modelo;

public class Estudante {
	private int id;
	private String nome;
	private String curso;
	private double nota;

// Estudante NOVO: o id fica 0 porque quem gera o id e o banco.
	public Estudante(String nome, String curso, double nota) {
		this(0, nome, curso, nota);
	}

// Estudante que VEIO do banco: o id ja existe.
	public Estudante(int id, String nome, String curso, double nota) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String toString() {
		return String.format("Estudante[id=%d, nome=%s, curso=%s, nota=%.1f]", id, nome, curso, nota);

	}
}