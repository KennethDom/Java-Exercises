//Kenneth Gerardo Aguirre Dominguez    19410251
//Lo hice con una interfaz, pero tuve complicaciones con las librerias, algunas no sabia usarlas asi que
//vi videos en YT pero no se si esten bien usadas

package Torres;
//Librerias para mostrar mensajes por medio de ventanas
import javax.swing.*;
//Librerias para las tablas
import javax.swing.table.*;

public class Ventana extends javax.swing.JFrame {

    int ContNumMov = 0;
    Pila Pila_TorreA;
    Pila Pila_TorreB;
    Pila Pila_TorreC;

    DefaultTableModel mt_TorreA, mt_TorreB, mt_TorreC;

    int Objetivo = 0;

    double numMinMov = 0;

    boolean Stop = false;


    public Ventana() {
        initComponents();

        mt_TorreA = (DefaultTableModel) Torre_A.getModel();
        mt_TorreA.setRowCount(10);

        mt_TorreB = (DefaultTableModel) Torre_B.getModel();
        mt_TorreB.setRowCount(10);

        mt_TorreC = (DefaultTableModel) Torre_C.getModel();
        mt_TorreC.setRowCount(10);

        //Sirve para centrar nuestros valores en las torres
        DefaultTableCellRenderer renderA = new DefaultTableCellRenderer();
        renderA.setHorizontalAlignment(SwingConstants.CENTER);
        Torre_A.getColumnModel().getColumn(0).setCellRenderer(renderA);

        DefaultTableCellRenderer renderB = new DefaultTableCellRenderer();
        renderB.setHorizontalAlignment(SwingConstants.CENTER);
        Torre_B.getColumnModel().getColumn(0).setCellRenderer(renderB);

        DefaultTableCellRenderer renderC = new DefaultTableCellRenderer();
        renderC.setHorizontalAlignment(SwingConstants.CENTER);
        Torre_C.getColumnModel().getColumn(0).setCellRenderer(renderC);
    }

    private void Limpiar(){
        //Reseteamos las variables y las inicializamos con 0
        ContNumMov = 0;
        numMinMov = 0;
        //Reseteaando el combo box del numero de discos
        //Le pasamos el valor del objetivo seleccionado (Objetivo)
        cb_numDiscos.setSelectedItem(String.valueOf(Objetivo));
        ((DefaultTableModel) Torre_B.getModel()).setRowCount(0);
        ((DefaultTableModel) Torre_C.getModel()).setRowCount(0);
        mt_TorreB.setRowCount(10);
        mt_TorreC.setRowCount(10);

    }
    
    private void Presentar_CantMovi(){
        ContNumMov++;
        l_movi.setText(String.valueOf(ContNumMov));
    }
    
    private void Reiniciar(){
        try{
          //Preguntaremos si el num min de movimientos esta vacio
          //Si esta vacio nos indica que todavia no a iniciado el juego
          //Si L_minMov es diferente a vacio podemos llamar a reinciar
            if(!l_minMov.getText().equals("")){
                //Llamamos al metodo limpiar para resetear variables
                Limpiar();
                //Llamamos al metodo iniciar
                Iniciar();
            }
            
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    private void Iniciar(){
        
        try {
            //Inicializamos las pilas que mencionamos antes
            Pila_TorreA = new Pila();
            Pila_TorreB = new Pila();
            Pila_TorreC = new Pila();

             //Objetivo sera la cantidad de discos que seleccionamos
            //Convertimos los items del combobox que son strings a enteros
            Objetivo = Integer.parseInt(cb_numDiscos.getSelectedItem().toString());

             //La formula para calcular el numero min de movimientos es 2 a la n -1
            //Para elevar a la n potencia debemos utilizar la clase math.pow
            numMinMov = Math.pow(2, Objetivo) - 1;

              //Como nos pasaran un double en NumMinMov entonces lo formatearemos 
            //Para hacer que nos pase su valor entero
            l_movi.setText(String.valueOf(ContNumMov));
            l_minMov.setText(String.valueOf(String.format("%.0f", numMinMov)));

            //Dependiendo el numero de discos que seleccionamos (Objetivo)
            //Se repetira ese numero de veces pero cada vez que se repita valdra -1
            for (int x = Objetivo; x >= 1; x--) {

                Nodo Plataforma = new Nodo();

                String Disco = "";

                for (int y = x; y > 0; y--) {
                    Disco += "#";
                }

                Plataforma.setDato(Disco);

                Pila_TorreA.Push(Plataforma);
                
            }
            
            Presentar_TorreA();
        } catch (Exception err) {
            JOptionPane.showConfirmDialog(this, err.getMessage());
        }
        
        
    }
    
    private void Presentar_TorreA(){
        
        try{
            //Borraremos todo lo que contiene la torre A casteando igualando a 0
           //Para que no aparezcan los discos anteriores
            ((DefaultTableModel) Torre_A.getModel()).setRowCount(0);

            //Como borramos las filas entonces necesitaremos agregarlas de nuevo
            mt_TorreA.setRowCount(10);

            //Iremos recorriendo este nodo
            Nodo k;

            //Con getContNodo sabremos la cantidad de discos que hay actualmente
           //De esta forma podremos restarle esa cantidad a 10
          //Se pondran los # de abajo para arriba
            int rowDisco = (10 - Pila_TorreA.getContNodo());  
            
            //Obteniendo el primer elemento para recorrer la pila
            if (Pila_TorreA.getContNodo() < 11) {

                
                for (k = Pila_TorreA.getCabeza(); k.getAbajo() != null; k = k.getAbajo()) {

                    //Obtieniendo el disco que actualmente lee la pila
                    String[] vectorNormal = {k.getDato()};

                    mt_TorreA.insertRow(rowDisco, vectorNormal);
                    rowDisco++;
                }

                if (k.getAbajo() == null) {
                    String[] vectorNormal = {k.getDato()};
                    mt_TorreA.insertRow(rowDisco, vectorNormal);
                }
            }

            Torre_A.setModel(mt_TorreA);
            mt_TorreA.setRowCount(10);
        }catch(Exception err){
        }
    }
    private void Presentar_TorreB(){
       try{
            //Borraremos todo lo que contiene la torre A casteando igualando a 0
           //Para que no aparezcan los discos anteriores
            ((DefaultTableModel) Torre_B.getModel()).setRowCount(0);  

            //Como borramos las filas entonces necesitaremos agregarlas de nuevo
            mt_TorreB.setRowCount(10);

             //Iremos recorriendo este nodo
            Nodo k;

              //Con getContNodo sabremos la cantidad de discos que hay actualmente
             //De esta forma podremos restarle esa cantidad a 10
            //Se pondran los # de abajo para arriba
            int rowDisco = (10 - Pila_TorreB.getContNodo()); 
            //Obteniendo el primer elemento para recorrer la pila
            if (Pila_TorreB.getContNodo() < 11) {

                
                for (k = Pila_TorreB.getCabeza(); k.getAbajo() != null; k = k.getAbajo()) {

                    //Obtieniendo el disco que actualmente lee la pila
                    String[] vectorNormal = {k.getDato()};

                    mt_TorreB.insertRow(rowDisco, vectorNormal);
                    rowDisco++;
                }

                if (k.getAbajo() == null) {
                    String[] vectorNormal = {k.getDato()};
                    mt_TorreB.insertRow(rowDisco, vectorNormal);
                }
            }

            Torre_B.setModel(mt_TorreB);
            mt_TorreB.setRowCount(10);
        }catch(Exception err){
        }
    }
    private void Presentar_TorreC(){
        try{
            //Borraremos todo lo que contiene la torre A casteando igualando a 0
           //Para que no aparezcan los discos anteriores
            ((DefaultTableModel) Torre_C.getModel()).setRowCount(0); 

            //Como borramos las filas entonces necesitaremos agregarlas de nuevo
            mt_TorreC.setRowCount(10);

            //Iremos recorriendo este nodo
            Nodo k;

              //Con getContNodo sabremos la cantidad de discos que hay actualmente
             //De esta forma podremos restarle esa cantidad a 10
             //Se pondran los # de abajo para arriba
            int rowDisco = (10 - Pila_TorreC.getContNodo()); 

            //Obteniendo el primer elemento para recorrer la pila
            if (Pila_TorreC.getContNodo() < 11) {

               
                for (k = Pila_TorreC.getCabeza(); k.getAbajo() != null; k = k.getAbajo()) {

                    //Obtieniendo el disco que actualmente lee la pila
                    String[] vectorNormal = {k.getDato()};

                    mt_TorreC.insertRow(rowDisco, vectorNormal);
                    rowDisco++;
                }

                if (k.getAbajo() == null) {
                    String[] vectorNormal = {k.getDato()};
                    mt_TorreC.insertRow(rowDisco, vectorNormal);
                }
            }

            Torre_C.setModel(mt_TorreC);
            mt_TorreC.setRowCount(10);
        }catch(Exception err){
        }
    }
    private void Moverde_AB(){
        try {
            //Sera la base
            if (Pila_TorreA.getContNodo() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(Pila_TorreA.Peek());

                //Sera el destino
                if (Pila_TorreB.getContNodo() > 0) {
                    if (plataforma.getDato().compareTo(Pila_TorreB.Peek()) > 0) {
                        return;
                    }
                }

                //La Base
                Pila_TorreA.Pop();
                //El Destino
                Pila_TorreB.Push(plataforma);

                Presentar_TorreA();
                Presentar_TorreB();
                Presentar_CantMovi();
            }

        } catch (Exception err) {
            System.out.println("Error al mover de A a B: "+err.toString());
        }
    }
    private void Moverde_AC(){
        try {

            if (Pila_TorreA.getContNodo() > 0) {

                Nodo plataforma = new Nodo();

                plataforma.setDato(Pila_TorreA.Peek());

                if (Pila_TorreC.getContNodo() > 0) {

                    if (plataforma.getDato().compareTo(Pila_TorreC.Peek()) > 0) {
                        return;
                    }
                }

                Pila_TorreA.Pop();
                Pila_TorreC.Push(plataforma);

                Presentar_TorreA();
                Presentar_TorreC();
                Presentar_CantMovi();

                //Mensajes para decir al usuario que terminaste el juego
                if (Pila_TorreC.getContNodo() == Objetivo && ContNumMov == numMinMov) {
                    JOptionPane.showMessageDialog(this, "Lo lograste en el minimo de movimientos\n\nPrueba con una dificultad mas alta", "Felicidades Amo", JOptionPane.WARNING_MESSAGE);
                } else if (Pila_TorreC.getContNodo() == Objetivo && ContNumMov != numMinMov) {
                    JOptionPane.showMessageDialog(this, "Lo resolviste\n\nIntenta hacerlo con el minimo de movimientos", "Felicidades Amo", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (Exception err) {
            System.out.println("Error al mover de A a C: "+err.toString());
        }
    }
    private void Moverde_BA(){
        try {

            if (Pila_TorreB.getContNodo() > 0) {

                Nodo plataforma = new Nodo();

                plataforma.setDato(Pila_TorreB.Peek());

                if (Pila_TorreA.getContNodo() > 0) {

                    if (plataforma.getDato().compareTo(Pila_TorreA.Peek()) > 0) {
                        return;
                    }
                }

                Pila_TorreB.Pop();
                Pila_TorreA.Push(plataforma);

                Presentar_TorreB();
                Presentar_TorreA();
                Presentar_CantMovi();
            }

        } catch (Exception err) {
            System.out.println("Error al mover de B a A: "+err.toString());
        }
    }
    private void Moverde_BC(){
        try {

            if (Pila_TorreB.getContNodo() > 0) {

                Nodo plataforma = new Nodo();

                plataforma.setDato(Pila_TorreB.Peek());

                if (Pila_TorreC.getContNodo() > 0) {

                    if (plataforma.getDato().compareTo(Pila_TorreC.Peek()) > 0) {
                        return;
                    }
                }

                Pila_TorreB.Pop();
                Pila_TorreC.Push(plataforma);

                Presentar_TorreB();
                Presentar_TorreC();
                Presentar_CantMovi();

                //Mensajes para decir al usuario que terminaste el juego
                if (Pila_TorreC.getContNodo() == Objetivo && ContNumMov == numMinMov) {
                    JOptionPane.showMessageDialog(this, "Lo lograste en el minimo de movimientos\n\nPrueba con una dificultad mas alta", "Felicidades Amo", JOptionPane.WARNING_MESSAGE);
                } else if (Pila_TorreC.getContNodo() == Objetivo && ContNumMov != numMinMov) {
                    JOptionPane.showMessageDialog(this, "Lo resolviste\n\nIntenta hacerlo con el minimo de movimientos", "Felicidades Amo", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (Exception err) {
            System.out.println("Error al mover de B a C: "+err.toString());
        }
    }
    private void Moverde_CA(){
        try {

            if (Pila_TorreC.getContNodo() > 0) {

                Nodo plataforma = new Nodo();

                plataforma.setDato(Pila_TorreC.Peek());

                if (Pila_TorreA.getContNodo() > 0) {

                    if (plataforma.getDato().compareTo(Pila_TorreA.Peek()) > 0) {
                        return;
                    }
                }

                Pila_TorreC.Pop();
                Pila_TorreA.Push(plataforma);

                Presentar_TorreC();
                Presentar_TorreA();
                Presentar_CantMovi();
            }

        } catch (Exception err) {
            System.out.println("Error al mover de C a A: "+err.toString());
        }
    }
    private void Moverde_CB(){
        try {
            //Sera la base
            if (Pila_TorreC.getContNodo() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(Pila_TorreC.Peek());

                //Sera el Destino
                if (Pila_TorreB.getContNodo() > 0) {
                    if (plataforma.getDato().compareTo(Pila_TorreB.Peek()) > 0) {
                        return;
                    }
                }

                //La Base
                Pila_TorreC.Pop();
                //El Destino
                Pila_TorreB.Push(plataforma);

                Presentar_TorreC();
                Presentar_TorreB();
                Presentar_CantMovi();
            }

        } catch (Exception err) {
            System.out.println("Error al mover de C a B: " + err.toString());
        }
    }
    
    Thread hilo = new Thread();
    
    private void MoverPlataforma(Pila origen,Pila destino){
        try{
            if (Stop == false) {
                Nodo plataforma = new Nodo();

                plataforma.setDato(origen.Peek());

                origen.Pop();

                destino.Push(plataforma);
                
                Presentar_TorreA();
                Presentar_TorreB();
                Presentar_TorreC();
                Presentar_CantMovi();

                //Deteniendo por unos segundos
                Thread.sleep(1000);
            }
        }catch(Exception err){
            
        }
    }
  
    private void ResolverHanoiRecursivo(int n,Pila origen,Pila auxiliar,Pila destino){
        
        try {
            if (n == 1) {
                
                MoverPlataforma(origen, destino);
             
            } else {
               
                ResolverHanoiRecursivo(n - 1, origen, destino, auxiliar);

                MoverPlataforma(origen, destino);

                ResolverHanoiRecursivo(n - 1, auxiliar, origen, destino);

            }
        } catch (Exception err) {

        }
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Torre_B = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Torre_C = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Torre_A = new javax.swing.JTable();
        btn_ab = new javax.swing.JButton();
        btn_ac = new javax.swing.JButton();
        btn_ba = new javax.swing.JButton();
        btn_bc = new javax.swing.JButton();
        btn_ca = new javax.swing.JButton();
        btn_cb = new javax.swing.JButton();
        cb_numDiscos = new javax.swing.JComboBox<>();
        l_movi = new javax.swing.JLabel();
        l_minMov = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bt_iniciar = new javax.swing.JButton();
        bt_reiniciar = new javax.swing.JButton();
        bt_resolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Torre_B.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre B"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre_B.setFocusable(false);
        Torre_B.setRowSelectionAllowed(false);
        Torre_B.setShowHorizontalLines(false);
        Torre_B.setShowVerticalLines(false);
        jScrollPane1.setViewportView(Torre_B);
        if (Torre_B.getColumnModel().getColumnCount() > 0) {
            Torre_B.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre_C.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre C"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre_C.setFocusable(false);
        Torre_C.setRowSelectionAllowed(false);
        Torre_C.setShowHorizontalLines(false);
        Torre_C.setShowVerticalLines(false);
        jScrollPane2.setViewportView(Torre_C);
        if (Torre_C.getColumnModel().getColumnCount() > 0) {
            Torre_C.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre_A.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre A"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre_A.setAutoscrolls(false);
        Torre_A.setFocusable(false);
        Torre_A.setRowSelectionAllowed(false);
        Torre_A.setShowHorizontalLines(false);
        Torre_A.setShowVerticalLines(false);
        jScrollPane3.setViewportView(Torre_A);
        if (Torre_A.getColumnModel().getColumnCount() > 0) {
            Torre_A.getColumnModel().getColumn(0).setResizable(false);
        }

        btn_ab.setBackground(new java.awt.Color(0, 51, 51));
        btn_ab.setForeground(new java.awt.Color(255, 255, 255));
        btn_ab.setText("B");
        btn_ab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abActionPerformed(evt);
            }
        });

        btn_ac.setBackground(new java.awt.Color(0, 51, 51));
        btn_ac.setForeground(new java.awt.Color(255, 255, 255));
        btn_ac.setText("C");
        btn_ac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acActionPerformed(evt);
            }
        });

        btn_ba.setBackground(new java.awt.Color(0, 51, 51));
        btn_ba.setForeground(new java.awt.Color(255, 255, 255));
        btn_ba.setText("A");
        btn_ba.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_ba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baActionPerformed(evt);
            }
        });

        btn_bc.setBackground(new java.awt.Color(0, 51, 51));
        btn_bc.setForeground(new java.awt.Color(255, 255, 255));
        btn_bc.setText("C");
        btn_bc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bcActionPerformed(evt);
            }
        });

        btn_ca.setBackground(new java.awt.Color(0, 51, 51));
        btn_ca.setForeground(new java.awt.Color(255, 255, 255));
        btn_ca.setText("A");
        btn_ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caActionPerformed(evt);
            }
        });

        btn_cb.setBackground(new java.awt.Color(0, 51, 51));
        btn_cb.setForeground(new java.awt.Color(255, 255, 255));
        btn_cb.setText("B");
        btn_cb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cbActionPerformed(evt);
            }
        });

        cb_numDiscos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9", "10" }));

        l_movi.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        l_movi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_movi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        l_minMov.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        l_minMov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_minMov.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Discos");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Movimientos Minimos");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Movimientos Realizados");

        bt_iniciar.setBackground(new java.awt.Color(0, 0, 0));
        bt_iniciar.setForeground(new java.awt.Color(255, 255, 255));
        bt_iniciar.setText("Iniciar");
        bt_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_iniciarActionPerformed(evt);
            }
        });

        bt_reiniciar.setBackground(new java.awt.Color(0, 0, 0));
        bt_reiniciar.setForeground(new java.awt.Color(255, 255, 255));
        bt_reiniciar.setText("Reiniciar");
        bt_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_reiniciarActionPerformed(evt);
            }
        });

        bt_resolver.setBackground(new java.awt.Color(0, 0, 0));
        bt_resolver.setForeground(new java.awt.Color(255, 255, 255));
        bt_resolver.setText("Resolver");
        bt_resolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_minMov, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_ab, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_ac))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_ba)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_bc)
                                        .addGap(27, 27, 27))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_numDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_movi, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(bt_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_resolver, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 33, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(btn_ca)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cb))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(125, 125, 125))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(l_movi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cb_numDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bt_iniciar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_resolver)
                        .addComponent(bt_reiniciar)))
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ab)
                                .addComponent(btn_ac))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ba)
                                .addComponent(btn_bc))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ca)
                            .addComponent(btn_cb))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_minMov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_iniciarActionPerformed
        // TODO add your handling code here:
      Iniciar();
    }//GEN-LAST:event_bt_iniciarActionPerformed

    private void bt_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reiniciarActionPerformed
        // TODO add your handling code here:
        Reiniciar();
    }//GEN-LAST:event_bt_reiniciarActionPerformed

    private void bt_resolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resolverActionPerformed
        // TODO add your handling code here:
        if(!l_minMov.getText().equals("") && Pila_TorreC.getContNodo()!=Objetivo){
            
            Reiniciar();
            Stop = false;
            
            ResolverHanoiRecursivo(Objetivo,Pila_TorreA,Pila_TorreB,Pila_TorreC);
            
        }
    }//GEN-LAST:event_bt_resolverActionPerformed

    private void btn_abActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abActionPerformed
        // TODO add your handling code here:
        Moverde_AB();
    }//GEN-LAST:event_btn_abActionPerformed

    private void btn_acActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acActionPerformed
        // TODO add your handling code here:
        Moverde_AC();
    }//GEN-LAST:event_btn_acActionPerformed

    private void btn_baActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baActionPerformed
        // TODO add your handling code here:
        Moverde_BA();
    }//GEN-LAST:event_btn_baActionPerformed

    private void btn_bcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bcActionPerformed
        // TODO add your handling code here:
        Moverde_BC();
    }//GEN-LAST:event_btn_bcActionPerformed

    private void btn_caActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caActionPerformed
        // TODO add your handling code here:
        Moverde_CA();
    }//GEN-LAST:event_btn_caActionPerformed

    private void btn_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cbActionPerformed
        // TODO add your handling code here:
        Moverde_CB();
    }//GEN-LAST:event_btn_cbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Torre_A;
    private javax.swing.JTable Torre_B;
    private javax.swing.JTable Torre_C;
    private javax.swing.JButton bt_iniciar;
    private javax.swing.JButton bt_reiniciar;
    private javax.swing.JButton bt_resolver;
    private javax.swing.JButton btn_ab;
    private javax.swing.JButton btn_ac;
    private javax.swing.JButton btn_ba;
    private javax.swing.JButton btn_bc;
    private javax.swing.JButton btn_ca;
    private javax.swing.JButton btn_cb;
    private javax.swing.JComboBox<String> cb_numDiscos;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel l_minMov;
    private javax.swing.JLabel l_movi;
    // End of variables declaration//GEN-END:variables
}
