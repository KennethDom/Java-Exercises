//Kenneth Gerardo Aguirre Dominguez           19410251


package quicksort;

public class QuickSort {
    
    public void ordenarQickSort(int[] array) {
        array = regreso(array);
    }

    public int[] regreso(int numeros[]) {
        return CodigoQuickSort(numeros, 0, numeros.length - 1);
    }

    public int[] CodigoQuickSort(int numeros[], int izquierda, int derecha) {
        
        if (izquierda >= derecha) {           //Comparamos si el puntero de la izquierda es mayor o igual al de la derecha
            return numeros; 
        }
        int izq = izquierda, der = derecha;   //Asignamos a los punteros a otras variables independientes
        if (izquierda != derecha) {           //Comparando si son diferentes
            int pivote;                       //Declaramos la variable pivote
            int auxiliar;                     //Declaramos la variable auxiliar
            pivote = izquierda;               //Igualamos el pivote al numero de la izquierda
            
            //Ciclo while que se ejecutara los movimientos mientras que izquierda sea diferente a derecha
            while (izquierda != derecha) {
                while (numeros[derecha] >= numeros[pivote] && izquierda < derecha) {
                    derecha--;
                }
                while (numeros[izquierda] < numeros[pivote] && izquierda < derecha) {
                    izquierda++;
                }
                
                //Cambio cuando dercha sea diferente a izquierda
                if (derecha != izquierda) {
                    auxiliar = numeros[derecha];
                    numeros[derecha] = numeros[izquierda];
                    numeros[izquierda] = auxiliar;                //Intercambiamos lado derecho por el lado izquierdo
                }
            }
            if (izquierda == derecha) {
                CodigoQuickSort(numeros, izq, izquierda - 1);   //Volvemos a llamar al mismo metodo pero con punteros diferentes
                CodigoQuickSort(numeros, izquierda + 1, der);
            }
        } else {
            return numeros;
        }
        return numeros;

    }

}
