//Kenneth Gerardo Aguirre Dominguez               19410251
//El menu y para mostrar los datos lo deje igual que el ordenamiento anterior, lo unico que cambio fue la manera de ordenarlos
package incersion;

import javax.swing.JOptionPane;

public class Incersion {

    int arreglo[];
    int elementos;
    int numeros;
    int auxiliar;
    int posicion;

    public void ordenar() {

        //Pregunta cuantos numeros se van a ordenar y le asignamos esos elementos al arreglo
        elementos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos elementos quieres ordenar"));
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

        // Incersion
        for (int i = 0; i < elementos; i++) {
            posicion = i;
            auxiliar = arreglo[i];

            while (posicion > 0 && (arreglo[posicion - 1] > auxiliar)) {
                arreglo[posicion] = arreglo[posicion - 1];
                posicion--;
            }
            arreglo[posicion] = auxiliar;
        }

        //Se muestra a pantalla los valores ordenados 
        System.out.println("\nAqui estan tus elementos ordenados");
        for (int i = 0; i < elementos; i++) {
            System.out.print(arreglo[i] + " , ");
        }
        System.out.println("\n");
    }

}
