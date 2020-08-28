package pt.ulusofona.lp2.crazyChess;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


public class Simulador
{
    List<CrazyPiece> pecas;
    int dimensaoTabuleiro;
    int idEquipaAJogar;
    int qtdPecasInicialBrancas;
    int qtdPecasInicialPretas;
    int qtdCapturasBrancas;
    int qtdCapturasPretas;
    int qtdTentativasInvalidasBrancas;
    int qtdTentativasInvalidasPretas;
    int qtdJogadasBrancas;
    int qtdJogadasPretas;
    int qtdJogadasSemCaptura;
    String resultado;
    File file;

    public boolean iniciaJogo(File ficheiro)
    {
        pecas = new ArrayList<>();
        idEquipaAJogar = 0;
        qtdCapturasBrancas = 0;
        qtdCapturasPretas = 0;
        qtdTentativasInvalidasBrancas = 0;
        qtdTentativasInvalidasPretas = 0;
        qtdJogadasBrancas = 0;
        qtdJogadasPretas = 0;
        qtdJogadasSemCaptura=0;
        qtdPecasInicialPretas=0;
        qtdPecasInicialBrancas=0;
        try
        {
            FileReader ler = new FileReader(ficheiro.getName());
            BufferedReader lerArq = new BufferedReader(ler);

            dimensaoTabuleiro = Integer.parseInt(lerArq.readLine());
            int qtdPecasInicial = Integer.parseInt(lerArq.readLine());
            for(int i = 0; i < qtdPecasInicial;i++) {
                String linha = lerArq.readLine();
                String[] spliter = (linha.split(":"));
                CrazyPiece peca = new CrazyPiece(Integer.parseInt(spliter[0]), Integer.parseInt(spliter[1]), Integer.parseInt(spliter[2]), spliter[3]);
                pecas.add(peca);
            }
            for(int i = 0; i < dimensaoTabuleiro;i++)
            {
                String linha = lerArq.readLine();
                String [] spliter = (linha.split(":"));
                for(int j = 0; j < dimensaoTabuleiro;j++)
                {
                    if(Integer.parseInt(spliter[j]) != 0)
                    {
                        CrazyPiece peca = new CrazyPiece();
                        peca = peca.getPieceById(pecas,Integer.parseInt(spliter[j]));
                        peca.setX(j);
                        peca.setY(i);
                        if (peca.getIdEquipa() == 0)
                        {
                            qtdPecasInicialPretas++;
                        }
                        else
                        {
                            qtdPecasInicialBrancas++;
                        }
                    }
                }
            }
            return true;
        }
        catch (IOException e)
        {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return false;
        }
    }
    public int getTamanhoTabuleiro()
    {
        return dimensaoTabuleiro;
    }
    public List<CrazyPiece> getPecasMalucas()
    {
        return pecas;
    }
    public int getIDPeca(int x,int y)
    {
        for(int i = 0;i<pecas.size();i++)
        {
            CrazyPiece peca = pecas.get(i);
            if(peca.getX() == x && peca.getY() == y && peca.capturada ==false)
            {
                return peca.getId();
            }
        }
        return 0;
    }
    public int getIDEquipaAJogar()
    {
        if(jogoTerminado() != true)
        {
            return idEquipaAJogar;
        }
        else
        {
            getResultados();
            return 0;
        }
    }
    public List<String> getAutores()
    {
        List<String> autores = new ArrayList<>();
        autores.add("Luís Gonçalves 21702482");
        autores.add("Tiago Pinto 21703819");
        return autores;
    }
    public boolean vesDeJogar(int idPeca)
    {
        for(int i = 0;i<pecas.size();i++)
        {
            CrazyPiece peca = pecas.get(i);
            if(peca.id == idPeca)
            {
                if(peca.idEquipa == idEquipaAJogar)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean processaJogada(int deX, int deY, int paraX, int paraY)
    {
        if(jogoTerminado() != true)
        {
            int idPeca = getIDPeca(deX, deY);
            if (vesDeJogar(idPeca))
            {
                CrazyPiece peca = new CrazyPiece();
                peca = peca.getPieceById(pecas, idPeca);
                int[][] ajuda = peca.jogadasPossiveis(deX, deY, peca.idEquipa,dimensaoTabuleiro,pecas);
                if (ajuda[paraX][paraY] == 1)
                {
                    peca.mover(paraX,paraY);
                    trocaIdEquipaJogar();
                    qtdJogadasSemCaptura++;
                    if (peca.idEquipa == 0)
                    {
                        qtdJogadasPretas++;
                    } else
                    {
                        qtdJogadasBrancas++;
                    }
                    return true;
                }
                else if (ajuda[paraX][paraY] == -2)
                {
                    int idRemover = getIDPeca(paraX, paraY);
                    CrazyPiece pecaRemover = new CrazyPiece();
                    pecaRemover = pecaRemover.getPieceById(pecas, idRemover);
                    pecaRemover.capturada = true;
                    qtdJogadasSemCaptura = 0;
                    if (peca.getIdEquipa() == 0)
                    {
                        qtdCapturasPretas++;
                        qtdPecasInicialBrancas--;
                        qtdJogadasPretas++;
                    }
                    if (peca.getIdEquipa() == 1)
                    {
                        qtdCapturasBrancas++;
                        qtdPecasInicialPretas--;
                        qtdJogadasBrancas++;
                    }
                    peca.mover(paraX,paraY);
                    trocaIdEquipaJogar();
                    return true;
                }
                else
                {
                    if (peca.getIdEquipa() == 0)
                    {
                        qtdTentativasInvalidasBrancas++;
                    }
                    if (peca.getIdEquipa() == 1)
                    {
                        qtdTentativasInvalidasPretas++;
                    }
                    return false;
                }
            }
            if (idEquipaAJogar == 0)
            {
                qtdTentativasInvalidasPretas++;
            } else
            {
                qtdTentativasInvalidasBrancas++;
            }
            return false;
        }
        else
        {
            getResultados();
            return true;
        }
    }
    public int trocaIdEquipaJogar()
    {
        if(idEquipaAJogar == 0)
        {
            return idEquipaAJogar = 1;
        }
        else
        {
            return idEquipaAJogar = 0;
        }
    }
    public boolean jogoTerminado()
    {
        if(qtdPecasInicialBrancas > 0 && qtdPecasInicialPretas==0)
        {
            resultado = "VENCERAM AS BRANCAS";
            return true;
        }
        if(qtdPecasInicialPretas >0 && qtdPecasInicialBrancas==0)
        {
            resultado = "VENCERAM AS PRETAS";
            return true;
        }
        else if(qtdPecasInicialPretas == 1 && qtdPecasInicialBrancas == 1)
        {
            resultado = "EMPATE";
            return true;
        }
        else if(qtdJogadasSemCaptura==10 && qtdCapturasPretas == 1 && qtdCapturasBrancas == 0
                || qtdJogadasSemCaptura==10 && qtdCapturasBrancas == 1 && qtdCapturasPretas == 0)
        {
            resultado = "EMPATE";
            return true;
        }
        return false;
    }
    public List<String> getResultados()
    {
        List<String> resultados = new ArrayList<>();
        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: "+resultado);
        resultados.add("---");
        resultados.add("Equipa das Pretas");
        resultados.add(""+qtdCapturasPretas);
        resultados.add(""+qtdJogadasPretas);
        resultados.add(""+qtdTentativasInvalidasPretas);
        resultados.add("Equipa das Brancas");
        resultados.add(""+qtdCapturasBrancas);
        resultados.add(""+qtdJogadasBrancas);
        resultados.add(""+qtdTentativasInvalidasBrancas);
        return resultados;
    }
    public void setDimensaoTabuleiro (int valor) {
        this.dimensaoTabuleiro = valor;
    }
    public void setLista(ArrayList<CrazyPiece> lista) {
        this.pecas = lista;
    }
    public void setQtdInicialPretas (int valor) {
        this.qtdPecasInicialPretas = valor;
    }
    public void setQtdInicialBrancas (int valor) {
        this.qtdPecasInicialBrancas = valor;
    }

}