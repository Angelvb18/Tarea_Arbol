package tarea_arbol;

import java.io.Serializable;
import java.util.Queue;


public class Btree implements Serializable {
    Bnode root; // Pointer to root node 
    int t = 3;  // Minimum degree

    // Constructor (Initializes tree as empty) 
    Btree() {
        root = null;

    }

    // function to traverse the tree 
    void traverse() {
        if (root != null) {
            root.traverse();
            System.out.println("");
        }
    }

    void PrintLevels() {
        try{
            if (root != null) {
                for (int i = 0; i < root.n; i++) {
                    if (root.key[i] != -1) {
                        System.out.print(root.key[i] + "  ");
                    }
                }

                System.out.println("");
                if (root.hijos != null) {
                    for (int i = 0; i < root.hijos.length; i++) {
                        if (root.hijos[i] != null) {
                            for (int j = 0; j < root.hijos[i].n; j++) {
                                if (root.hijos[i].key[j] != -1) {
                                    System.out.print(root.hijos[i].key[j]+"-");
                                }
                            }
                            System.out.print("|");
                        }

                    }
                    System.out.println("");
                    for (int i = 0; i < root.hijos.length; i++) {
                        if (root.hijos[i] != null) {
                            for (int j = 0; j < root.hijos[i].n; j++) {
                                if (root.hijos[i].hijos[j] != null) {
                                    for (int k = 0; k < root.hijos[i].hijos[j].n + 1; k++) {
                                        //System.out.println("j:"+j+" K:"+k);
                                        if (root.hijos[i].hijos[j].key[k] != 0) {
                                            System.out.print(root.hijos[i].hijos[j].key[k]+"-");

                                        }

                                    }
                                    System.out.print("|");
                                }

                            }
                            System.out.print("|");
                        }

                    }

                }
            }
       
            
            }catch(Exception x){
                
            }

    }

    // function to search a key in this tree 
    Bnode search(int k) {
        //return (root == NULL)? NULL : root->search(k); c++
        //return (root == null) ? null : root.search(k);
        if (root == null) {
            return null;
        } else {
            return root.search(k);
        }
    }

    void insert(int r) {
        // If tree is empty 
        if (root == null) {
            // Allocate memory for root 
            root = new Bnode(t, true);
            // root.keys[0] = k;  // Insert key 
            root.key[0] = r;
            //root->n = 1;  // Update number of keys in root 
            root.n = 1;
        } else // If tree is not empty 
        {
            // If root is full, then tree grows in height 
            // if (root->n == 2*t-1) 
            if (root.getN() == 2 * t - 1) {
                // Allocate memory for new root 
                // BTreeNode *s = new BTreeNode(t, false); 
                Bnode s = new Bnode(t, false);

                // Make old root as child of new root 
                //s->C[0] = root; 
                s.hijos[0] = root;

                // Split the old root and move 1 key to the new root 
                //s->splitChild(0, root); 
                s.splitChild(0, root);

                // New root has two children now.  Decide which of the 
                // two children is going to have new key 
                int i = 0;
                //if (s->keys[0] < k) 
                if ( ((Integer) s.getKey(0) ) < r) {
                    i++;
                }
                //s->C[i]->insertNonFull(k); 
                s.getHijos()[i].insertNonFull(r);
                // Change root 
                root = s;
            } else // If root is not full, call insertNonFull for root 
            //root->insertNonFull(k); 
            {
                root.insertNonFull(r);
            }
        }
    }

    void remove(int k) {
        if (root == null) {
            System.out.println("El Arbol esta vacio");

            return;
        }

        // Call the remove function for root 
        root.remove(k);

        // If the root node has 0 keys, make its first child as the new root 
        //  if it has a child, otherwise set root as NULL 
        if (root.n == 0) {
            Bnode tmp = root;
            if (root.isLeaf()) {
                root = null;
            } else {
                root = root.hijos[0];
            }

            // Free the old root 
            //delete tmp;
            tmp = null;
            System.out.println("entro aca");
        }
        System.out.println("salio de ahi3");
    }
    
}
















