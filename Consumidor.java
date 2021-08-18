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
public class Consumidor extends Thread {

    private Integer solicitud;
    private Bodega bodega;
    private String nombre;

    public Consumidor(Bodega bodega, String name) {
        Random r = new Random();
        this.solicitud = r.nextInt(20) + 1; //genera un aleatorio entre 1 y 20 para pedir tartas
        this.bodega = bodega;
        this.nombre = name;
    }

    private String getNombre() {
        return nombre;
    }

    private void newSolicitud() {
        Random r = new Random();
        this.solicitud = r.nextInt(20) + 1;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getNombre() + ": quiero " + solicitud + " tartas"); //cuando se presenta, hace la presentacion de solicitud de pedido
            synchronized (bodega) { //sincroniza la baodega
                while (true) {
                    if (bodega.despachar(solicitud)) { //si lo puede despachar lo despacha si no le dice que espere
                        System.out.println(String.format("DESPACHADO: %s pidio %d tartas, quedan %d", getNombre(), solicitud, bodega.getTarta()));
                        break;
                    } else {
                        System.out.println(getNombre() + ": Esperando");
                        try {
                            bodega.wait(); 
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                }

            }
            newSolicitud(); //despachado, se crea una nueva solicitud
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}
