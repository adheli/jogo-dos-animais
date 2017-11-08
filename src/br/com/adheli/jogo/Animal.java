package br.com.adheli.jogo;

public class Animal {

	private String especie;
	private String habitat;
	private String acao;
	
	public Animal(String especie, String habitat, String acao) {
		this.especie = especie;
		this.habitat = habitat;
		this.acao = acao;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	@Override
	public String toString() {
		return this.especie + " " + this.acao;
	}
}
