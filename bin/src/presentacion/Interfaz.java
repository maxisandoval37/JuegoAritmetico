package presentacion;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


import javax.swing.*;

import multimedia.Sonidos;
import negocio.Logica;

public class Interfaz {
 

	 
	public JFrame frame; 
	private Timer time;
	
	private int constanteEsquina=5;
	private int tiempoActual = 0;
	
    private JTextField[][] cuadrilla =new JTextField[constanteEsquina][constanteEsquina];
    private JLabel[] LabelSumaFila = new JLabel[constanteEsquina];
    private JLabel[] LabelSumaColu = new JLabel[constanteEsquina];
    private JLabel labelPuntos = new JLabel(".....");
    private JLabel imagenFondo = new JLabel("");
    private JLabel lblTiempo = new JLabel("Tiempo");
    private JLabel lblMejorTiempo; 

    private int constanteXsumaFila=0;
    private int constanteYsumaFila=0;
    
    private int constanteXsumaColu=0;
    private int constanteYsumaColu=0;
    
    
    //////////BOTONES
    private JButton btnProbar = new JButton("Probar");
    private JButton btnComenzar = new JButton("Comenzar");
    private JButton btnRendir = new JButton("Rendirse");
    private JButton btnDificil = new JButton("Dificil");
    private JButton btnFacil = new JButton("Facil");
    private JButton btnNormal = new JButton("Normal");   
    private JButton tutorial = new JButton("TUTORIAL"); 
    private JButton btnReiniciar = new JButton("Reiniciar");
    //////////
    
    
	Logica codigo = new Logica();

	public Interfaz() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		labelPuntos.setVisible(false);

		frame.setBounds(100, 100, 583, 392);
		frame.setTitle("Juego");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Logica.abrirArchivoRecord();

		botonFacil();
		botonNormal();
		botonDificil();
		botonTutorial();
		fondo();
		
		Sonidos.sonidoInterfaz();
		
	}

	private void botonFacil() {
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sonidos.sonidoBoton();

				constanteEsquina = 3;
				
				determinarPosicionDeLasSuma(185,50,50,185);
				
				crear();
				ocultarBotones();
				mostrarTodo();
				
			}
		});
		btnFacil.setBounds(91, 303, 106, 23);
		frame.getContentPane().add(btnFacil);
	}

	private void botonNormal() {
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sonidos.sonidoBoton();
				constanteEsquina = 4;
				
				determinarPosicionDeLasSuma(230,50,50,230);
				crear();
				
				ocultarBotones();
				mostrarTodo();

			}
		});
		btnNormal.setBounds(266, 303, 89, 23);
		frame.getContentPane().add(btnNormal);
	}

	private void botonDificil() {
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sonidos.sonidoBoton();
				//tamanoCuadrilla = 25;
				constanteEsquina = 5;
				
				determinarPosicionDeLasSuma(275,50,50,275);
				
				crear();
				ocultarBotones();
				mostrarTodo();
			}
		});
		btnDificil.setBounds(430, 303, 89, 23);
		frame.getContentPane().add(btnDificil);
	}
	
	private void botonTutorial() {
		
		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sonidos.sonidoBoton();
		        URL url=null;
		        try {
		            url = new URL("https://youtu.be/rvXVHrIsL3g");
		            try {
		                Desktop.getDesktop().browse(url.toURI());
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            } catch (URISyntaxException e1) {
		                e1.printStackTrace();
		            }
		        } catch (MalformedURLException e2) {
		            e2.printStackTrace();
		        }
				
				
			}
		});
		tutorial.setBounds(422, 170, 100, 23);
		frame.getContentPane().add(tutorial);
	}
    
    private void determinarPosicionDeLasSuma(int xFila,int yFila,int xColu,int yColu) {
        constanteXsumaFila=xFila;
        constanteYsumaFila=yFila;
        
        constanteXsumaColu=xColu;
        constanteYsumaColu=yColu;
    }
    
    private void ocultarBotones(){
		btnDificil.setVisible(false);
		btnFacil.setVisible(false);
		btnNormal.setVisible(false);
    	
    }
    private void crear() {

    	dibujarCuadrilla();
    	almacenarSumas();
    	limitarInputUsuario();
    	botonComenzar();
    	creartiempo();
    	botonReiniciar();
    	
        botonRendir();
        dibujarSumaColu();
        dibujarSumaFila();
    
        labelPuntos();
        mostrarMejorTiempo();
        botonProbar();
        crearTimer();
        fondo();
        
    }
    
    
	private void mostrarTodo() {
		for (int i = 0; i < constanteEsquina; i++) {
			for (int j = 0; j < constanteEsquina; j++) {
				cuadrilla[i][j].setVisible(true);
			}
		}
		labelPuntos.setVisible(true);
		lblTiempo.setVisible(true);
		btnComenzar.setVisible(true);
		btnRendir.setVisible(true);
		btnProbar.setVisible(true);
		btnReiniciar.setVisible(true);
		tutorial.setVisible(false);
	}

	

    
    private void creartiempo() {
        lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTiempo.setBounds(373, 80, 89, 40);
        lblTiempo.setForeground(Color.WHITE);
        frame.getContentPane().add(lblTiempo);
        lblTiempo.setVisible(false);
    }
    
	// MATRIZ DE TEXTFIELD CON NÚMEROS ALEATORIOS
	private void dibujarCuadrilla() {
		int posX = 0;
		int posY = 0;

		for (int i = 0; i < constanteEsquina; i++) {
			for (int j = 0; j < constanteEsquina; j++) {
				int numeroRandom = codigo.crear_n_Random();

				cuadrilla[i][j] = new JTextField();

				if (j % constanteEsquina == 0) { // pega el salto cuando llega al brode
					posX = 45;// resetea pos de x
					posY += 45;// avanza en y
					cuadrilla[i][j].setBounds(posX, posY, 40, 40);
					cuadrilla[i][j].setName("" + numeroRandom);
				} else {
					posX += 45;// desplaza hacia la der
					cuadrilla[i][j].setBounds(posX, posY, 40, 40);
					cuadrilla[i][j].setName("" + numeroRandom);
				}
				frame.getContentPane().add(cuadrilla[i][j]);
			}
		}
	}
	

	private void almacenarSumas() {

		int numFila = 0;
		int numColu = 1;

		for (int i = 0; i < constanteEsquina; i++) {
			for (int j = 0; j < constanteEsquina; j++) {
				
				if (j % constanteEsquina == 0) { 

					// AGREGA LA SUMA DE LAS FILAS AL MAP
					numFila++;
					Logica.getFilas().put(numFila, Integer.parseInt(cuadrilla[i][j].getName()));

					// AGREGA LA SUMA DE LAS COLU AL MAP
					numColu = 1;
					Logica.calcularSumaColu(numColu, Integer.parseInt(cuadrilla[i][j].getName()));
					
				} else {

					// AGREGA LA SUMA DE LAS FILAS AL MAP
					Logica.calcularSumaFila(numFila, Integer.parseInt(cuadrilla[i][j].getName()));

					// AGREGA LA SUMA DE LAS COLU AL MAP
					numColu++;
					Logica.calcularSumaColu(numColu, Integer.parseInt(cuadrilla[i][j].getName()));
					
				}
			}
		}
	}

	
	private void dibujarSumaFila() {
		int posXsumaFilas = 0;
		int posYsumaFilas = 0;
		
		for (int i = 0; i < constanteEsquina; i++) {

			posXsumaFilas = constanteXsumaFila;
			posYsumaFilas += constanteYsumaFila;
			LabelSumaFila[i] = new JLabel();
			LabelSumaFila[i].setBounds(posXsumaFilas, posYsumaFilas, 26, 14);

			LabelSumaFila[i].setText(Logica.getFilas().get(i + 1).toString());
			
			LabelSumaFila[i].setVisible(false);
			LabelSumaFila[i].setForeground(Color.WHITE);
			frame.getContentPane().add(LabelSumaFila[i]);
		}
	}

	private void dibujarSumaColu() {
		int posXsumaColu = 0;
		int posYsumaColu = 0;

		for (int i = 0; i < constanteEsquina; i++) {

			posXsumaColu += constanteXsumaColu;
			posYsumaColu = constanteYsumaColu;
			LabelSumaColu[i] = new JLabel();
			LabelSumaColu[i].setBounds(posXsumaColu, posYsumaColu, 26, 14);

			LabelSumaColu[i].setText(Logica.getColumnas().get(i + 1).toString());
			
			LabelSumaColu[i].setVisible(false);
			LabelSumaColu[i].setForeground(Color.WHITE);
			frame.getContentPane().add(LabelSumaColu[i]);
		}
	}
	
	
	private void mostrarSumaColumnasFilas() {
		if(!time.isRunning()) {
    		for (int i = 0; i < constanteEsquina; i++) {
    		LabelSumaColu[i].setVisible(true);
    		LabelSumaFila[i].setVisible(true);
    	}
	  }
	}
    
	private void limitarInputUsuario() {// PERMITE SOLO NUMEROS DE UN DIGITO
		for (int cont = 0; cont < constanteEsquina; cont++) {

			int a = cont;
			for (int cont2 = 0; cont2 < constanteEsquina; cont2++) {
				int b = cont2;
				cuadrilla[a][b].addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						if (time.isRunning()) {
							char caracter = e.getKeyChar();
							// Verificar si la tecla pulsada no es un digito
							if (((caracter < '1') || (caracter > '9')) && (caracter != '\b')) {
								e.consume(); // ignorar el evento de teclado
							} else {
								if (cuadrilla[a][b].getText().length() >= 1) // NO DEJA INGRESAR MAS DE UN DIGITO
									e.consume();
							}
						} else
							e.consume();
					}
				});
			}
		}
	}
	

	
	//INICIO TEMPORIZADOR
	private void crearTimer() {
		time = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelPuntos.setText(Integer.toString(tiempoActual));
				tiempoActual++;
			}
		});
	}
	
    private int getTiempoActual() {
        return tiempoActual;
    }
 
    private void setTiempoActual(int tiempoActual) {
        this.tiempoActual = tiempoActual;
    }
	//        FIN TEMPORIZADOR
 
    
	//        INICIO BOTONES
	private void botonComenzar() {
		btnComenzar.setVisible(false);
        btnComenzar.addActionListener(new ActionListener() {       	
            public void actionPerformed(ActionEvent e) { 
            	Sonidos.sonidoBoton();
            	mostrarSumaColumnasFilas();
            	if(getTiempoActual()==0)
            	time.start();	
                }
        });
        btnComenzar.setBounds(91, 303, 106, 23);
        frame.getContentPane().add(btnComenzar);////
	}

	private void botonRendir() {
		btnRendir.setVisible(false);
        btnRendir.addActionListener(new ActionListener() {       	
            public void actionPerformed(ActionEvent e) { 
            	Sonidos.sonidoBoton();
            	rendirseYmostrarResolucion();
                }
        });
        btnRendir.setBounds(420, 303, 106, 23);
        frame.getContentPane().add(btnRendir);
	}
	
	private void rendirseYmostrarResolucion() {
		if (time.isRunning()) {
			for (int i = 0; i < constanteEsquina; i++) {
				for (int j = 0; j < constanteEsquina; j++) {
					System.out.println(""+i+j);
				   cuadrilla[i][j].setText(cuadrilla[i][j].getName());
				   cuadrilla[i][j].setBackground(Color.RED);
				}

				time.stop();
			}
		}
	}

	
	private void labelPuntos() {
		labelPuntos.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelPuntos.setBounds(393, 114, 55, 53);
		labelPuntos.setForeground(Color.WHITE);
		frame.getContentPane().add(labelPuntos);
	}


	private void mostrarMejorTiempo() {
		lblMejorTiempo = new JLabel("Mejor tiempo : " + Logica.tiempoRecord);
		lblMejorTiempo.setFont(new Font("Tahoma",Font.BOLD,20));
		lblMejorTiempo.setBounds(320,10,300,100);
		lblMejorTiempo.setForeground(Color.WHITE);
		frame.getContentPane().add(lblMejorTiempo);
	}

	
	private void fondo() {
		imagenFondo.setIcon(new ImageIcon(Interfaz.class.getResource("/presentacion/FONDO.png")));
		imagenFondo.setBounds(0, 0, 567, 353);
		frame.getContentPane().add(imagenFondo);
	}
	
	private void botonReiniciar() { //////
		btnReiniciar.setVisible(false);
		btnReiniciar.addActionListener(new ActionListener() {       	
            public void actionPerformed(ActionEvent e) { 
            	
            	Sonidos.sonidoBoton();
            
            	for (int i = 0; i < constanteEsquina; i++) {
        			for (int j = 0; j < constanteEsquina; j++) {
        				cuadrilla[i][j].setVisible(false);
        			}
        		}
            	
            	for (int i = 0; i < constanteEsquina; i++) {
            		LabelSumaColu[i].setVisible(false);
            		LabelSumaFila[i].setVisible(false);
            	}
            	labelPuntos.setVisible(false);
        		lblTiempo.setVisible(false);
        		btnComenzar.setVisible(false);
        		btnRendir.setVisible(false);
        		btnProbar.setVisible(false);
        		btnReiniciar.setVisible(false);
        		lblMejorTiempo.setVisible(false);
        		
        		btnDificil.setVisible(true);
        		btnFacil.setVisible(true);
        		btnNormal.setVisible(true);
        		labelPuntos.setText(".....");
        		setTiempoActual(0);
        		time.stop();
                }
        });
		btnReiniciar.setBounds(420, 200, 106, 23);
        frame.getContentPane().add(btnReiniciar);
	}
	

	private void botonProbar() {
		btnProbar.setVisible(false);
		btnProbar.setBounds(266, 303, 89, 23);
		frame.getContentPane().add(btnProbar);

		btnProbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sonidos.sonidoBoton();
				Logica.compararRecords(tiempoActual);

				if (time.isRunning()) {
					if (comprobarVictoria()) {
						for (int i = 0; i < constanteEsquina; i++) {
							
							pintarColu(i,Color.GREEN);
							pintarFila(i,Color.GREEN);
						}
						time.stop();
					} else {
						setTiempoActual(tiempoActual+10);

						for (int i = 0; i < constanteEsquina; i++) {
							if (comprobarFila(i)) {
								pintarFila(i,Color.GREEN);
							}
							else {
								pintarFila(i,Color.WHITE);
							}

							if (comprobarColumna(i)) {
								pintarColu(i,Color.GREEN);
							}
							else {
								pintarColu(i,Color.WHITE);
							}
						  }						



						}
					}
				}
			

		});
	}
	
	private void pintarFila(int fila,Color f) {
		LabelSumaFila[fila].setForeground(f);;
	}
	
	private void pintarColu(int columna, Color c) {
		LabelSumaColu[columna].setForeground(c);
	}
	
	private boolean comprobarVictoria() {// COMPRUEBA SI TODOS LOS TEXTFIELD SON VERDES
        boolean ret = true;
        for (int i = 0; i < constanteEsquina; i++) {
        	for(int j=0;j<constanteEsquina;j++) {
        		ret=ret&&(comprobarFila(i)&&comprobarColumna(j));
        	}   
        }
        return ret;
    }
	
	private boolean comprobarFila(int fila) {
		int suma = 0;

		for (int i = 0; i < constanteEsquina; i++) {

			int imput = Integer.parseInt(cuadrilla[fila][i].getText() + "0");
			suma = Logica.sumaNumeros(suma, imput / 10);
		}
		return Logica.equalsNumeros(suma, Integer.parseInt(LabelSumaFila[fila].getText()));
	}
	
	private boolean comprobarColumna(int columna) {
		int suma = 0;
		for (int i = 0; i < constanteEsquina; i++) {
			int imput = Integer.parseInt(cuadrilla[i][columna].getText() + "0");
			suma = Logica.sumaNumeros(suma, imput/10);
		}
		return Logica.equalsNumeros(suma, Integer.parseInt(LabelSumaColu[columna].getText()));
	}
	// FIN BOTONES
}