
package tarea_arbol;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Scanner;

public class Tarea_Arbol {

    static Scanner lea = new Scanner(System.in);
    public static void main(String[] args) {
        Main();
    }
    public static void Main(){
        Btree arbol = new Btree(3);
        File archivo = new File("arbol.pb");
        boolean cargado = false;
        
        char opcion_MenuPrincipal = '0' ;
        while(opcion_MenuPrincipal != '6'){
            System.out.print("1.Cargar Arbol\n"
                    + "2.Guardar Arbol\n"
                    + "3.Imprimir Árbol\n"
                    + "4.Insertar Elemento\n"
                    + "5.Buscar Elemento\n"
                    + "6.Salir\n"
                    + "Ingrese una opcion:");
            opcion_MenuPrincipal = lea.next().charAt(0);
            switch (opcion_MenuPrincipal) {
                case '1':{
                    if(cargado){
                        System.out.println("Ya hay un Árbol en Ejecución");
                    }else{
                        try {
                            
                            Btree temp;
                            if (archivo.exists()) {
                                FileInputStream entrada = new FileInputStream(archivo);
                                ObjectInputStream objeto = new ObjectInputStream(entrada);
                                try {
                                    temp = (Btree) objeto.readObject();
                                        arbol = temp;
                                    
                                } catch (EOFException e) {
                                    //encontro el final del archivo
                                }
                                objeto.close();
                                entrada.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cargado = true;
                    }
                        
                }
                    
                    break;
                case '2':{
                    FileOutputStream fw = null;
                    ObjectOutputStream bw = null;
                    try {
                        fw = new FileOutputStream(archivo);
                        bw = new ObjectOutputStream(fw);
                        
                        bw.writeObject(arbol);
                        bw.flush();
                    } catch (Exception e) {
                    } finally {
                        try {
                            bw.close();
                            fw.close();
                        } catch (Exception e) {
                        }
                    }
                }
                    break;
                case '3':{
                    System.out.println("Impresion del Arbol: ");
                    arbol.PrintTree(arbol.root, "");
                }
                    break;
                case '4':
                    System.out.print("Ingrese numero que va insertar:");
                    int numero_insert = lea.nextInt();
                    arbol.insert(numero_insert);
                    break;
                case '5':{
                    System.out.println("Ingrese la Llave que desea Buscar:");
                    int numBuscar = lea.nextInt();
                    int buscado = arbol.search(arbol.root, numBuscar);
                    if(buscado == -1)
                    {
                        System.out.println("No se encontro");
                        
                    }
                    else
                    {
                        System.out.println("Se encontro");
                    }
                    
                }
                    break;
                case '6':
                    System.out.println("Adios");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingreso una opcion no valida");
                    break;
            }
        }
    }
}
