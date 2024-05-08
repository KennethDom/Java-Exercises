//Kenneth Gerardo Aguirre Dominguez     19410251

package Torres;

public class Pila {
    
    private int ContNodo = 0;
    private Nodo Cabeza;

    public int getContNodo() {
        return ContNodo;
    }

    public Nodo getCabeza() {
        return Cabeza;
    }

    public void Push(Nodo N) {
        ContNodo++;
        if (Cabeza == null) {
            Cabeza = N;
        } else {
            N.setAbajo(Cabeza);
            Cabeza.setArriba(N);
            Cabeza = N;
        }
    }

    public void Pop() {
        if (ContNodo > 0) {
            ContNodo--;
            Cabeza = Cabeza.getAbajo();
        }
    }

    public String Peek() {
        return Cabeza.getDato();
    }

}
