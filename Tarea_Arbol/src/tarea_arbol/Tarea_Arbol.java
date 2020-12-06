
package tarea_arbol;

import java.util.Scanner;

public class Tarea_Arbol {

    static Scanner lea = new Scanner(System.in);
    public static void main(String[] args) {
        Main();
    }
    public static void Main(){
        Btree arbol = null;
        
        char opcion_MenuPrincipal = '0' ;
        while(opcion_MenuPrincipal != 6){
            System.out.print("1.Cragar Arbol\n"
                    + "2.Guardar Arbol\n"
                    + "3.Imprimir Árbol\n"
                    + "4.Insertar Elemento\n"
                    + "5.Buscar Elemento\n"
                    + "6.Salir\n"
                    + "Ingrese una opcion:");
            opcion_MenuPrincipal = lea.next().charAt(0);
            switch (opcion_MenuPrincipal) {
                case '1':
                    
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':{
                    System.out.println("Ingrese la Llave que desea Buscar:");
                    int numBuscar = lea.nextInt();
                    int buscado = arbol.search(arbol.root, numBuscar);
                    System.out.println("Enocntrado en: " + buscado);
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
