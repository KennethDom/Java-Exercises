//Kenneth Gerardo Aguirre Dominguez           19410251

package quicksort;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        int arreglo[];
        int elementos;
        int numeros;

        
        //Menu con dos opciones
        int opcion = 0;
        
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "QuickSort\n"
                        + "1. Empezar ordenamiento\n" + "2. Salir"));
                switch (opcion) {
                    case 1:
                        elementos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos elementos quieres ordenar"));
                        arreglo = new int[elementos];
                        for (int i = 0; i < elementos; i++) {
                            numeros = Integer.parseInt(JOptionPane.showInputDialog(null, "Dame un numero"));
                            arreglo[i] = numeros;
                        }

                        System.out.println("Elementos desordenados: ");
                        for (int i = 0; i < arreglo.length; i++) {
                            System.out.print(arreglo[i] + " , ");
                        }

                        QuickSort ordenar = new QuickSort();
                        ordenar.ordenarQickSort(arreglo);

                        System.out.println("\nElmentos ordenados");
                        for (int i = 0; i < arreglo.length; i++) {
                            System.out.print(arreglo[i] + " , ");
                        }
                    case 2:
                        JOptionPane.showMessageDialog(null, "Aplicacion finalizada", "Fin", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Parece que pusiste un numero incorrecto",
                                "Precaucion", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Error " + n.getMessage());
            }
        } 
    }
