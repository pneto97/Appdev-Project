package br.unb.appdev.igor;

/**
 * Created by paulo on 11/9/18.
 */

public class Evento {
    MyDate dataEvento;
    String nomeEvento;
    String textoEvento;

    Evento(MyDate dataEvento, String nomeEvento) {
        this.dataEvento = dataEvento;
        this.nomeEvento = nomeEvento;
        this.textoEvento = "";
    }
}
