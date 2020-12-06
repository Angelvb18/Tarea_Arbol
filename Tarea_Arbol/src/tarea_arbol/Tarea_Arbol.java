
package tarea_arbol;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Tarea_Arbol {

    static Scanner lea = new Scanner(System.in);
    public static void main(String[] args) {
        Main();
    }
    public static void Main(){
        Btree arbol = new Btree(5);
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
                case '3':
                    break;
                case '4':
                    System.out.println("Ingrese numero que va ");
                    break;
                case '5':{
                    System.out.println("Ingrese la Llave que desea Buscar:");
                    int numBuscar = lea.nextInt();
                    int buscado = arbol.search(arbol.root, numBuscar);
                    System.out.println("Encontrado en: " + buscado);
                }
                    break;
                case '6':
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Ingreso una opcion no valida");
                    break;
            }
        }
    }
}
