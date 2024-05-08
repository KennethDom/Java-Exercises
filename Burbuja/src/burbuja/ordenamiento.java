//Kenneth Gerardo Aguirre Dominguez          19410251

package burbuja;

import javax.swing.JOptionPane;

public class ordenamiento extends Main {

    int arreglo[];
    int elementos;
    int numeros;
    int auxiliar;
    public void ordenar() {

        //Pregunta cuantos numeros se van a ordenar y le asignamos esos elementos al arreglo
        elementos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos elementos quiere ordenar amo "));
        arreglo = new int[elementos];
  
        //Con un for pedimos los elementos al usuario
        for (int i = 0; i < elementos; i++) {
            numeros = Integer.parseInt(JOptionPane.showInputDialog(null, "Dame un numero"));
            arreglo[i] = numeros;
        }
        
        //Mostramos los valores desordenados
        System.out.println("Aqui estan tus elementos desordenados");
        for (int i = 0; i < elementos; i++) {
            System.out.print(arreglo[i] + " , ");
        }
        System.out.println("\n");
        // for sirve para saber cuantas vueltas tiene que dar 
        for (int i = 0; i <(elementos - 1); i++) {
            // for sirve para ir intercambiando los numeros
            for (int j = 0; j < (elementos - 1); j++) {
                if (arreglo[j] > arreglo[j + 1]) {       //Si el numero actual (arreglo[j]) es mayor al num siguiente (arreglo[j+1])
                    auxiliar = arreglo[j];              // Con auxiliar podemos intercambiar los valores de los elementos
                    arreglo[j] = arreglo[j + 1];       // Numero actual agregamos valor del numero siguiente
                    arreglo[j + 1] = auxiliar;        // Numero siguiente sera igual al numero actual dentro de auxiliar
                }
            }
        }
        
        //Se muestra a pantalla los valores ordenados 
        System.out.println("\nAqui estan tus elementos ordenados");
        for (int i = 0; i < elementos; i++) {
            System.out.print(arreglo[i] + " , ");
        }
    }

}
