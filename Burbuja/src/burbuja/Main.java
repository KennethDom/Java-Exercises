//Kenneth Gerardo Aguirre Dominguez          19410251


package burbuja;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        //Menu con dos opciones
        
        int opcion = 0;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ordenamiento Burbuja\n"
                        + "1. Empezar ordenamiento\n" + "2. Salir"));
                switch (opcion) {
                    case 1:
                        ordenamiento ordenamientos1 = new ordenamiento();
                        ordenamientos1.ordenar();
                        break;
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

        } while (opcion != 6);
    }
}
