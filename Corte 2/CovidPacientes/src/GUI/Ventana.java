
package GUI;


import GUI.Lienzo;
import GUI.Controlador;
import Logic.Reporte;
import Logic.Nodo;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ventana {

	ArrayList<Integer> valores = new ArrayList<Integer>();
        int x = 0;
        Nodo arbol;
        
        int pos;
        Graphics g;
	private JFrame frame;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtPreorden;
	private JTextField txtInorden;
	private JTextField txtPosorden;
	private JTextField txtCod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
            LocalDateTime fechHoy = LocalDateTime.now();
            
		frame = new JFrame("Reporte Covid");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);

                
                JLabel lblTitle = new JLabel("Informacion paciente:");
                lblTitle.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTitle.setBounds(21, 25, 180, 18);
                lblTitle.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setBounds(21, 85, 96, 14);
                lblCodigo.setForeground(Color.WHITE);
		frame.getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(21, 55, 96, 14);
                lblNombre.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(89, 85, 200, 20);
		frame.getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(89, 55, 200, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
                JLabel lblformato = new JLabel("Fecha tipo AAAA/MM/DD ");
		lblformato.setBounds(21, 120, 200, 14);
                lblformato.setForeground(Color.WHITE);
		frame.getContentPane().add(lblformato);
                
                
                JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(21, 140, 96, 14);
                lblFecha.setForeground(Color.WHITE);
		frame.getContentPane().add(lblFecha);
                
		txtFecha = new JTextField();
		txtFecha.setBounds(89, 140, 200, 20);
		frame.getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblPreorden = new JLabel("Preorden: ");
		lblPreorden.setBounds(21, 185, 83, 14);
                lblPreorden.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPreorden);
		
		JLabel lblInorden = new JLabel("Inorden: ");
		lblInorden.setBounds(21, 215, 83, 14);
                lblInorden.setForeground(Color.WHITE);
		frame.getContentPane().add(lblInorden);
		
		JLabel lblPosorden = new JLabel("Posorden: ");
		lblPosorden.setBounds(21, 245, 83, 14);
                lblPosorden.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPosorden);
		
		txtPreorden = new JTextField();
		txtPreorden.setBounds(95, 182, 200, 20);
		frame.getContentPane().add(txtPreorden);
		txtPreorden.setColumns(10);
		
		txtInorden = new JTextField();
		txtInorden.setBounds(95, 212, 200, 20);
		frame.getContentPane().add(txtInorden);
		txtInorden.setColumns(10);
		
		txtPosorden = new JTextField();
		txtPosorden.setBounds(95, 242, 200, 20);
		frame.getContentPane().add(txtPosorden);
		txtPosorden.setColumns(10);
		
		/*JLabel lblCod = new JLabel("Codigo: ");
		lblCod.setBounds(21, 107, 96, 14);
		frame.getContentPane().add(lblCod);*/
		
		/*txtCod = new JTextField();
		txtCod.setBounds(89, 104, 400, 20);
		frame.getContentPane().add(txtCod);
		txtCod.setColumns(10);*/
		
		JButton btnAgregar = new JButton("Agregar Paciente");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agregar) {
				try {
		            if (x < 19 && !valores.contains(Integer.parseInt(txtCodigo.getText()))) {
		                if (x == 0) {
		                    arbol = new Nodo(Integer.parseInt(txtCodigo.getText()),txtNombre.getText());

		                } else {
		                    arbol.agregar(Integer.parseInt(txtCodigo.getText()),txtNombre.getText());
		                }
		                valores.add(Integer.parseInt(txtCodigo.getText()));
		                x++;
		                txtCodigo.setText("");
                                txtNombre.setText("");
		                txtPreorden.setText(arbol.preorden());
		                txtInorden.setText(arbol.inorden());
		                txtPosorden.setText(arbol.posorden());
		            } else if (x == 19) {
		                JOptionPane.showMessageDialog(null, "Cantidad máxima de números alcanzada");
		            } else {
		                JOptionPane.showMessageDialog(null, "El código está repetido");
		            }

		        } catch (NullPointerException e) {
		            System.out.println("nulo");
		        }
			}
		});
		btnAgregar.setBounds(320, 50, 230, 23);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar Paciente");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eliminar) {
				try {
                                    if (valores.contains(Integer.parseInt(txtCodigo.getText()))) {

                                        pos = valores.indexOf(Integer.parseInt(txtCodigo.getText()));
                                        valores.remove(pos);
                                        arbol = arbol.borrar(Integer.parseInt(txtCodigo.getText()));
                                        x--;
                                        txtCodigo.setText("");
                                        txtNombre.setText("");
                                        try{
                                            txtPreorden.setText(arbol.preorden());
                                        }catch(NullPointerException e){
                                            txtPreorden.setText("");
                                        }
                                        try{
                                            txtInorden.setText(arbol.inorden());
                                        }catch(NullPointerException e){
                                            txtInorden.setText("");
                                        }
                                        try{
                                            txtPosorden.setText(arbol.posorden());
                                        }catch(NullPointerException e){
                                            txtPosorden.setText("");
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "El valor no se encuentra en el arbol");
                                    }
                                } catch (NullPointerException e) {
                                    System.out.println("null 2");
                                }
			}
		});
		btnEliminar.setBounds(320, 90, 230, 23);
		frame.getContentPane().add(btnEliminar);
		
		JButton btnFecha = new JButton("Vacunar");
		btnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent fecha) {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
				Date fech = null;
                                
				try {
                                    if(txtFecha.getText().equals("")){
                                        JOptionPane.showMessageDialog(null, "Vacunacion dia actual");
                                        Calendar fechActual = new GregorianCalendar();
                                        int año = fechActual.get(Calendar.YEAR);
                                        int mes = fechActual.get(Calendar.MONTH);
                                        int dia = fechActual.get(Calendar.DAY_OF_MONTH);
                                        String fAct = String.valueOf(año)+"/"+String.valueOf((mes+1))+"/"+String.valueOf(dia);
                                        System.out.println(fAct);
                                        fech = formato.parse(fAct);
                                    }else{
                                        fech = formato.parse(txtFecha.getText());
                                    }
					if(arbol.vacuna1(Integer.parseInt(txtCodigo.getText()), fech)){
                                            JOptionPane.showMessageDialog(null, "Fecha registrada");
                                            txtFecha.setText("");
                                        }else{
                                            if(arbol.vacuna2(Integer.parseInt(txtCodigo.getText()), fech)){
                                                JOptionPane.showMessageDialog(null, "Fecha registrada");
                                                txtFecha.setText("");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "Necesita por lo menos 30 dias entre vacunas");
                                            }
                                        }
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnFecha.setBounds(320, 120, 230, 23);
		frame.getContentPane().add(btnFecha);
		
		JButton btnDibujar = new JButton("Graficar Informacion");
		btnDibujar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent dibujar) {
                            
                            Lienzo objLienzo = new Lienzo();
                            Controlador objControlador = new Controlador(objLienzo, arbol);
                            objControlador.iniciar();
                            JFrame ventana = new JFrame("Grafica datos");
                            ventana.getContentPane().add(objLienzo);
                            ventana.setSize(600, 600);
                            ventana.setVisible(true);
			}
		});
		btnDibujar.setBounds(320, 150, 230, 23);
		frame.getContentPane().add(btnDibujar);
                
                JButton btnInformacion = new JButton("Ver Info");
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent info) {
                            
                            Reporte ini = new Reporte(arbol.infoPaciente(Integer.parseInt(txtCodigo.getText())));
                            JFrame ven = new JFrame("Informacion usuarios");
                            ven.add(ini);
                            ven.setLocationRelativeTo(null);
                            ven.setSize(450, 150);
                            ven.setVisible(true);
			}
		});
                //
		btnInformacion.setBounds(320, 180, 230, 23);
		frame.getContentPane().add(btnInformacion);
	}
}