package br.unb.appdev.igor;

import java.util.List;

/**
 * Created by paulo on 10/19/18.
 */

public class Adventure {
    String name;
    int photoId;
    List<Evento> eventos;

    Adventure(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}
