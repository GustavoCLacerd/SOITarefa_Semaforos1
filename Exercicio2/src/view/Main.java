package view;

import java.util.concurrent.Semaphore;
import controller.PreparoCulinario;

public class Main {
    public static void main(String[] args) {
        Semaphore espacos = new Semaphore(1);
        for (int contador = 0; contador < 5; contador++) {
            PreparoCulinario t = new PreparoCulinario(espacos, contador);
            t.start();
        }
    }
}