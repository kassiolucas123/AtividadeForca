import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class ForcaEstruturada {
	static int pontuacaoTotal = 0;
	static String letrasEncontradas = "";
	static String letrasChutadas = "";
	static String chute = null;
	static String palavraSecreta = null;
	static String[] tracos = null;
	static String[] letras = null;
	static int erros = 6;
	static int palavrasAchadasContagem = 0;
	static String nomeJogador = null;
	static String temaEscolhido = null;

	static List<String> filmes = new ArrayList<>();
	static List<String> musica = new ArrayList<>();

	static void arriscarLetra() {

		try {
			chute = JOptionPane.showInputDialog("QUAL O SEU CHUTE?");
			boolean encontrou = false;

			for (int i = 0; i < letras.length; i++) {
				if (letras[i].equals(chute.toUpperCase())) {
					encontrou = true;
					palavrasAchadasContagem += 1;
					tracos[i] = letras[i];
				}
			}

			if (!encontrou) {
				erros--;
				letrasChutadas += chute + " ";
				JOptionPane.showMessageDialog(null, "VOCE ERROU\nVOCE AINDA TEM " + erros
						+ " CHANCES\n\nLETRAS CHUTADAS:  " + letrasChutadas.toUpperCase() + "\n\n");

				if (erros < 0) {

					String chutePalaraSecreta = JOptionPane
							.showInputDialog("CHANCES ESGOTADAS\nQUAL A PALAVRA SECRETA:n");
					if (palavraSecreta.equals(chutePalaraSecreta.toUpperCase())) {
						JOptionPane.showMessageDialog(null, tracos.length);
						JOptionPane.showMessageDialog(null,
								"VOCE ACERTOU A PALAVRA SECRETA, SUA PONTUACAO FOI DE: " + pontuacaoTotal);
					} else {
						JOptionPane.showMessageDialog(null, "VOCE NAO ACERTOU A PALAVRA SECRETA...");
					}
					System.exit(0);
				}
			}

			if (encontrou) {
				pontuacaoTotal += 5;

				JOptionPane.showMessageDialog(null, "VOCE ACERTOU");
			}

			letrasEncontradas = "";

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "CARACTERE INVALIDO");
		}
	}

	// ARRISCAR PALAVRA SECRETA
	static void arriscarPalavraSecreta() {
		String palavra = JOptionPane.showInputDialog("QUAL O NOME DA PALAVRA SECRETA: ");
		if (palavraSecreta.equals(palavra.toUpperCase())) {
			pontuacaoTotal += ((letras.length - palavrasAchadasContagem) * 15) * 2 + 1000;
			JOptionPane.showMessageDialog(null, "VOCE ACERTOU A PALAVRA SECRETA\nA PALAVRA É " + palavraSecreta + "\n"
					+ nomeJogador.toUpperCase() + " , SUA PONTUACAO FOI DE " + pontuacaoTotal);
			System.exit(0);
		} else {
			JOptionPane.showMessageDialog(null, "VOCE NAO ACERTOU A PALAVRA SECRETA\nA PALAVRA SECRETA É "
					+ palavraSecreta + "SUA PONTUAÇÃO É DE " + palavrasAchadasContagem * 15);
			System.exit(0);
		}
	}

	// ESCOLHER OPÇOES PARA TEMA
	static String escolherTema() {
		String palavraSecreta = null;
		try {
			int op = Integer.valueOf(JOptionPane.showInputDialog(
					"PRIMEIRO TEMOS QUE ESCOLHER O TEMA DO NOSSO JOGO\nQUAL TEMA VOCE ESCOLHE PARA O JOGO?\n1 - GENEROS DE FIMES\n2 - GENEROS MUSICAIS\n3 - DESISTIR"));
			if (op == 1) {
				JOptionPane.showMessageDialog(null, "BOA ESCOLHA");
				Random gerador = new Random();
				int numero = gerador.nextInt(filmes.size());
				palavraSecreta = filmes.get(numero);
				temaEscolhido = "FILMES";
				// return palavraSecreta;

			} else if (op == 2) {
				JOptionPane.showMessageDialog(null, "BOA ESCOLHA");
				Random gerador = new Random();
				int numero = gerador.nextInt(musica.size());
				palavraSecreta = musica.get(numero);
				temaEscolhido = "MUSICA";
				// return palavraSecreta;
			} else if (op == 3) {
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(null, "DIGITE APENAS UMA OPCAO DO MENU");
				escolherTema();
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "DIGITE APENAS OPCOES DO MENU");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "DIGITE CARACTERES DO MENU");
		}
		return palavraSecreta;
	}

	static String desenharBoneco() {
		if (erros == 5) {
			return "0";
		} else if (erros == 4) {
			return "0\n |";
		} else if (erros == 3) {
			return "  0\n  |\\";
		} else if (erros == 2) {
			return "  0 \n /|\\ ";
		} else if (erros == 1) {
			return "  0 \n/|\\\n/";
		} else if(erros == 0){
			return "  0 \n /|\\ \n /\\";
		}
		return "";
		

	}

	static void adicinarPalavra() {
		while (true) {
			try {
				int op = 999;
				op = Integer.valueOf(JOptionPane
						.showInputDialog("ESCOLHA O TEMA\n1 - GENEROS MUSICAIS\n2 - GENEROS DE FILMES\n3 - DESISTIR"));
				String palavra = JOptionPane.showInputDialog("QUAL A NOVA PALAVRA SECRETA");
				if (op == 1) {
					filmes.add(palavra.toUpperCase());
				}
				if (op == 2) {
					musica.add(palavra.toUpperCase());

				} else if (op == 3) {
					System.exit(0);
				} else if (op > 3) {
					JOptionPane.showMessageDialog(null, "DIGITE OPCOES DO MENU");
					adicinarPalavra();
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "DIGITE CARACTERES DO MENU");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "DIGITE CARACTERES DO MENU");
			}
			jogar();
		}
	}

	public static void jogador() {

		nomeJogador = JOptionPane.showInputDialog("PRIMEIRO DIGITE SEU NOME: ");

	}

	static String mostrarTracos() {
		for (int i = 0; i < tracos.length; i++) {
			letrasEncontradas += tracos[i] + " ";
		}
		return letrasEncontradas;
	}

	public static void jogar() {
		palavraSecreta = escolherTema();
		letras = palavraSecreta.split("");
		tracos = new String[letras.length];
		Arrays.fill(tracos, "__ ");
	}

	public static void menuPalavra() {
		int op = 999;
		while (erros >= 0) {
			while (op != 3) {
				try {
					op = Integer.valueOf(JOptionPane.showInputDialog("TEMA: " + temaEscolhido
							+ "\n\nDICA: A PALAVRA TEM " + letras.length + " LETRAS\n" + mostrarTracos()
							+ " \n\n LETRAS CHUTADAS:  " + letrasChutadas.toUpperCase()
							+ "\n\n" + desenharBoneco() + "\n\nACERTE A PALAVRA SECRETA\n\n1 - ARRISCAR UMA LETRA\n2 - ARRISCAR PALAVRA SECRETA\n3 - DESISTIR"));

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "DIGITE CARACTERES DO MENU");

				}
				if (op == 1) {
					arriscarLetra();

				} else if (op == 2) {
					arriscarPalavraSecreta();

				} else if (op == 3) {
					System.exit(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		filmes.add("TERROR");
		filmes.add("ACAO");
		filmes.add("SUSPENSE");
		filmes.add("COMEDIA");
		filmes.add("AVENTURA");
		filmes.add("FICCAO");

		musica.add("ROCK");
		musica.add("FORRO");
		musica.add("FUNK");
		musica.add("DANCE");
		musica.add("METAL");
		musica.add("BREGA");
		musica.add("POP");
		musica.add("ELETRONICA");
		musica.add("FOLK");

		// MENU
		jogador();
		while (true) {
			try {
				JOptionPane.showMessageDialog(null,
						"***********           JOGO DE FORCA           *********** \n\nBEM VINDO AO NOSSO JOGO DE FORCA "
								+ nomeJogador.toUpperCase() + "\nSUA PONTUACAO SERA EXIBIDA NO FINAL \n\nBOA SORTE...");
				int opMenu = Integer.valueOf(
						JOptionPane.showInputDialog("1 - JOGAR\n2 - ADICIONAR PALAVRAS SECRETAS\n3 - DESISTIR"));
				if (opMenu == 1) {
					jogar();

				} else if (opMenu == 2) {
					adicinarPalavra();

				} else if (opMenu == 3) {
					System.exit(0);

				} else {
					JOptionPane.showMessageDialog(null, "DIGITE APENAS OPCOES DISPONIVEIS NO MENU");
					main(args);

				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "DIGITE APENAS OPCOES DISPONIVEIS NO MENU");

			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "DIGITE APENAS OPCOES DISPONIVEIS NO MENU");

			}
			menuPalavra();

		}
	}
}
