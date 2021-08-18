/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorconsumidor;

/**
 *
 * @author Octavio
 */
public class Main {
    
    public static void main(String[] args) {
        Bodega bodega = new Bodega();
        
        Productor productor = new Productor(bodega);                    
        
        Consumidor c1 = new Consumidor(bodega, "Octavio");
        Consumidor c2 = new Consumidor(bodega, "Cris");
        Consumidor c3 = new Consumidor(bodega, "Marco");
        Consumidor c4 = new Consumidor(bodega, "Fer");
        
        productor.start();        
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
