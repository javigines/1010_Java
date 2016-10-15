package juego;

import java.awt.Color;
import java.util.Random;

public class Piezas {
	
	private boolean[][] pieza= {{false,false,false,false},
								{false,false,false,false},
								{false,false,false,false},
								{false,false,false,false}};
	private Color color;
	private int puntuacion;
	private int [] porcentPieza= {0, 10, 10, 10, 10, 10, 8, 12, 8, 5, 2, 2, 8, 10, 3};

	private Random random=new Random();
	
	
	
	public Piezas(){
		for(int n=1; n<porcentPieza.length; n++)
			porcentPieza[0]+=porcentPieza[n];
		for(int n=2; n<porcentPieza.length; n++)
				porcentPieza[n]+=porcentPieza[n-1];
		
		setPieza();
		
	}
	public Piezas(Color color){
		this.color=color;
	}
	
	public void setPieza(){
		int p = random.nextInt(porcentPieza[0]);
		
		if(p<=porcentPieza[1]){
			pieza1();
		}else if(p<=porcentPieza[2]){
			pieza2();
		}else if(p<=porcentPieza[3]){
			pieza3();
		}else if(p<=porcentPieza[4]){
			pieza4();
		}else if(p<=porcentPieza[5]){
			pieza5();
		}else if(p<=porcentPieza[6]){
			pieza6();
		}else if(p<=porcentPieza[7]){
			pieza7();
		}else if(p<=porcentPieza[8]){
			pieza8();
		}else if(p<=porcentPieza[9]){
			pieza9();
		}else if(p<=porcentPieza[10]){
			pieza10();
		}else if(p<=porcentPieza[11]){
			pieza11();
		}else if(p<=porcentPieza[12]){
			pieza12();
		}else if(p<=porcentPieza[13]){
			pieza13();
		}else if(p<=porcentPieza[14]){
			pieza14();
		}



		p = random.nextInt(100);

		if(p%4==0){
			rotate();
		}else if(p%4==1){
			rotate();
			rotate();
		}else if(p%4==2){
			rotate();
			rotate();
			rotate();
		}
	}
	public boolean[][] getPieza() {
		return pieza;
	}
	
	public void pieza1(){
		this.resetPieza();
//		System.out.println("Pieza1");
		setColor(1);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[0][1]=true;
		this.pieza[0][2]=true;
		this.pieza[1][2]=true;		
	}
	public void pieza2(){
		this.resetPieza();

//		System.out.println("Pieza2");
		setColor(2);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[0][1]=true;
		this.pieza[1][1]=true;		
	}
	public void pieza3(){
		this.resetPieza();

//		System.out.println("Pieza3");
		setColor(3);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[0][1]=true;
		this.pieza[0][2]=true;		
	}
	public void pieza4(){
		this.resetPieza();

//		System.out.println("Pieza4");
		setColor(4);
		setPuntuacion(5);
		
		this.pieza[1][0]=true;
		this.pieza[2][0]=true;
		this.pieza[0][1]=true;
		this.pieza[1][1]=true;		
	}
	public void pieza5(){
		this.resetPieza();

//		System.out.println("Pieza5");
		setColor(5);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[1][1]=true;
		this.pieza[2][1]=true;		
	}
	public void pieza6(){
		this.resetPieza();

//		System.out.println("Pieza6");
		setColor(6);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[2][0]=true;
		this.pieza[3][0]=true;		
	}
	public void pieza7(){
		this.resetPieza();

//		System.out.println("Pieza7");
		setColor(7);
		setPuntuacion(3);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;	
	}
	public void pieza8(){
		this.resetPieza();

//		System.out.println("Pieza8");
		setColor(8);
		setPuntuacion(2);
		
		this.pieza[0][0]=true;		
	}
	public void pieza9(){
		this.resetPieza();

//		System.out.println("Pieza9");
		setColor(9);
		setPuntuacion(5);
		
		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[1][1]=true;
		this.pieza[2][0]=true;		
	}
	public void pieza10(){
		this.resetPieza();

//		System.out.println("Pieza10");
		setColor(10);
		setPuntuacion(10);
		
		this.pieza[0][0]=true;
		this.pieza[0][1]=true;
		this.pieza[1][0]=true;
		this.pieza[2][0]=true;	
		this.pieza[2][1]=true;		
	}
	public void pieza11(){
		this.resetPieza();

//		System.out.println("Pieza11");
		setColor(11);
		setPuntuacion(6);
		
		this.pieza[0][0]=true;
		this.pieza[0][1]=true;
		this.pieza[0][2]=true;
		this.pieza[1][0]=true;	
		this.pieza[1][1]=true;	
		this.pieza[1][2]=true;	
		this.pieza[2][0]=true;	
		this.pieza[2][1]=true;	
		this.pieza[2][2]=true;		
	}
	public void pieza12(){
		this.resetPieza();

//		System.out.println("Pieza12");
		setColor(11);
		setPuntuacion(4);
		
		this.pieza[0][0]=true;
		this.pieza[0][1]=true;
		this.pieza[1][0]=true;	
	}
	public void pieza13(){
		this.resetPieza();

//		System.out.println("Pieza13");
		setColor(12);
		setPuntuacion(4);
		
		this.pieza[0][0]=true;
		this.pieza[0][1]=true;
		this.pieza[0][2]=true;		
	}
	public void pieza14(){
		this.resetPieza();

//		System.out.println("Pieza14");
		setColor(13);
		setPuntuacion(6);

		this.pieza[0][0]=true;
		this.pieza[1][0]=true;
		this.pieza[2][0]=true;
		this.pieza[0][1]=true;
		this.pieza[0][2]=true;		
	}

	public void setColor(int p){
		if(p==1){
			color=new Color(0,102,102);
		}else if(p==2){
			color=new Color(178,102,255);
		}else if(p==3){
			color=new Color(102,178,255);
		}else if(p==4){
			color=new Color(102,255,178);
		}else if(p==5){
			color=new Color(102,255,102);
		}else if(p==6){
			color=new Color(255,178,102);
		}else if(p==7){
			color=new Color(76,153,0);
		}else if(p==8){
			color=new Color(127,0,255);
		}else if(p==9){
			color=new Color(0,128,255);
		}else if(p==10){
			color=new Color(0,204,0);
		}else if(p==11){
			color=new Color(0,255,255);
		}else if(p==12){
			color=new Color(255,128,0);
		}else if(p==13){
			color=new Color(51,51,255);
		}else if(p==14){
			color=new Color(255,0,0);
		}else if(p==15){
			color=new Color(153,0,153);
		}
	}
	public Color getColor(){
		return color;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	public void rotate(){
		boolean [][] aux=new boolean [pieza.length][pieza[0].length];
		
		for(int p=0, j=0; p<pieza.length; p++, j++){
			for(int k=pieza.length-1, l=0; k>=0; k--, l++){
				aux[k][p]=pieza[j][l];
			}
		}
		pieza=aux;
	}
	
	public void resetPieza() {
		for(int p=0; p<pieza.length; p++)
			for(int j=0; j<pieza[p].length;j++)
				pieza[p][j]=false;
	}
	
	public void ajustarPosicion(int pos){
		if(pos==0){
			while(pieza[0][0]==false&&pieza[0][1]==false&&pieza[0][2]==false&&pieza[0][3]==false){
				for(int p=0; p+1<pieza.length;p++){
					for(int m=0; m<pieza.length;m++){
						pieza[p][m]=pieza[p+1][m];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[pieza.length-1][m]=false;
				}
			}
			while(pieza[0][0]==false&&pieza[1][0]==false&&pieza[2][0]==false&&pieza[3][0]==false){
				for(int p=0; p+1<pieza.length;p++){
					for(int m=0; m<pieza.length;m++){
						pieza[m][p]=pieza[m][p+1];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[m][pieza.length-1]=false;
				}
			}
		}else if(pos==1){
			while(pieza[pieza.length-1][0]==false&&pieza[pieza.length-1][1]==false&&pieza[pieza.length-1][2]==false&&pieza[pieza.length-1][3]==false){
				for(int p=pieza.length-1; p>0;p--){
					for(int m=0; m<pieza.length;m++){
						pieza[p][m]=pieza[p-1][m];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[0][m]=false;
				}
			}
			while(pieza[0][0]==false&&pieza[1][0]==false&&pieza[2][0]==false&&pieza[3][0]==false){
				for(int p=0; p+1<pieza.length;p++){
					for(int m=0; m<pieza.length;m++){
						pieza[m][p]=pieza[m][p+1];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[m][pieza.length-1]=false;
				}
			}
		}else if(pos==2){
			while(pieza[0][0]==false&&pieza[0][1]==false&&pieza[0][2]==false&&pieza[0][3]==false){
				for(int p=0; p+1<pieza.length;p++){
					for(int m=0; m<pieza.length;m++){
						pieza[p][m]=pieza[p+1][m];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[pieza.length-1][m]=false;
				}
			}
			while(pieza[0][pieza.length-1]==false&&pieza[1][pieza.length-1]==false&&pieza[2][pieza.length-1]==false&&pieza[3][pieza.length-1]==false){
				for(int p=pieza.length-1; p>0;p--){
					for(int m=0; m<pieza.length;m++){
						pieza[m][p]=pieza[m][p-1];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[m][0]=false;
				}
			}
		}else if(pos==3){
			while(pieza[pieza.length-1][0]==false&&pieza[pieza.length-1][1]==false&&pieza[pieza.length-1][2]==false&&pieza[pieza.length-1][3]==false){
				for(int p=pieza.length-1; p>0;p--){
					for(int m=0; m<pieza.length;m++){
						pieza[p][m]=pieza[p-1][m];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[0][m]=false;
				}
			}
			while(pieza[0][pieza.length-1]==false&&pieza[1][pieza.length-1]==false&&pieza[2][pieza.length-1]==false&&pieza[3][pieza.length-1]==false){
				for(int p=pieza.length-1; p>0;p--){
					for(int m=0; m<pieza.length;m++){
						pieza[m][p]=pieza[m][p-1];
					}
				}
				for(int m=0; m<pieza.length;m++){
					pieza[m][0]=false;
				}
			}
		} 
	}
}
