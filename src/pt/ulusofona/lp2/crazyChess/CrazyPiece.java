package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class CrazyPiece
{
    int id;                 //personagem no simulador
    int idPeca;             //tipo de peca
    int idEquipa;           // Brancas ou Pretas
    String alcunha;
    int x;
    int y;
    boolean capturada;

    public CrazyPiece(int id, int idPeca, int idEquipa, String alcunha)
    {
        this.id = id;
        this.idPeca = idPeca;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.capturada = false;
    }
    public CrazyPiece(int id, int idPeca, int idEquipa, String alcunha, int x, int y)
    {
        this.id = id;
        this.idPeca = idPeca;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
        this.capturada = false;
        this.x = x;
        this.y = y;
    }
    public CrazyPiece()
    {}
    public int getId() {
        return id;
    }

    public int getIdEquipa() {
        return idEquipa;
    }
    public  int getIdPeca()
    {
        return getIdPeca();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CrazyPiece getPieceById(List<CrazyPiece> lista, int id)
    {
        CrazyPiece peca = new CrazyPiece();
        for(int i = 0; i<lista.size();i++)
        {
            peca = lista.get(i);
            if(peca.id == id)
            {
                break;
            }
        }
        return peca;
    }
    public String getImagePNG()
    {
        if(idEquipa == 0)
        {
            return "crazy_emoji_black.png";
        }
        else
        {
            return "crazy_emoji_white.png";
        }
    }
    public String toString()
    {
        if(capturada == true)
        {
            return this.id+" | "+this.idPeca+" | "+this.idEquipa+" | "+this.alcunha+" @ (n/a)";
        }
        return this.id+" | "+this.idPeca+" | "+this.idEquipa+" | "+this.alcunha+" @ ("+this.x+", "+this.y+")";
    }
    public int[][] jogadasPossiveis(int x , int y,int idPecaEquipa,int dimensaoTabuleiro, List<CrazyPiece> Pecas)
    {
        int [][] jogadas = new int [dimensaoTabuleiro][dimensaoTabuleiro];
        if(x == dimensaoTabuleiro-1 && y == dimensaoTabuleiro-1)
        {
            jogadas[x][y-1] = 1;
            jogadas[x-1][y-1] = 1;
            jogadas[x-1][y] = 1;
        }
        else if(x == 0 && y == 0)
        {
            jogadas[x+1][y] = 1;
            jogadas[x+1][y+1] = 1;
            jogadas[x][y+1] = 1;
        }
        else if(x == dimensaoTabuleiro-1 && y == 0)
        {
            jogadas[x-1][y] = 1;
            jogadas[x][y+1] = 1;
            jogadas[x-1][y+1] = 1;
        }
        else if(x == 0 && y == dimensaoTabuleiro-1)
        {
            jogadas[x][y-1] = 1;
            jogadas[x+1][y-1] = 1;
            jogadas[x+1][y] = 1;
        }
        else if(x == 0)
        {
            jogadas[x][y-1] = 1;
            jogadas[x][y+1] = 1;
            jogadas[x+1][y+1] = 1;
            jogadas[x+1][y] = 1;
            jogadas[x+1][y-1] = 1;
        }
        else if(y == 0)
        {
            jogadas[x+1][y] = 1;
            jogadas[x-1][y] = 1;
            jogadas[x+1][y+1] = 1;
            jogadas[x][y+1] = 1;
            jogadas[x-1][y+1] = 1;
        }
        else if(y == dimensaoTabuleiro-1)
        {
            jogadas[x-1][y] = 1;
            jogadas[x+1][y] = 1;
            jogadas[x][y-1] = 1;
            jogadas[x+1][y-1] = 1;
            jogadas[x-1][y-1] = 1;
        }else if(x == dimensaoTabuleiro-1)
        {
            jogadas[x][y+1] = 1;
            jogadas[x][y-1] = 1;
            jogadas[x-1][y] = 1;
            jogadas[x-1][y+1] = 1;
            jogadas[x-1][y-1] = 1;
        }
        else
        {
            jogadas[x][y-1]=1;
            jogadas[x+1][y-1]=1;
            jogadas[x-1][y-1]=1;
            jogadas[x-1][y]=1;
            jogadas[x-1][y+1]=1;
            jogadas[x][y+1]=1;
            jogadas[x+1][y+1]=1;
            jogadas[x+1][y]=1;
        }
        for (int i = 0; i < Pecas.size(); i++)
        {
            CrazyPiece peca = Pecas.get(i);
            if(peca.capturada == true){

            }
            else
            {
                if(peca.idEquipa != idPecaEquipa)
                {
                    jogadas[peca.x][peca.y] = -2;
                }
                else {
                    jogadas[peca.x][peca.y] = -1;
                }
            }
        }
        return jogadas;
    }
    public void mover(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}