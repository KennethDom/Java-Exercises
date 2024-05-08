//Kenneth Gerardo Aguirre Dominguez 19410251


package hash;

import java.util.Arrays;

public class Hash {

    //Atributos que utilizaremos
    String[] arreglo;
    int dimension, cont;

    //Creando nuestro constructor
    public Hash(int tam) {
        dimension = tam;
        arreglo = new String[tam];
        Arrays.fill(arreglo, "-1");     //El -1 nos indica que el arreglo esta vacio
    }

    //Metodo para asignar claves
    public void funcionHash(String[] cadenaArreglo, String[] arreglo) {     //Recibe parametros cadenaArreglo y srreglo    
        for (int i = 0; i < cadenaArreglo.length; i++) {
            String elemento = cadenaArreglo[i];
            int indice = Integer.parseInt(elemento) % 4; //<-----Sacarmeos el modulo del elemento y como su tamaño es 5 entonces 5-1=4
            System.out.println("El indice " + indice + " sera el elemento " + elemento); //Imprimimos el elemento en su respectivo indice

            //Si ocurriera una colision entonces
            while (arreglo[indice] != "-1") {    //Mientras arreglo en su posicion indice sea diferente de -1 entonces
                indice++;                   //Incrementamos el indice
                System.out.println("Parece que ocurrio una colision en el indice " + (indice - 1)
                        + " asi que se cambiara al indice " + indice);
                indice %= dimension;         //Volviendo a sacar el modulo
            }
            arreglo[indice] = elemento;
        }
    }

    public void mostrar() {
        int incremento = 0, i, j;
        for (i = 0; i < 1; i++) {
            incremento += 4;
            for (j = 0; j < 71; j++) {
                System.out.print("-");
            }
            System.out.println();

            for (j = incremento - 4; j < incremento; j++) {
                System.out.format("| %3s" + "  ", j);
            }
            System.out.println("|");
            for (int n = 0; n < 71; n++) {
                System.out.print("-");
            }

            System.out.println();

            for (j = incremento - 4; j < incremento; j++) {
                if (arreglo[j].equals("-1")) {
                    System.out.print("|   ");
                } else {
                    System.out.print(String.format("| %3s " + " ", arreglo[j]));
                }

            }
            System.out.println("|");
            for (j = 0; j < 71; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
    
    
     public String buscar(String elemento) {
        int indiceArreglo = Integer.parseInt(elemento) % 4;
        int contador = 0;
        while (arreglo[indiceArreglo] != "-1") {
            if (arreglo[indiceArreglo] == elemento) {
                System.out.println("Tu valor " + elemento + " Esta en el indice " + indiceArreglo);
                return arreglo[indiceArreglo];
            }

            indiceArreglo++;
            indiceArreglo %= dimension;
            contador++;
            if (contador > 4) {
                break;
            }
        }
        return null;
    }
    
    
}
