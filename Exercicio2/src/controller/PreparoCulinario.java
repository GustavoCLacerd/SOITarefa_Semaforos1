package controller;

import java.util.concurrent.Semaphore;

public class PreparoCulinario extends Thread {
    private int idThread;
    private Semaphore controleEntrega;

    public PreparoCulinario(Semaphore controleEntrega, int idThread) {
        this.controleEntrega = controleEntrega;
        this.idThread = idThread;
    }

    @Override
    public void run() {
        String prato;
        if (idThread % 2 == 0) {
            prato = "Ravioli";
            prepararPrato(prato, 1200, 600, idThread);
            realizarEntrega(controleEntrega, idThread, prato);
        } else {
            prato = "Caldo de Legumes";
            prepararPrato(prato, 800, 500, idThread);
            realizarEntrega(controleEntrega, idThread, prato);
        }
    }

    private void realizarEntrega(Semaphore semaforo, int id, String nomePrato) {
        try {
            System.out.println("Thread " + id + " está preparando a entrega do prato.");
            semaforo.acquire();
            Thread.sleep(5000);
            System.out.println("Prato " + nomePrato + " foi entregue pela Thread " + id + ".");
        } catch (InterruptedException e) {
            System.err.println("Erro na entrega: " + e.getMessage());
        } finally {
            semaforo.release();
        }
    }

    private void prepararPrato(String nomePrato, int tempoMax, int tempoMin, int id) {
        int tempoTotal = (int) ((Math.random() * (tempoMax - tempoMin)) + tempoMin);
        int tempoDecorrido = 0;
        while (tempoDecorrido < tempoTotal) {
            try {
                Thread.sleep(100);
                tempoDecorrido += 100;
                double progresso = (tempoDecorrido / (double) tempoTotal) * 100;
                String progressoFormatado = String.format("%.1f", progresso);
                System.out.println("Thread " + id + " preparando " + nomePrato + ": " + progressoFormatado + "% concluído.");
            } catch (InterruptedException e) {
                System.err.println("Erro no preparo: " + e.getMessage());
            }
        }
    }
}