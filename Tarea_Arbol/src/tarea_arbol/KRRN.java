package tarea_arbol;
import java.io.Serializable;



public class KRRN implements Serializable {
    protected int key;
    protected int RRN;

    public KRRN() {
        key=-1;
        RRN=-1;
    }

    public KRRN(int key, int RRN) {
        this.key = key;
        this.RRN = RRN;
    }
}


