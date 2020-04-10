package presentacion;

import java.awt.EventQueue;

public class Main_TP1 {
	
	   public static void main(String[] args) { 
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Interfaz window = new Interfaz();
	                    window.frame.setVisible(true);
	                    window.frame.setResizable(false);
	                    
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
}
