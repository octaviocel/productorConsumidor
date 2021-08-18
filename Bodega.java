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
public class Bodega {

    private Integer tartas;

    public Bodega() {
        this.tartas = 0;
    }

    public synchronized void addTarta(Integer tartas) {
        this.tartas+=tartas; //sincroniza los metodos para que no se interrumpan cuando se les llame
    }

    public synchronized Integer getTarta() {
        return tartas;
    }

    public boolean despachar(Integer tarta) { //primero verifica que lo que pide este dentro de lo hay
        if (tarta <= getTarta()) {
            synchronized (getClass()) {
                this.tartas = getTarta() - tarta;
            }
            return true;
        } else {
            return false;
        }

    }
}
