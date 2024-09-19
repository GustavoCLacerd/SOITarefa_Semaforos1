package view;

import java.util.concurrent.Semaphore;
import controller.ThreadOperacoes;

public class Main {
    public static void main(String[] args) {
        int espaco = 1;
        Semaphore semaforo = new Semaphore(espaco);

        for (int i = 0; i < 22; i++) {
            ThreadOperacoes t = new ThreadOperacoes(semaforo, i);
            t.start();
        }
    }
}