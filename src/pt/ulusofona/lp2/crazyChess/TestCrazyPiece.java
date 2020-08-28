package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestCrazyPiece {
    @Test
    public void Teste01getPieceById () {
        List<CrazyPiece> lista = new ArrayList<>();
        CrazyPiece peca = new CrazyPiece(0,0,1,"teste01");
        CrazyPiece peca1 = new CrazyPiece(1,0,0,"teste02");
        CrazyPiece peca2 = new CrazyPiece(2,0,1,"teste03");
        lista.add(peca);
        lista.add(peca1);
        lista.add(peca2);
        String resultadoEsperado = peca1.toString();
        CrazyPiece pecaObtida = new CrazyPiece();
        pecaObtida = pecaObtida.getPieceById(lista,1);
        String resultadoObtido = pecaObtida.toString();
        assertEquals(resultadoEsperado, resultadoObtido);

    }
    @Test
    public void Teste02getPieceById() {
        List<CrazyPiece> lista = new ArrayList<>();
        CrazyPiece peca = new CrazyPiece(0,0,1,"teste01");
        CrazyPiece peca1 = new CrazyPiece(1,0,0,"teste02");
        CrazyPiece peca2 = new CrazyPiece(2,0,1,"teste03");
        lista.add(peca);
        lista.add(peca1);
        lista.add(peca2);
        String resultadoEsperado = peca2.toString();// new CrazyPiece().toString();
        CrazyPiece pecaObtida = new CrazyPiece();
        pecaObtida = pecaObtida.getPieceById(lista, 3);
        String resultadoObtido = pecaObtida.toString();
        assertEquals(resultadoEsperado, resultadoObtido);

    }

}
