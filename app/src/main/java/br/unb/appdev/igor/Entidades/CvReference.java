package br.unb.appdev.igor.Entidades;

public class CvReference {

    private String title;
    private String image;
    private int hpMonstro;
    private int atqMonstro;

    public CvReference() {
        this.title = title;
        this.image = image;
        this.hpMonstro = hpMonstro;
        this.atqMonstro = atqMonstro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHpMonstro() {
        return hpMonstro;
    }

    public void setHpMonstro(int hpMonstro) {
        this.hpMonstro = hpMonstro;
    }

    public int getAtqMonstro() {
        return atqMonstro;
    }

    public void setAtqMonstro(int atqMonstro) {
        this.atqMonstro = atqMonstro;
    }
}
