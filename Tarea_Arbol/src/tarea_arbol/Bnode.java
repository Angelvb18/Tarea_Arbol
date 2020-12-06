package tarea_arbol;

import java.io.Serializable;

public class Bnode implements Serializable {
    protected KRRN keys[];//llaves
    protected int t;//rango minimo de las llaves
    protected Bnode childs[];//hijos de x
    protected int n;//numero actual de llaves
    protected boolean leaf;//leaf or not

    public Bnode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        keys=new KRRN[2*t-1];
        childs=new Bnode[2*t];
        n=0;
        
    }
}


