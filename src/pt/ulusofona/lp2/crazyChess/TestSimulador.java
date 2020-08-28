package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSimulador {

    @Test
    public void Teste01processaJogada() {
        ArrayList<CrazyPiece> lista = new ArrayList<>();
        Simulador simulador = new Simulador();
        simulador.setDimensaoTabuleiro(4);
        CrazyPiece peca = new CrazyPiece(1, 0, 0, "teste01", 1 , 2);
        lista.add(peca);
        simulador.setLista(lista);
        simulador.setQtdInicialPretas(1);
        boolean resultadoObtido = simulador.processaJogada(peca.getX(), peca.getY(), 1,3);
        boolean resultadoEsperado = true;
        assertEquals(resultadoEsperado, resultadoObtido);
    }
    @Test
    public void Teste02processaJogada() {
        ArrayList<CrazyPiece> lista = new ArrayList<>();
        Simulador simulador = new Simulador();
        simulador.setDimensaoTabuleiro(4);
        CrazyPiece peca = new CrazyPiece(1, 0, 0, "teste01", 1, 2);
        CrazyPiece peca2 = new CrazyPiece(2, 0, 0, "teste02", 1, 3);
        CrazyPiece peca3 = new CrazyPiece(3, 0, 1, "teste03",2, 3);
        lista.add(peca);
        lista.add(peca2);
        lista.add(peca3);
        simulador.setLista(lista);
        simulador.setQtdInicialPretas(2);
        simulador.setQtdInicialBrancas(1);
        boolean resultadoObtido = simulador.processaJogada(peca.getX(), peca.getY(), peca2.getX(), peca2.getY());
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado, resultadoObtido);
    }
}
