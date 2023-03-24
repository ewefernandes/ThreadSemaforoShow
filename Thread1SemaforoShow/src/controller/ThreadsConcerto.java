package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadsConcerto extends Thread {

	private int threadId;
	private int numIng;
	private static int ingressos = 10;
	private Semaphore semaforo;
	
	public ThreadsConcerto(int threadId, int numIng, Semaphore semaforo) {
		this.threadId = threadId;
		this.numIng = numIng;
		this.semaforo = semaforo;
	}

	public void run() {
		Logar();
	}
	
	public void Logar() {
		double tempoTotal = ((Math.random()*1969.7) + 50);
		try {
			sleep(tempoTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tempoTotal > 1000) {
			System.out.println("Nao foi possivel logar. Time out. Seu ID: " +threadId);
		} else {
			System.out.println("Voce logou! Guarde seu ID >> " +threadId);
			ProcessoCompra();
		}
	}
	

	public void ProcessoCompra() {
		double tempoTotal = ((Math.random()*2020) + 1000);
		try {
			sleep(tempoTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Random aleatorio = new Random();
		int numIng = aleatorio.nextInt(1,5);
		if (tempoTotal > 2000) {
			System.out.println("Nao foi possivel solicitar seu ingresso. Seu ID > " +threadId);
		} else {
			System.out.println("Recebemos o pedido do cliente #" +threadId+ " - " +numIng+ " ingressos.");
			
			try {
				semaforo.acquire();
				Validar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			semaforo.release();
		}
	}
	
	private void sleep(double tempoTotal) {
		// TODO Auto-generated method stub
		
	}

	public void Validar() {
		
		ingressos = ingressos - numIng;
		if (ingressos < 0) {
			System.out.println("Nao ha ingressos disponiveis - Cliente #" +threadId);
		} else {
			System.out.println("Compra feita! - Cliente #" +threadId+ " - Ingressos ainda disponiveis >> " +ingressos);
		}
		System.out.println("Fim do processo - Cliente #" +threadId);
	}
	
}
