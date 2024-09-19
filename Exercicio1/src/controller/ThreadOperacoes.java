package controller;

import java.util.concurrent.Semaphore;

public class ThreadOperacoes extends Thread {

    private Semaphore controleAcesso;
    private int idThread;

    public ThreadOperacoes(Semaphore controleAcesso, int idThread) {
        this.controleAcesso = controleAcesso;
        this.idThread = idThread;
    }

    @Override
    public void run() {
        if (idThread % 3 == 1) {
            realizarCalculo(800, 300, idThread);
            realizarTransacao(800, idThread);
            realizarCalculo(800, 300, idThread);
            realizarTransacao(800, idThread);            
        } else if (idThread % 3 == 2) {
            realizarCalculo(1200, 400, idThread);
            realizarTransacao(1200, idThread);
            realizarCalculo(1200, 400, idThread);
            realizarTransacao(1200, idThread);
        } else {
            realizarCalculo(1600, 600, idThread);
            realizarTransacao(1600, idThread);
            realizarCalculo(1600, 600, idThread);
            realizarTransacao(1600, idThread);
        }
    }

    private void realizarTransacao(int duracaoTransacao, int idAtual) {
        try {
            controleAcesso.acquire();
            Thread.sleep(duracaoTransacao);
            System.out.println("Thread " + idAtual + " está executando transações por " + duracaoTransacao + "ms.");
        } catch (InterruptedException e) {
            System.err.println("Erro na thread: " + e.getMessage());
        } finally {
            controleAcesso.release();
        }
    }

    private void realizarCalculo(int tempoMax, int tempoMin, int idAtual) {
        int duracaoCalculo = (int) (Math.random() * (tempoMax - tempoMin)) + tempoMin;
        System.out.println("Thread " + idAtual + " está realizando cálculos por " + duracaoCalculo + "ms.");
        try {
            Thread.sleep(duracaoCalculo);
        } catch (InterruptedException e) {
            System.err.println("Erro na thread: " + e.getMessage());
        }
    }
}