/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */

package tortenelem;

/* @author Máté Andrea */
public class Evszam {
private int id, ev;
private String esemeny;

    public void setId(int id) {
        this.id = id;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public void setEsemeny(String esemeny) {
        this.esemeny = esemeny;
    }

    public int getId() {
        return id;
    }

    public int getEv() {
        return ev;
    }

    public String getEsemeny() {
        return esemeny;
    }

    public Evszam(int id, int ev, String esemeny) {
        this.id = id;
        this.ev = ev;
        this.esemeny = esemeny;
    }
}
