package br.com.adheli.jogo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Servico {

	// animais
	Animal macaco = new Animal("Macaco", "terra", "pula");
	Animal baleia = new Animal("Baleia", "agua", "nada");
	Animal papagaio = new Animal("Papagaio", "terra", "bica");

	private List<Animal> animais = new ArrayList<Animal>();

	private List<String> habitats = new ArrayList<>();

	// preenche as listas padrões
	private void init() {
		this.animais.add(baleia);
		this.animais.add(macaco);
		this.animais.add(papagaio);

		habitats.add("agua");
		habitats.add("terra");
	}

	private void execute() {
		this.init();

		// cria o frame onde os JOptionPane's serão associados
		JFrame frame = new JFrame("Jogo dos Animais");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// continuar com o jogo
		boolean continuar = true;

		while (continuar) {
			JOptionPane.showMessageDialog(frame, "Pense em um animal.");

			// pega um habitat aleatório
			String habitat = this.habitats.stream().findAny().get();

			int resposta = JOptionPane.showConfirmDialog(frame, "Este animal vive na " + habitat + "?", null,
					JOptionPane.YES_NO_OPTION);

			Animal animal;

			// caso Sim
			if (resposta == 0) {
				// pega um animal do mesmo habitat
				animal = this.animais.stream().filter(a -> a.getHabitat().equals(habitat)).findAny().get();
				int resposta2 = adivinharAnimal(frame, animal);

				if (resposta2 == 0) {
					JOptionPane.showMessageDialog(frame, animal.toString());
				} else {
					String novoHabitat = habitat;
					this.novoAnimal(frame, animal, novoHabitat);
				}
			}
			
			// caso Não
			else {
				// pega um animal de habitat diferente
				animal = this.animais.stream().filter(a -> !a.getHabitat().equals(habitat)).findAny().get();
				int resposta2 = adivinharAnimal(frame, animal);

				if (resposta2 == 0) {
					JOptionPane.showMessageDialog(frame, animal.toString());
				} else {
					String novoHabitat = this.habitats.stream().filter(s -> !s.equals(habitat)).findFirst().get();
					this.novoAnimal(frame, animal, novoHabitat);
				}
			}

			int cont = JOptionPane.showConfirmDialog(frame, "Continuar?", null, JOptionPane.YES_NO_OPTION);

			if (cont == 1) {
				continuar = false;
			}
		}

		if (!continuar) {
			System.exit(0);
		}
	}

	// tenta adivinhar qual animal foi pensado
	private int adivinharAnimal(JFrame frame, Animal animal) {
		return JOptionPane.showConfirmDialog(frame, "Você pensou em " + animal.getEspecie() + "?",
				null, JOptionPane.YES_NO_OPTION);
	}

	// adiciona um novo animal na lista para adivinhar
	private void novoAnimal(JFrame frame, Animal animal, String novoHabitat) {
		String novaEspecie = JOptionPane.showInputDialog(frame, "Qual animal você pensou?");
		
		String novaAcao = JOptionPane.showInputDialog(frame,
				animal.toString() + ". " + novaEspecie + " _______.", "Complete a frase:",
				JOptionPane.DEFAULT_OPTION);

		Animal novoAnimal = new Animal(novaEspecie, novoHabitat, novaAcao);
		this.animais.add(novoAnimal);
	}

	public static void main(String[] args) {
		Servico servico = new Servico();
		servico.execute();
	}
}
