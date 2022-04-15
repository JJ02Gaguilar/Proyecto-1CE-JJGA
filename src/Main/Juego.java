
package Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego {
    
    //atributos
    JFrame ventana;
    //Presentacion
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JLabel BotonJugar;
    //Variables juego
    JPanel panelJuego;
    JLabel fondoJuego;
    JLabel matriz[][];
    int mat[][];
    int matAux[][];
    String Jugador;
    Random aleatoria;
    JLabel nombreJugador;
    Timer tiempo;
    JLabel Cronometro;
    int Min;
    int Seg;
    int contador;
    Timer tiempoEspera;
    int contSegEsp;
    Timer tiempoEspera1;
    int ban;
    int ban1;
    //Elección de cartas
    int antnum;
    int antx;
    int anty;
    //
    int actualnum;
    int actualx;
    int actualy;
    //Constructor   
    public Juego(){
        
        //ventana
        ventana = new JFrame("Juego de Memoria");
        ventana.setSize(500,600);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        //Propiedades del Panel(Presentacion)
        panelPresentacion = new JPanel();
        panelPresentacion.setSize(ventana.getWidth(),ventana.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);
        
        //Fondo de inicio
        fondoPresentacion = new JLabel();
        fondoPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        fondoPresentacion.setLocation(0, 0);
        fondoPresentacion.setIcon(new ImageIcon("IMG/Fondo.png"));
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion,0);
          
        //Botón de jugar
        BotonJugar = new JLabel();
        BotonJugar.setSize(550,400);
        BotonJugar.setLocation(5, 300);
        BotonJugar.setIcon(new ImageIcon("IMG/Boton.png"));
        BotonJugar.setVisible(true);
        panelPresentacion.add(BotonJugar,0);
        
        //JPanel Juego
        panelJuego = new JPanel();
        panelJuego.setSize(ventana.getWidth(),ventana.getHeight());
        panelJuego.setLocation(0, 0);
        panelJuego.setLayout(null);
        panelJuego.setVisible(true);
        
        //Fondo de juego
        fondoJuego = new JLabel();
        fondoJuego.setSize(ventana.getWidth(), ventana.getHeight());
        fondoJuego.setLocation(0, 0);
        fondoJuego.setIcon(new ImageIcon("IMG/BG1.png"));
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego,0);
                
        //Nombre del jugador
        nombreJugador = new JLabel();
        nombreJugador.setSize(150,20);
        nombreJugador.setLocation(20, 40);
        nombreJugador.setForeground(Color.WHITE);
        nombreJugador.setVisible(true);
        panelJuego.add(nombreJugador,0);
        
        //Visualizacion del tiempo durante la partida
        Cronometro = new JLabel();
        Cronometro.setSize(150,20);
        Cronometro.setLocation(ventana.getWidth()-180, 40);
        Cronometro.setForeground(Color.WHITE);
        Cronometro.setVisible(true);
        panelJuego.add(Cronometro,0);
          
        //Matriz logica
        mat = new int [4][5];
        matAux = new int [4][5];
        aleatoria = new Random ();
        this.numerosAleatorios();
        
        //Matriz Cargado de Imagenes
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(110+(j*55),160+(i*66),55,66);
                matriz[i][j].setIcon(new ImageIcon("IMG/"+matAux[i][j]+".png"));
                matriz[i][j].setVisible(true);
                panelJuego.add(matriz[i][j],0);
            }    
        }
        
        //Opcion del cronometro
        Min = 0;
        Seg = 0;
        tiempo = new Timer(1000,new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                Seg++;
                if(Seg == 60){
                    Min++;
                    Seg = 0;
                }  
                Cronometro.setText("Tiempo: "+Min+":"+Seg);       
        }});
        
        
        //Tiempo de ordenamiento del volteo de las cartas
        contSegEsp = 0;
        tiempoEspera = new Timer(1000,new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                contSegEsp++;
        }});
        tiempoEspera.start();
        tiempoEspera.stop();
        contSegEsp = 0;
        ban = 0;
        ban1 = 0;
        //Evento voltear cartas
        
        contador = 0;
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){
                                    //System.out.println(k" "+1);
                                    
                                    if(matAux[k][l] == 0 && contador !=2){                                   
                                        matAux[k][l] = mat[k][l];
                                        matriz[k][l].setIcon(new ImageIcon("IMG/"+matAux[k][l]+".png"));
                                        contador++;
                                        actualnum = mat[k][l];
                                        actualx =k;
                                        actualy = l;
                                        if(contador == 1){
                                            antnum = mat[k][l];
                                            antx = k;
                                            anty = l;
                                        }
                                        
                                        tiempoEspera1 = new Timer(500,new ActionListener ()
                                        {
                                        public void actionPerformed(ActionEvent e)
                                        {
      
                                        
                                            if(contador == 2 && ban1 == 0){
                                                tiempoEspera.restart();
                                                ban1 = 1;   
                                            }    
                                            if( contador == 2 && contSegEsp == 2){
                                                tiempoEspera.stop();
                                                contSegEsp = 0;
                                                if( matAux[actualx][actualy] == matAux[antx][anty] ){
                                                    matAux[actualx][actualy] = -1;
                                                    matAux[antx][anty] = -1;
                                                    matriz[actualx][actualy].setIcon(new ImageIcon("IMG/"+matAux[actualx][actualy]+".png"));
                                                    matriz[antx][anty].setIcon(new ImageIcon("IMG/"+matAux[antx][anty]+".png"));
                                                    contador = 0;
                                                    //fin del juego y puntuación
                                                    int acum = 0;
                                                    for (int m = 0; m < 4; m++) {
                                                        for (int n = 0; n < 5; n++) {
                                                            if(matAux[m][n] == -1)
                                                                acum++;
                                                        }
                                                    }
                                                    if(acum == 20 ){
                                                        tiempo.stop();
                                                        JOptionPane.showMessageDialog(ventana, "FELICITACIONES HA GANADO LA PARTIDA-Tiempo:"+Min+":"+Seg);
                                                        for (int m = 0; m < 4; m++) {
                                                            for (int n = 0; n < 5; n++){
                                                                mat[m][n] = 0;
                                                                matAux[m][n] = 0;
                                                                matriz[m][n].setIcon(new ImageIcon("IMG/"+matAux[m][n]+".png"));
                                                            }    
                                                        }
                                                        numerosAleatorios();
                                                        Min = 0;
                                                        Seg = 0;
                                                        tiempo.restart();
                                                    }   
                                                }
                                            
                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n <5; n++) {
                                                        if(matAux[m][n] != 0 && matAux[m][n] != -1){
                                                            matAux[m][n] = 0;
                                                            matriz[m][n].setIcon(new ImageIcon("IMG/"+matAux[m][n]+".png"));
                                                            contador = 0;
                                                        }    
                                                    }        
                                                }
                                                tiempoEspera1.stop();
                                                ban1 = 0;
                                            }
                                        }});
                                        if(ban == 0 ){
                                            tiempoEspera1.start();
                                            ban = 1;
                                        }
                                        if(contador == 2)
                                                tiempoEspera1.restart();
                                    }
                                }
                            }    
                        }    
                    }
                });  
        
            }      
        }
        
        
        
        //
        BotonJugar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //System.out.println("presionó el boton");
                
                Jugador = JOptionPane.showInputDialog(ventana, "Nombre del Jugador", "Escriba aquí");
                while(Jugador == null || Jugador.compareTo("Escriba aquí")== 0 || Jugador.compareTo("")== 0){
                    Jugador = JOptionPane.showInputDialog(ventana,"Debe Ingresar el Usuario","Escriba aquí");
                }
                nombreJugador.setText("Jugador:" + Jugador);
                tiempo.start();
                panelPresentacion.setVisible(false);
                ventana.add(panelJuego);
                panelJuego.setVisible(true);
                    
            }});        
        ventana.add(panelPresentacion);      
        ventana.setVisible(true);
    }
    
    public void numerosAleatorios(){
        
        int acumulador = 0;
        for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 5; j++){
                mat[i][j] = 0;
                matAux[i][j] = 0;
            }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = aleatoria.nextInt(10)+1;
                do{
                    acumulador=0;
                
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            if(mat[i][j] == mat[k][l]) {
                               acumulador += 1;
                            }
                        }
                    }
                    if(acumulador == 3){
                        mat[i][j] = aleatoria.nextInt(10)+1;
                    }
                }while(acumulador == 3);
            }
        }       
    }    
}
