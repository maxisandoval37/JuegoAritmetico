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
import negocio.Record;

public class Interfaz {
 
	public JFrame frame; 
	private Timer time;
	
	private int constanteEsquina=5;
	private int tiempoActual = 0;
	
    private JTextField[][] cuadrilla =new JTextField[constanteEsquina][constanteEsquina];
    private JLabel[] LabelSumaFila = new JLabel[constanteEsquina];
    private JLabel[] LabelSumaColu = new JLabel[constanteEsquina];
    private JLabel labelPuntos = new JLabel(".....");
    private JLabel imagen = new JLabel("");
    private JLabel lblTiempo = new JLabel("Tiempo");
    private JLabel lblMejorTiempo; 
    private Color colorRosa=new Color(255, 77, 77);

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
		

		botonFacil();
		botonNormal();
		botonDificil();
		botonTutorial();
		cambiarImagen(new ImageIcon(Interfaz.class.getResource("/presentacion/INICIO.png")));
		
		Sonidos.sonidoInterfaz();
		
	}

	private void botonFacil() {
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record.abrirArchivoYCopiar("src/negocio/RecordFacil.txt");
				Logica.facil = true;
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
				Record.abrirArchivoYCopiar("src/negocio/RecordNormal.txt");
				Logica.normal = true;
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

				Record.abrirArchivoYCopiar("src/negocio/RecordDificil.txt");
				Logica.dificil = true;
				Sonidos.sonidoBoton();
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
        resolver();
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
		cambiarImagen(new ImageIcon(Interfaz.class.getResource("/presentacion/FONDO.png")));
	}
    
    private void creartiempo() {
        lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTiempo.setBounds(373, 120, 89, 40);
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
							if (Logica.esUnNumuero(caracter)) {
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

	private void botonTutorial() {

		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				URL url = null;
				try {
					url = new URL("https://youtu.be/ARYf7cvr4YQ");
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
		tutorial.setBounds(422, 120, 100, 23);
		frame.getContentPane().add(tutorial);
	}
    
	private void botonComenzar() {
		btnComenzar.setVisible(false);
        btnComenzar.addActionListener(new ActionListener() {       	
            public void actionPerformed(ActionEvent e) { 
            	btnComenzar.setVisible(false);
            	Sonidos.sonidoBoton();
            	mostrarSumaColumnasFilas();
            	if(getTiempoActual()==0)
            	time.start();	
                }
        });
        btnComenzar.setBounds(91, 303, 106, 23);
        frame.getContentPane().add(btnComenzar);
	}

	private void botonRendir() {
		btnRendir.setVisible(false);
		btnRendir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (time.isRunning()) {
					btnProbar.setVisible(false);
					Sonidos.sonidoBoton();
					rendirseYmostrarResolucion();
					btnRendir.setVisible(false);
				}
			}
		});
		btnRendir.setBounds(420, 303, 106, 23);
		frame.getContentPane().add(btnRendir);
	}
	
	
	private void labelPuntos() {
		labelPuntos.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelPuntos.setBounds(393, 140, 55, 53);
		labelPuntos.setForeground(Color.WHITE);
		frame.getContentPane().add(labelPuntos);
	}


	private void mostrarMejorTiempo() {
		lblMejorTiempo = new JLabel("Mejor tiempo : " + Record.getTiempoRecord());
		lblMejorTiempo.setFont(new Font("Tahoma",Font.BOLD,20));
		lblMejorTiempo.setBounds(320,30,300,100);
		lblMejorTiempo.setForeground(Color.WHITE);
		frame.getContentPane().add(lblMejorTiempo);
	}

	private void botonReiniciar() { //////
		btnReiniciar.setVisible(false);
		btnReiniciar.addActionListener(new ActionListener() {       	
            public void actionPerformed(ActionEvent e) { 
            	
            	Logica.facil = false;
            	Logica.normal = false;
            	Logica.dificil = false;
            	
            	Logica.Sumafilas.clear();
                Logica.Sumacolumnas.clear();
                        
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
		btnReiniciar.setBounds(420, 240, 106, 23);
        frame.getContentPane().add(btnReiniciar);
	}
	
	private void botonProbar() {
		btnProbar.setVisible(false);
		btnProbar.setBounds(266, 303, 89, 23);
		frame.getContentPane().add(btnProbar);

		btnProbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaJugadorGana();
				Sonidos.sonidoBoton();
				

				if (time.isRunning()) {
					if (comprobarVictoria()) {
						Record.compararTiempo(tiempoActual);
						
						for (int i = 0; i < constanteEsquina; i++) {
							pintarColu(i, Color.GREEN);
							pintarFila(i, Color.GREEN);
						}
						time.stop();
					} else {
						setTiempoActual(tiempoActual + 10);

						for (int i = 0; i < constanteEsquina; i++) {
							if (comprobarSumaFila(i)) {
								pintarFila(i, Color.GREEN);
							} else {
								pintarFila(i, Color.WHITE);
							}
							if (comprobarSumaColumna(i)) {
								pintarColu(i, Color.GREEN);
							} else {
								pintarColu(i, Color.WHITE);
							}
						}
					}
				}
			}
		});
	}
	
	// FIN BOTONES
	private void pintarFila(int fila,Color f) {
		LabelSumaFila[fila].setForeground(f);;
	}
	
	private void pintarColu(int columna, Color c) {
		LabelSumaColu[columna].setForeground(c);
	}
	
	private void cambiarImagen(ImageIcon img) {
		imagen.setIcon(img);
		imagen.setBounds(0, 0, 567, 353);
		frame.getContentPane().add(imagen);
	}
	
	private void rendirseYmostrarResolucion() {
		if (time.isRunning()) {
			for (int i = 0; i < constanteEsquina; i++) {
				for (int j = 0; j < constanteEsquina; j++) {
				   cuadrilla[i][j].setText(cuadrilla[i][j].getName());
				   cuadrilla[i][j].setBackground(colorRosa);
				}
				time.stop();
			}
		}
	}
	
	private boolean comprobarVictoria() {// COMPRUEBA SI TODAS LAS COLUMNAS Y FILAS SON CORRECTAS
        boolean ret = true;
        for (int i = 0; i < constanteEsquina; i++) {
        	for(int j=0;j<constanteEsquina;j++) {
        		ret=ret&&(comprobarSumaFila(i)&&comprobarSumaColumna(j));
        	}   
        }
        return ret;
    }
	
	private boolean comprobarSumaFila(int fila) {
		int suma = 0;
		boolean ret1= true;
		boolean ret2= true;
		
		for (int i = 0; i < constanteEsquina; i++) {
			int imput = Integer.parseInt(cuadrilla[fila][i].getText() + "0");
			suma = Logica.sumaNumeros(suma, imput / 10);
			ret2 = ret2 && !cuadrilla[fila][i].getText().isEmpty();
		}
		ret1 = ret1 && Logica.equalsNumeros(suma, Integer.parseInt(LabelSumaFila[fila].getText()));
		return ret1&&ret2;
	}
	
	private boolean comprobarSumaColumna(int columna) {
		int suma = 0;
		boolean ret1= true;
		boolean ret2= true;
		
		for (int i = 0; i < constanteEsquina; i++) {
			int imput = Integer.parseInt(cuadrilla[i][columna].getText() + "0");
			suma = Logica.sumaNumeros(suma, imput/10);
			ret2 = ret2 && !cuadrilla[i][columna].getText().isEmpty();
		}
		
		ret1 = ret1 && Logica.equalsNumeros(suma, Integer.parseInt(LabelSumaColu[columna].getText()));
		return ret1&&ret2;
	}
	
	private void pantallaJugadorGana() {
		if (comprobarVictoria()) {
			cambiarImagen(new ImageIcon(Interfaz.class.getResource("/presentacion/FIN.png")));
			btnComenzar.setVisible(false);
			btnRendir.setVisible(false);
			btnProbar.setVisible(false);
			for (int i = 0; i < constanteEsquina; i++) {
				LabelSumaColu[i].setVisible(false);
				LabelSumaFila[i].setVisible(false);
				for (int j = 0; j < constanteEsquina; j++) {
					cuadrilla[i][j].setVisible(false);
				}
			}
		}
	}


	public void resolver () {//aux, para que el programador vea las respuestas del juego
		for (int i = 0;i<constanteEsquina;i++) {
			for (int j = 0;j<constanteEsquina;j++) {
				System.out.println(cuadrilla[i][j].getName());
			}
		}
	}
}