package GUI;

import Gauss.Gauss;
import Graffene.Graeffe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana extends JFrame implements ActionListener {

    private JLabel titulo, descripcion, resultado;
    private JButton generarC, calcular;
    private JTextField grado;
    private Expresion panel;

    public Ventana() {
        titulo = new JLabel("Ecuaciones de Recurrencia");
        descripcion = new JLabel("Inserte el grado del polinomio:");
        grado = new JTextField();
        generarC = new JButton("Generar campos");
        calcular = new JButton("Calcular");
        resultado = new JLabel();
        
    }

    public void initComponents() {
        titulo.setSize(new Dimension(800, 40));
        titulo.setLocation(250, 20);
        titulo.setFont(new Font("Agency FB", Font.BOLD, 40));
        add(titulo);

        descripcion.setSize(new Dimension(800, 40));
        descripcion.setLocation(10, 80);
        descripcion.setFont(new Font("Agency FB", Font.BOLD, 25));
        add(descripcion);

        grado.setSize(new Dimension(90, 30));
        grado.setLocation(15, 120);
        add(grado);

        generarC.setSize(150, 30);
        generarC.setLocation(450, 120);
        generarC.addActionListener(this);
        generarC.setForeground(Color.WHITE);
        generarC.setBackground(Color.BLACK);
        add(generarC);

        calcular.setSize(150, 30);
        calcular.setLocation(610, 120);
        calcular.addActionListener(this);
        calcular.setForeground(Color.WHITE);
        calcular.setBackground(Color.BLACK);
        add(calcular);
        
        resultado.setLocation(120, 300);
        resultado.setSize(new Dimension(800,40));
        resultado.setFont(new Font("Agency FB", Font.PLAIN, 23));
        add(resultado);
    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Recurrencia");
        setSize(new Dimension(800, 500));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == generarC) {

            try {
                
                int n = Integer.parseInt(grado.getText());
                panel = new Expresion(n);
                panel.removeInputElements();
                panel.initInputElements();
                //panel.setOpaque(true);
                //panel.setBackground(Color.red);
                panel.setSize(new Dimension(700, 290));
                panel.setLocation(10, 160);
                //panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                add(panel);  
                repaint();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == calcular) {

            try {
                //Se obtienen las raices usando graffene
                Graeffe g = new Graeffe(panel.getCoeficientes());
                g.mostrarRaices();
                double[] raices = g.getRaicesReales();
                for(int i = 0;i<raices.length;i++){
                    System.out.println(raices[i]);
                }
                Gauss ga = new Gauss(raices,panel.getFn(),panel.getNvalues());
                double[] soluciones = ga.obtenerMatriz();
                String valores = "";
                for(int i = 0;i<soluciones.length;i++){
                    valores += "("+soluciones[i]+") ("+raices[i]+")^n + ";
                                    }
                
                
                // Impresión del resultado:
                
                
                
                System.out.println("la ecuacion completa es: fn = "+valores);
                
                resultado.setText("La ecuacion completa es: fn = "+valores);
                //metodo de graffene obtenido de http://www.sc.ehu.es/sbweb/fisica_/numerico/raices/graeffe.xhtml
                

                //ahora, toca obtener el otro numero y luego armar el string con la ecuacion
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}