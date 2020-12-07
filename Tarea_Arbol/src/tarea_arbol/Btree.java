package tarea_arbol;

import java.io.Serializable;


public class Btree implements Serializable {
    protected Bnode root;
    protected int t;
    
    public Btree(int t) { // se inicializa el arbol-b vacio
        root=null;
        this.t = t;
        
    }
    
    public void PrintTree(Bnode node, String nivel){
        if(node == null){
            System.out.println(nivel + "El Árbol está Vacío");
        }else{
            System.out.println(nivel + "");
            String nivelChild = nivel + "\t";
            
            for (int i = 0; i < node.n; i++) {
                if (!node.leaf) {
                    System.out.print("{");
                    PrintTree(node.childs[i], nivelChild);
                    System.out.print("}");
                }
                
                if (node.keys[i].key > 0) {
                    System.out.print(nivelChild + node.keys[i].key);
                }
            }
            
            if (!node.leaf) {
                System.out.print("{");
                PrintTree(node.childs[node.n], nivelChild);
                System.out.print("}");
            }
            
        }
        System.out.println("");
    }
 
    public void insert(int key){
        if (root==null) {
            root=new Bnode(t,true);
            //root.getKeys()[root.getN()]=key;
            root.keys[0]=new KRRN(key);
            //root.setN(root.getN()+1);//aumenta el numero de llaves
            root.n+=1;//aumenta el numero de llaves
        }
        else if(root.n==2*t-1){//root is full
            //new node
            Bnode r=root;
            Bnode s=new Bnode(t,false);
            root=s;
            s.childs[0]=r;//un hijo del nuevo nodo pasa a ser la antigua raiz
            //split
            split(s,0,r);
            int i=0;
            if(s.keys[0].key<key){
                i++;
            }
            //from new root we insert in a aproppiate child
            insertNonFull(s.childs[i], key);
        }
        else{
            //insert in root node
            insertNonFull(root,key);
        }
    }
    
    public void insertNonFull(Bnode x,int key){
        int i=x.n-1;//array last element
        if (x.leaf==true) {
            while (i>=0&&key<x.keys[i].key) { //se corren los elementos mayores a la derecha
                x.keys[i+1]=x.keys[i];
                i--;
            }
            x.keys[i+1]=new KRRN(key);//se inserta el elemento en la posicion correcta
            x.n=x.n+1;//aumenta el numero de llaves de x
        }else{//not leaf
            
            //encontrar hijo en el cual insertar el elemento
            while (i>=0&&key<x.keys[i].key) {
                i--;
            }
            i=i+1;
            //found child
            //full child
            if (x.childs[i].n==2*t-1) {
                
                //split
                split(x, i, x.childs[i]);
                
                //find child to insert middle key
                if (key>x.keys[i].key) {
                    i++;
                }
            }
            
            insertNonFull(x.childs[i], key);
            
        }
        
    }
    public void split(Bnode x,int i,Bnode y){
        //z new node, no leaf and same t as y
        Bnode z=new Bnode(y.t,y.leaf);
        
        //z number of keys one less than new root
        z.n=x.t-1;
        
        // last keys from y to z
        for (int j = 0; j < x.t-1; j++) {
            z.keys[j]=y.keys[j+x.t];
            
        }
        //child of y to z
        if (y.leaf==false) {
            for (int j = 0; j < x.t; j++) {
                z.childs[j]=y.childs[j+x.t];
            }
            
        }
        //old root now one less key
        y.n=x.t-1;
        
        //sort childs of new root
        for (int j = x.n; j>=i+1 ; j--) {
            x.childs[j+1]=x.childs[j];
        }
        
        //rigth child of new root = new node with t-1 keys
        x.childs[i+1]=z;
        
        //sort keys in new root
        for (int j = x.n-1; j >=i; j--) {
            x.keys[j+1]=x.keys[j];
        }
        
        //copy middle key to new root
        x.keys[i]=y.keys[x.t-1];
        //i plus key in new root
        x.n+=1;
    }
    
    //funcion buscar llave(retorna RRN o -1)
    int search(Bnode x,int k){ 
        // primer llave mayor o igual a k
        int i = 0; 
        while (i < x.n && k > x.keys[i].key){
            i++; 
        } 
        
        if (i<x.n && x.keys[i].key == k){
            return x.keys[i].key; 
        } 
        // no esta en el arbol
        if (x.leaf == true) {

            return -1; 
        }
        // ir al subnodo adecuado
        return search(x.childs[i],k);
    
    }
    
}





