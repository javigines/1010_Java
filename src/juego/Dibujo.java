package juego;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


@SuppressWarnings("serial")
public class Dibujo extends Canvas{
	
	private Graphics2D g2d;
	private Frame frame;

	private int x,y;
	
	private int canRect=10;
	private int []tamRect={35,35};
	private int []posRect={80,80};
	private ColoresJuego colores=new ColoresJuego();
	private Tablero tablero=new Tablero(canRect);
	
	
	private Piezas[] piezaConjunto= new Piezas [5];
	private boolean[] piezasZonaPiezas={false,true,false,true,false,true,false,true};
	private int[] coordsSelectPieza= new int [3];
	private int[] coordsSelectTablero= new int [2];
	private boolean selected=false;
	
	private int puntuacion=0;
	private int bestScore=0;
	
	
	
	public Dibujo(){
		
		frame=new Frame();
		frame.add(this);
		frame.setTitle("1010 - Creado por Javi");
		frame.setSize(900,550);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		addMouseListener(new ControladorMouse());
		frame.addWindowListener(new ControladorVentana());

		piezaConjunto[piezaConjunto.length-1]=new Piezas (colores.getColorPiezasRestantes());
	}
	
	public void paint(Graphics g){
		
		g2d = (Graphics2D) g;
		
		g2d.setFont(new Font("Arial", Font.BOLD,13));
		puntuacion+=tablero.checkEstado();
		
		
		tablero();
		resetButton();
		bestScore();
		marcador();
		zonaPiezas();
		setPiezas();
		
	}
	
	public void tablero(){
		
		
		g2d.draw3DRect(posRect[0]-2,posRect[1]-2,tamRect[0]*canRect+canRect+2,tamRect[1]*canRect+canRect+2,true);
		
		g2d.setColor(colores.getColorRect());
		
		for(int posx=posRect[0], canx=0; canx<canRect; posx=posx+tamRect[0]+1, canx++){
			for(int posy=posRect[1], cany=0; cany<canRect; posy=posy+tamRect[1]+1, cany++){
				if(!tablero.getEstadoTablero(cany, canx))
					g2d.fill3DRect(posx,posy,tamRect[0],tamRect[1],true);
				else{
					g2d.setColor(tablero.getColoresTablero(cany, canx));
					g2d.fill3DRect(posx,posy,tamRect[0],tamRect[1],true);
					g2d.setColor(colores.getColorRect());
				}
			}		
		}
	}
	public void marcador(){
		
		g2d.setColor(colores.getColorDefault());
		g2d.drawString("Puntuación:", posRect[0]+tamRect[0]*canRect+canRect-100, posRect[1]-55);
		g2d.setColor(colores.getColorMarcador());
		g2d.fill3DRect(posRect[0]+tamRect[0]*canRect+canRect-100,posRect[1]-50,100,35,true);
		g2d.setFont(new Font("Arial", Font.BOLD,20));
		g2d.setColor(colores.getColorDefault());
		g2d.drawString(String.valueOf(puntuacion), posRect[0]+tamRect[0]*canRect+canRect-65, posRect[1]-25);
		g2d.setFont(new Font("Arial", Font.BOLD,13));
		
	}
	public void bestScore(){

		if(puntuacion>bestScore)
			bestScore=puntuacion;
		
		g2d.setColor(colores.getColorDefault());
		g2d.drawString("Mejor Puntuación:", posRect[0]+tamRect[0]*canRect+canRect-225, posRect[1]-55);
		g2d.setColor(colores.getColorMarcador());
		g2d.fill3DRect(posRect[0]+tamRect[0]*canRect+canRect-225,posRect[1]-50,115,35,true);
		g2d.setFont(new Font("Arial", Font.BOLD,20));
		g2d.setColor(colores.getColorBestScore());
		g2d.drawString(String.valueOf(bestScore), posRect[0]+tamRect[0]*canRect+canRect-185, posRect[1]-25);
		g2d.setColor(colores.getColorDefault());
		g2d.setFont(new Font("Arial", Font.BOLD,13));
		
	}
	public void resetButton() {

		g2d.setColor(colores.getColorBotonBordes());
		g2d.fill3DRect(posRect[0]-2, posRect[1]-52, 84, 34, true);
		g2d.setColor(colores.getColorBoton());
		g2d.fill3DRect(posRect[0], posRect[1]-50, 80, 30, true);
		g2d.setColor(colores.getColorDefault());
		g2d.drawString("Reiniciar", posRect[0]+10, posRect[1]-30);
		
	}

	public void zonaPiezas(){
		
		g2d.setColor(colores.getColorPiezasRestantes());
		g2d.fill3DRect(posRect[0]+tamRect[0]*canRect+canRect+15,posRect[1]-2,tamRect[0]*canRect+canRect+2,tamRect[1]*canRect+canRect+2,true);

		g2d.setColor(colores.getColorDefault());
		g2d.drawString("Piezas Restantes:",posRect[0]+tamRect[0]*canRect+canRect+30 , posRect[1]+20);		
	}
	
	public void setPiezas(){
		int m,n,p;
		int[]formulas=new int [2];

		if(!piezasZonaPiezas[0] && !piezasZonaPiezas[2] && !piezasZonaPiezas[4] && !piezasZonaPiezas[6]){
			for(p=0; p<piezaConjunto.length-1;p++){
				//if(!piezasZonaPiezas[2*p]){
					piezaConjunto[p]=new Piezas();
					piezaConjunto[p].ajustarPosicion(p);
					piezasZonaPiezas[2*p]=true;
				//}
			}
		}
		
		
		for(p=0;p<piezaConjunto.length-1;p++){
			if(!piezasZonaPiezas[(2*p)+1])
				g2d.setColor(colores.getColorSeleccionPieza());
			else
				g2d.setColor(piezaConjunto[p].getColor());

			boolean[][] pieza= new boolean [4][4];
			pieza=piezaConjunto[p].getPieza();
			
			
			
			for(n=0;n<pieza.length;n++)
				for(m=0;m<pieza[n].length;m++)
					if(pieza[m][n]){
						if(p==0){
							formulas[0]=posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0]);
							formulas[1]=posRect[1]+40+(n*tamRect[1]);
						}else if(p==1){
							formulas[0]=posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0]);
							formulas[1]=posRect[1]+40+(n*tamRect[1]);
						}else if(p==2){
							formulas[0]=posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0]);
							formulas[1]=posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1]);
						}else if(p==3){
							formulas[0]=posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0]);
							formulas[1]=posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1]);
						}
						g2d.fill3DRect(formulas[0],formulas[1],tamRect[0],tamRect[1],true);
					}
		}
	}
	
	public boolean isFigura1() {
		
		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[0].getPieza();
				
		
		for(int n=0;n<pieza.length;n++)
			for(int m=0;m<pieza[n].length;m++)
				if(pieza[m][n]){
					if(posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0])<x && posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0])+tamRect[0]>x
							&& posRect[1]+40+(n*tamRect[1])<y && posRect[1]+40+(n*tamRect[1])+tamRect[1]>y){
						coordsSelectPieza[0]=1;
						coordsSelectPieza[1]=n;
						coordsSelectPieza[2]=m;
						//System.out.println("( "+n+", "+m+" )");
						return true;
					}
					
				}
		return false;
	}
	public boolean isFigura2() {
		
		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[1].getPieza();
				
		for(int n=0;n<pieza.length;n++)
			for(int m=0;m<pieza[n].length;m++)
				if(pieza[m][n]){
					if(posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0])<x && posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0])+tamRect[0]>x
							&& posRect[1]+40+(n*tamRect[1])<y && posRect[1]+40+(n*tamRect[1])+tamRect[1]>y){
						coordsSelectPieza[0]=2;
						coordsSelectPieza[1]=n;
						coordsSelectPieza[2]=m;
				//		System.out.println("( "+n+", "+m+" )");
						return true;
					}
					
				}
		return false;
	}
	public boolean isFigura3() {

		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[2].getPieza();
		
		for(int n=0;n<pieza.length;n++)
			for(int m=0;m<pieza[n].length;m++)
				if(pieza[m][n]){
					if(posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0])<x && posRect[0]+tamRect[0]*canRect+canRect+40+(m*tamRect[0])+tamRect[0]>x
							&& posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1])<y && posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1])+tamRect[1]>y){
						coordsSelectPieza[0]=3;
						coordsSelectPieza[1]=n;
						coordsSelectPieza[2]=m;
			//			System.out.println("( "+n+", "+m+" )");
						return true;
					}
					
				}
		return false;
	}
	public boolean isFigura4() {
		
		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[3].getPieza();
		
		for(int n=0;n<pieza.length;n++)
			for(int m=0;m<pieza[n].length;m++)
				if(pieza[m][n]){
					if(posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0])<x && posRect[0]+tamRect[0]*canRect+canRect+tamRect[0]*canRect-(pieza.length*tamRect[0])+(m*tamRect[0])+tamRect[0]>x
							&& posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1])<y && posRect[1]+tamRect[1]*canRect-20-(pieza[n].length*tamRect[1])+(n*tamRect[1])+tamRect[1]>y){
						coordsSelectPieza[0]=4;
						coordsSelectPieza[1]=n;
						coordsSelectPieza[2]=m;
			//			System.out.println("( "+n+", "+m+" )");
						return true;
					}
				
				}
		return false;
	}

	public boolean isTablero() {
		
		for(int posx=posRect[0], canx=0; canx<canRect; posx=posx+tamRect[0]+1, canx++){
			for(int posy=posRect[1], cany=0; cany<canRect; posy=posy+tamRect[1]+1, cany++){
				g2d.fill3DRect(posx,posy,tamRect[0],tamRect[1],true);
				if(posx<x && posx+tamRect[0]>x && posy<y && posy+tamRect[1]>y){
					coordsSelectTablero[0]=canx;
					coordsSelectTablero[1]=cany;
			//		System.out.println("( "+canx+", "+cany+" )");
					return true;
				}
			}		
		}
		return false;
	}
	public boolean isInTablero(){
		int x, y;
		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[coordsSelectPieza[0]-1].getPieza();
		
		for(int n=0; n<pieza.length; n++){
			for(int m=0; m<pieza.length; m++){
				if(pieza[m][n]){
					x=n-coordsSelectPieza[1];
					y=m-coordsSelectPieza[2];
					if(coordsSelectTablero[0]+y<0 || coordsSelectTablero[0]+y>canRect-1 
							|| coordsSelectTablero[1]+x<0 || coordsSelectTablero[1]+x>canRect-1 
							|| tablero.getEstadoTablero(coordsSelectTablero[1]+x, coordsSelectTablero[0]+y))
						return false;
				}		
			}
		}
		
		return true;
	}
	
	public boolean isInButton(){
		
		if(posRect[0]<x && posRect[0]+80>x && posRect[1]-50<y && posRect[1]-20>y)
			return true;
		return false;
	}
	
	public void setPiezaTablero(){
		int x, y;
		boolean[][] pieza= new boolean [4][4];
		pieza=piezaConjunto[coordsSelectPieza[0]-1].getPieza();
		
		for(int n=0; n<pieza.length; n++){
			for(int m=0; m<pieza.length; m++){
				if(pieza[m][n]){
					x=n-coordsSelectPieza[1];
					y=m-coordsSelectPieza[2];
					tablero.setEstadoTablero(coordsSelectTablero[1]+x, coordsSelectTablero[0]+y, true, piezaConjunto[coordsSelectPieza[0]-1].getColor());
				}		
			}
		}
		piezasZonaPiezas[(coordsSelectPieza[0]-1)*2]=false;
		piezasZonaPiezas[((coordsSelectPieza[0]-1)*2)+1]=true;
		puntuacion+=piezaConjunto[coordsSelectPieza[0]-1].getPuntuacion();
		piezaConjunto[coordsSelectPieza[0]-1]=piezaConjunto[piezaConjunto.length-1];
	}
	
/*	public boolean checkGameOver(){
		int savePieza=coordsSelectPieza[0], savePiezaX=coordsSelectPieza[1], savePiezaY=coordsSelectPieza[2],
				saveTableroX=coordsSelectTablero[0], saveTableroY=coordsSelectTablero[1];
		boolean[][] pieza;
		
		
		for(coordsSelectPieza[0]=1; coordsSelectPieza[0]<piezaConjunto.length; coordsSelectPieza[0]++){
			pieza=piezaConjunto[coordsSelectPieza[0]].getPieza();
			for(coordsSelectPieza[1]=0; coordsSelectPieza[1]<4; coordsSelectPieza[1]++)
				for(coordsSelectPieza[2]=0; coordsSelectPieza[2]<4; coordsSelectPieza[2]++)
					if(pieza[coordsSelectPieza[2]][coordsSelectPieza[1]])
						for(coordsSelectTablero[0]=0; coordsSelectTablero[0]<canRect; coordsSelectTablero[0]++)
							for(coordsSelectTablero[1]=0; coordsSelectTablero[1]<canRect; coordsSelectTablero[1]++)
								if(isInTablero()){
									coordsSelectPieza[0]=savePieza;
									coordsSelectPieza[1]=savePiezaX;
									coordsSelectPieza[2]=savePiezaY;
									coordsSelectTablero[0]=saveTableroX;
									coordsSelectTablero[1]=saveTableroY;
									return false;
								}
		}
		
		coordsSelectPieza[0]=savePieza;
		coordsSelectPieza[1]=savePiezaX;
		coordsSelectPieza[2]=savePiezaY;
		coordsSelectTablero[0]=saveTableroX;
		coordsSelectTablero[1]=saveTableroY;
		
		return true;
				
		
	}
*/	
	public void resetGame(){
		this.g2d=null;
		this.piezaConjunto=new Piezas [5];
		piezaConjunto[piezaConjunto.length-1]=new Piezas (colores.getColorPiezasRestantes());
		this.tablero=new Tablero(canRect);
		for(int p=0; p<piezasZonaPiezas.length; p++){
			if(p%2==0)
				piezasZonaPiezas[p]=false;
			else
				piezasZonaPiezas[p]=true;
		}
		this.coordsSelectPieza= new int [3];
		this.coordsSelectTablero= new int [2];
		this.selected=false;
		this.puntuacion=0;
		
	}
	
	
	public class ControladorMouse extends MouseAdapter{

		public void mousePressed(MouseEvent e) {
			x=e.getX();
			y=e.getY();
					
			if(isInButton()){
				//System.out.println("Has pulsado");
				resetGame();
				repaint();
				return;
			}

			if(selected && isTablero()){
				if(isInTablero()){
					//System.out.println("La pieza mete dentro del Tablero");
					setPiezaTablero();
					/*if(checkGameOver() && (!piezasZonaPiezas[0] && !piezasZonaPiezas[2] && !piezasZonaPiezas[4] && !piezasZonaPiezas[5])){
						System.out.println("Game Over");
					}*/
					repaint();
				}
			}else{
				if(selected=isFigura1()){
					piezasZonaPiezas[1]=false;
					piezasZonaPiezas[3]=true;
					piezasZonaPiezas[5]=true;
					piezasZonaPiezas[7]=true;
					//System.out.println("Has pulsado en la figura 1");
					repaint();
				}else if(selected=isFigura2()){
					piezasZonaPiezas[1]=true;
					piezasZonaPiezas[3]=false;
					piezasZonaPiezas[5]=true;
					piezasZonaPiezas[7]=true;
					//System.out.println("Has pulsado en la figura 2");
					repaint();
				}else if(selected=isFigura3()){
					piezasZonaPiezas[1]=true;
					piezasZonaPiezas[3]=true;
					piezasZonaPiezas[5]=false;
					piezasZonaPiezas[7]=true;
					//System.out.println("Has pulsado en la figura 3");
					repaint();
				}else if(selected=isFigura4()){
					piezasZonaPiezas[1]=true;
					piezasZonaPiezas[3]=true;
					piezasZonaPiezas[5]=true;
					piezasZonaPiezas[7]=false;
					//System.out.println("Has pulsado en la figura 4");
					repaint();
				}else{
					selected=false;
					piezasZonaPiezas[1]=true;
					piezasZonaPiezas[3]=true;
					piezasZonaPiezas[5]=true;
					piezasZonaPiezas[7]=true;
					repaint();
				}
			}
			
			
		}
		
		public void mouseReleased(MouseEvent e) {
		}
	}
	public class ControladorVentana implements WindowListener {
		
		@Override
		public void windowClosing(WindowEvent e){
			frame.dispose();
		}

		public void windowActivated(WindowEvent e) {
			
		}

		public void windowClosed(WindowEvent e) {
			
		}

		public void windowDeactivated(WindowEvent e) {
			
		}

		public void windowDeiconified(WindowEvent e) {
			
		}

		public void windowIconified(WindowEvent e) {
			
		}

		public void windowOpened(WindowEvent e) {
			
		}
		
	}	
	
	public static void main(String[] args){
		new Dibujo();
	}
}




/*System.out.print(" \n");
for(int q=0; q<pieza.length; q++){
	for(int w=0; w<pieza[q].length;w++){
		if(pieza[w][q]==true)
			System.out.print("1 ");
		else
			System.out.print("0 ");
	}
	System.out.print(" \n");		
}
System.out.print(" \n");*/

