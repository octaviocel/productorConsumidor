/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorconsumidor;

import java.util.Random;

/**
 *
 * @author Octavio
 */
public class Productor extends Thread {

    private Bodega bodega;
    private Integer productos;

    public Productor(Bodega bodega) {
        productos = 0;
        this.bodega = bodega;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Random r = new Random();
                Integer produccion = r.nextInt(30); //aleatorio para producir 
                Integer time = (r.nextInt(4) + 1) * 1000; //aleatorio para darle cada cuantos segundos producira

                synchronized (bodega) {
                    bodega.addTarta(produccion); //añade la produccion a bodega
                    System.out.println("Existen: " + bodega.getTarta() + " tartas");
                    bodega.notifyAll(); //notifica que ya estan las tartas
                }
                Thread.sleep(time); //duerme la produccion 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
