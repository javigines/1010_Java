package juego;

import java.awt.Color;

public class Tablero {

	private boolean[][] estadoTablero;
	private Color[][]	coloresTablero;
	
	public Tablero(int canRect){
		estadoTablero= new boolean [canRect][canRect];
		coloresTablero= new Color [canRect][canRect];
		for(int p=0; p<canRect; p++){
			for(int k=0; k<canRect;k++){
				estadoTablero[k][p]=false;
			}
		}		
	}
	
	public boolean getEstadoTablero(int n, int m){
		return estadoTablero[n][m];
	}
	public void setEstadoTablero(int n, int m, boolean estado, Color color){
		estadoTablero[n][m]=estado;
		coloresTablero[n][m]=color;
	}
	public Color getColoresTablero(int n, int m){
		return coloresTablero[n][m];
	}
	public int checkEstado(){
		int p, k, filas=0;

		for(p=0; p<estadoTablero.length; p++){
			for(k=0; k<estadoTablero[p].length; k++)
				if(estadoTablero[k][p]==false)
					break;
	
			if(k==estadoTablero[p].length){
				filas++;
				for(k=0; k<estadoTablero[p].length; k++)
					estadoTablero[k][p]=false;
			}
		}
		for(p=0; p<estadoTablero.length; p++){
			for(k=0; k<estadoTablero[p].length; k++)
				if(estadoTablero[p][k]==false)
					break;
	
			if(k==estadoTablero[p].length){
				filas++;
				for(k=0; k<estadoTablero[p].length; k++)
					estadoTablero[p][k]=false;
			}
		}
		return filas*20;
	}
}
