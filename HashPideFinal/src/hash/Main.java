//Kenneth Gerardo Aguirre Dominguez             19410251
package hash;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int resp;
        Hash tam = new Hash(10);                      //El tamaño de nuestro arreglo sera 10
        do {
            resp = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Tabla Hash\n"
                    + "1.-iniciar  \n2.-Buscar \n3.-Salir"));
            switch (resp) {
                case 1:

                    Scanner scanner = new Scanner(System.in);
                    String[] elementos = new String[10];
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Dame el numero de telefono");
                        elementos[i] = scanner.next();
                    }
                    tam.funcionHash(elementos, tam.arreglo);
                    tam.mostrar();
                    break;
               
                
                
                
                
                case 2:
                    tam.buscar("");
                    break;

                case 3:
                    System.exit(0);
                    break;

                case 4:

            }

        } while (resp != 4);
    }
}
