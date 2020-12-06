package tarea_arbol;

import java.io.Serializable;


public class Btree implements Serializable {
    protected Bnode root;
    protected int t;
    
    public Btree(int t) { // se inicializa el arbol-b vacio
        root=null;
        this.t = t;
        
    }
    
    public void delete_key(Bnode x,int k){
        //la funcion comienza con la busqueda de la posicion de la llave
        int pos=encontrar_llave(x, k);
        
        if(pos<x.n&&x.keys[pos].key==k){//la llave esta en bnode x
            if(x.leaf){
                remover_de_hoja(x,pos);
            }else{
                remover_de_interno(x,pos);
            }
        }else{ //la llave no esta en x
            
            if(x.leaf){// nodo hoja sin encontrar llave = la llave no existe
                System.out.println("llave inexistente");
                return;
            }
            
            //el nodo es interno y no hemos encontrado la llave, hay que bajar al sub-arbol
            
            //down to last child of x
            
            boolean test=false;
            
            //last child 
            if(pos==x.n){
                test=true;
            }
            
            //less than accepted number of keys
            if(x.childs[pos].n<x.t){
                llenar(x.childs[pos],pos);
            }
            
            
            if(test&&pos>x.n){
                delete_key(x.childs[pos-1], k);
            }else{
                delete_key(x.childs[pos], k);
            }
            
        }
        
    }
    
    public void llenar(Bnode x,int pos){
        if (pos!=0&&x.childs[pos-1].n>=x.t) {
            prestar_anterior(x,pos);
        }else if(pos!=x.n&&x.childs[pos+1].n>=t){
            prestar_next(x,pos);
        }else{
            if(pos!=x.n){
                merge(x,pos);
            }else{
                merge(x,pos+1);
            }
            
        }
    }
    
    //get predecessor
    public int getPre(Bnode x,int pos){
        Bnode temp=x.childs[pos];
        while(!temp.leaf){
            temp=temp.childs[temp.n];
        }
        
        //last key of leaf
        return temp.keys[temp.n-1].key;
    }
    
    //get succesor
    public int getSu(Bnode x,int pos){
        Bnode temp=x.childs[pos+1];
        while(!temp.leaf){
            temp=temp.childs[0];
        }
        
        //first key of leaf
        return temp.keys[0].key;
    }
    
    public void prestar_anterior(Bnode x,int pos){
        Bnode child=x.childs[pos]; 
	Bnode sib=x.childs[pos-1]; 

	for (int i=child.n-1; i>=0; i--){
            child.keys[i+1] = child.keys[i]; 
        } 
	
	if (!child.leaf){ 
            for(int i=child.n; i>=0; --i){
                child.childs[i+1]=child.childs[i];
            }
	} 
	
	child.keys[0] = x.keys[pos-1]; 
	
	if(!child.leaf){
          child.childs[0] = sib.childs[sib.n];   
        } 
            
	x.keys[pos-1] = sib.keys[sib.n-1]; 

	child.n += 1; 
	sib.n -= 1;
    }
    
    public void prestar_next(Bnode x,int pos){
        Bnode child=x.childs[pos]; 
	Bnode sib=x.childs[pos-1]; 

	
        child.keys[child.n] = x.keys[pos];
         
	
	if (!child.leaf){ 
            child.childs[(child.n)+1]=sib.childs[0];
	} 
	
	x.keys[pos]=sib.keys[0];
	
	
        for (int i = 1; i < sib.n; i++) {
            sib.keys[i-1]=sib.keys[i];
        }
            
	
        if (!sib.leaf) {
            for (int i = 1; i <= sib.n; i++) {
                sib.childs[i-1]=sib.childs[i];
            }
        }

	child.n += 1; 
	sib.n -= 1;
    }
    
    public void remover_de_interno(Bnode x, int pos){
        int k=x.keys[pos].key;//key
        
        if(x.childs[pos].n>=t){
            int pre=getPre(x,pos);
            x.keys[pos].key=pre;
            delete_key(x.childs[pos], pre);
            
        }else if(x.childs[pos+1].n>=t) {
            int suc=getSu(x, pos);
            x.keys[pos].key=suc;
            delete_key(x.childs[pos+1], suc);
        }else{
            merge(x,pos);
            delete_key(x.childs[pos], k);
        }
    }
    
    public void merge(Bnode x,int pos){
        Bnode child=x.childs[pos];
        Bnode sib=x.childs[pos+1];
        
        child.keys[x.t-1]=x.keys[pos];
        
        for (int i = 0; i < sib.n; i++) {
            child.keys[i+t]=sib.keys[i];
        }
        
        if(!child.leaf){
            for (int i = 0; i < sib.n; i++) {
                child.childs[i+t]=sib.childs[i];
            }
        }
        
        for (int i = pos+1; i < x.n; i++) {
            x.keys[i-1]=x.keys[i];
        }
        
        for (int i = pos+2; i <=x.n; i++) {
            x.childs[i-1]=x.childs[i];
        }
        
        child.n=child.n+sib.n+1;
        
        x.n--;
        
        sib=null;
    }
    
    public void remover_de_hoja(Bnode x,int pos){
        for (int i = pos+1; i < x.n; i++) {
            x.keys[i-1]=x.keys[i];
        }
        
        //one less key
        x.n=x.n-1;
    }
    
    public int encontrar_llave(Bnode x,int k){//retorna la posicion de la llave o la posicion de un valor mas grande o la posicion final si no se cumplen los 2 primeros casos
        int pos=0;
        while(pos<x.n&&x.keys[pos].key<k){
            pos++;
        }
        return pos;
        
    }
    
    public void insert(int key,int RRN){
        if (root==null) {
            root=new Bnode(t,true);
            //root.getKeys()[root.getN()]=key;
            root.keys[0]=new KRRN(key, RRN);
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
            insertNonFull(s.childs[i], key,RRN);
        }
        else{
            //insert in root node
            insertNonFull(root,key,RRN);
        }
    }
    
    public void insertNonFull(Bnode x,int key,int RRN){
        int i=x.n-1;//array last element
        if (x.leaf==true) {
            while (i>=0&&key<x.keys[i].key) { //se corren los elementos mayores a la derecha
                x.keys[i+1]=x.keys[i];
                i--;
            }
            x.keys[i+1]=new KRRN(key, RRN);//se inserta el elemento en la posicion correcta
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
            
            insertNonFull(x.childs[i], key,RRN);
            
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
            return x.keys[i].RRN; 
            
        } 
        // no esta en el arbol
        if (x.leaf == true) {

            return -1; 
        }
        // ir al subnodo adecuado
        return search(x.childs[i],k);
    
    }
    
}



