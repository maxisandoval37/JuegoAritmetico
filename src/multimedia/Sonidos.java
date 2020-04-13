package multimedia;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonidos {
	
	//SONIDO PARA LA INTERFA (EN CASO DE QUE NO ENCUENTRE EL ARCHIVO DE SONIDO, TIRA UNA EXCEPCION)
	public static void sonidoInterfaz()
	{
		try {
           Clip sonido = AudioSystem.getClip();
           File a = new File("src/multimedia/sonidoInterfaz.wav");
           sonido.open(AudioSystem.getAudioInputStream(a));         
           sonido.start();          
           sonido.loop(10);
         } catch (Exception tipoError) {
            System.out.println("" + tipoError);
         }
	}
	
	public static void sonidoBoton()
	{
		try {
           Clip sonido = AudioSystem.getClip();
           File a = new File("src/multimedia/sonidoBoton.wav");
           sonido.open(AudioSystem.getAudioInputStream(a));         
           sonido.start();          
         } catch (Exception tipoError) {
            System.out.println("" + tipoError);
         }
	}

}
