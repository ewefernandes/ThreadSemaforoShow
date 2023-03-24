package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsConcerto;

public class Principal {

	public static void main(String[] args) {

		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		int numIng = 0;
		
		
		for (int thread = 1; thread < 30; thread++) {
			ThreadsConcerto show = new ThreadsConcerto(thread, numIng, semaforo);
			show.start();
		}

	}

}
