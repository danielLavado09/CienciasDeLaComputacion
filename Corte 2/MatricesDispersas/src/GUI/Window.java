package GUI;

import javax.swing.*;

import Logic.Matriz;
import Logic.NodoColumna;
import Logic.NodoFila;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;

public class Window extends JFrame implements ActionListener {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel optionsPanel, mathPanel;
	private JButton btnMath, btnShow, btnClean;
	private JTextPane txtMathA, txtMathB, txtMathProductAB;
	private JScrollPane scrollMathA, scrollMathB, scrollMathAB;
	private JLabel lblTitle, lblMatrixA, lblMatrixB;
	private boolean checkDataMatrixA, checkDataMatrixB = false;
	private Matriz a, b, r;
	private int MaxColA, MaxColB, MaxFilA, MaxFilB;

	public Window(String title) {
		a = new Matriz("a");
		b = new Matriz("b");
		r = new Matriz("resultado");
		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void loadComponents() {

		optionsPanel = new JPanel(null);
		// optionsPanel.setBounds(0,0,400,600);
		optionsPanel.setBackground(Color.gray);
		add(optionsPanel, BorderLayout.CENTER);

		// mathPanel = new JPanel();
		// contentPanel.add(mathPanel);

		lblMatrixA = new JLabel("Matriz A (Fila, Columna, Valor)");
		lblMatrixA.setBounds(10, 10, 380, 40);
		lblMatrixA.setFont(new Font("courier", Font.BOLD, 20));
		optionsPanel.add(lblMatrixA);

		txtMathA = new JTextPane();
		optionsPanel.add(txtMathA);

		scrollMathA = new JScrollPane(txtMathA);
		scrollMathA.setBounds(10, 40, 380, 80);
		optionsPanel.add(scrollMathA);

		lblMatrixB = new JLabel("Matriz B (Fila, Columna, Valor)");
		lblMatrixB.setBounds(10, 170, 380, 40);
		lblMatrixB.setFont(new Font("courier", Font.BOLD, 20));
		optionsPanel.add(lblMatrixB);

		txtMathB = new JTextPane();
		optionsPanel.add(txtMathB);

		scrollMathB = new JScrollPane(txtMathB);
		scrollMathB.setBounds(10, 200, 380, 80);
		optionsPanel.add(scrollMathB);

		btnMath = new JButton("Enviar Matrices");
		btnMath.setFocusable(false);
		btnMath.setBounds(10, 500, 380, 50);
		btnMath.addActionListener(this);
		optionsPanel.add(btnMath);

		btnShow = new JButton("Imprimir");
		btnShow.setFocusable(false);
		btnShow.setBounds(380, 500, 380, 50);
		btnShow.addActionListener(this);
		optionsPanel.add(btnShow);

		txtMathProductAB = new JTextPane();
		optionsPanel.add(txtMathProductAB);

		scrollMathAB = new JScrollPane(txtMathProductAB);
		scrollMathAB.setBounds(400, 10, 380, 480);
		optionsPanel.add(scrollMathAB);

		btnClean = new JButton("Limpiar");
		btnClean.setFocusable(false);
		btnClean.setBounds(10, 300, 380, 50);
		btnClean.addActionListener(this);
		optionsPanel.add(btnClean);

		add(optionsPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public void cleanData() {
		a = new Matriz("a");
		b = new Matriz("b");
		r = new Matriz("r");
		txtMathA.setText("");
		txtMathB.setText("");
		txtMathProductAB.setText("");
	}

	public void addA(int fila, int columna, int valor) {

		NodoColumna col = new NodoColumna(columna);
		NodoFila fil = new NodoFila(fila, valor);

		if (a.getInicio() == null) {
			// si no hay un nodo inicial , se convierte en el primero
			a.setInicio(col);
			col.setCabeza(fil);
		} else {
			// A�ade el nodo
			a.addNodoColumna(col, a.getInicio(), fil);

		}
		System.out.println("fila bassic: " + fila + " Columna: " + columna + " Valor: " + valor+"\n");

	}

	public void addB(int fila, int columna, int valor) {

		NodoColumna col = new NodoColumna(columna);
		NodoFila fil = new NodoFila(fila, valor);

		if (b.getInicio() == null) {
			// si no hay un nodo inicial , se convierte en el primero
			b.setInicio(col);
			col.setCabeza(fil);
		} else {
			// A�ade el nodo
			b.addNodoColumna(col, b.getInicio(), fil);

		}
		System.out.println("fila bassic: " + fila + " Columna: " + columna + " Valor: " + valor+"\n");
	}

	public void max() {
		MaxColA = a.nColumnas(a.getInicio());
		MaxColB = b.nColumnas(b.getInicio());
		MaxFilA = a.nFilas(a.getInicio());
		MaxFilB = b.nFilas(b.getInicio());
		System.out.println("Matriz A -> MaxCol: " + MaxColA + " MaxFil: " + MaxFilA);
		System.out.println("Matriz B -> MaxCol: " + MaxColB + " MaxFil: " + MaxFilB);
	}

	public void showData() {
		a.mostrarlista(a.getInicio());
		b.mostrarlista(b.getInicio());
		r.mostrarlista(r.getInicio());
		txtMathProductAB.setText("Matriz A: \n" + a.getListaString() + "\n Matriz B: \n" + b.getListaString()
				+ "\n Matriz A*B" + ": \n" + r.getListaString());
	}

	public void enviarMatriz() {
		System.out.println("Datos enviados: Calculando...");

		// Get data.
		Validation validate = new Validation();

		String stringMatrixA = getTxtMathA().getText();
		System.out.println(stringMatrixA);
		String[] splitMatrixA = stringMatrixA.split(";");

		String stringMatrixB = getTxtMathB().getText();
		System.out.println(stringMatrixB);
		String[] splitMatrixB = stringMatrixB.split(";");

		// Check data for MatrixA.
		for (int i = 0; i < splitMatrixA.length; i++) {

			if (validate.isValidFormat(splitMatrixA[i]) == false) {
				checkDataMatrixA = false;
				i = splitMatrixA.length;
			} else {
				checkDataMatrixA = true;
			}
		}

		// Check data for MatrixB.
		for (int i = 0; i < splitMatrixB.length; i++) {
			if (validate.isValidFormat(splitMatrixB[i]) == false) {
				checkDataMatrixB = false;
				i = splitMatrixB.length;
			} else {
				checkDataMatrixB = true;
			}
		}
		//System.out.println(checkDataMatrixA + " <-Matrix A - Matrix B-> " + checkDataMatrixB);

		// Send data.
		if (checkDataMatrixB && (checkDataMatrixA)) {
			System.out.println("Datos correctos: Calculando...");

			// Send data for MatrixA.
			for (int i = 0; i < splitMatrixA.length; i++) {
				splitMatrixA[i] = splitMatrixA[i].replace("(", "");
				splitMatrixA[i] = splitMatrixA[i].replace(")", "");
				String[] tempValuesMatrixA = splitMatrixA[i].split(",");
                System.out.println(Arrays.toString(tempValuesMatrixA));
                // splitMatrix[n] = "(c,f,v)[0]; (c,f,v)[1]"

				boolean aux = false;
				while (aux != true) {
					// Fila,columna,valor
					System.out.println("Matriz A __>"+"Fila>"
					        + Integer.parseInt(tempValuesMatrixA[0])+
					        " Columna> "
					        + Integer.parseInt(tempValuesMatrixA[1])+
							" Valor> "
							+ Integer.parseInt(tempValuesMatrixA[2]));
					addA(Integer.parseInt(tempValuesMatrixA[0]), Integer.parseInt(tempValuesMatrixA[1]),
							Integer.parseInt(tempValuesMatrixA[2]));
					aux = true;
				}
				// si metemos el crear nodo aca, sera que se rompe? // R: Ya vamos a ver
			}

			// Send data for MatrixB.
			for (int i = 0; i < splitMatrixB.length; i++) {
				splitMatrixB[i] = splitMatrixB[i].replace("(", "");
				splitMatrixB[i] = splitMatrixB[i].replace(")", "");
				String[] tempValuesMatrixB = splitMatrixB[i].split(",");
				System.out.println(Arrays.toString(tempValuesMatrixB));
				boolean aux = false;
				while (aux != true) {
					// Fila,columna,valor
					System.out.println("Matriz B __>"+"Fila>"+Integer.parseInt(tempValuesMatrixB[0])+" Columna> "+ Integer.parseInt(tempValuesMatrixB[1])+
							" valor> "+Integer.parseInt(tempValuesMatrixB[2]));
					addB(Integer.parseInt(tempValuesMatrixB[0]), Integer.parseInt(tempValuesMatrixB[1]),
							Integer.parseInt(tempValuesMatrixB[2]));
					aux = true;
				}
			}
		}
	}
	public void nuevaMultiplicacion() {
		System.out.println(MaxColA + "--" + MaxFilB);
		if (MaxColA == MaxFilB) {//Col A = filas B
			NodoColumna columnaA = a.getInicio();
	        while(columnaA != null){//Mientras que hayan elementos en la columna A inicial
	        	
	        NodoColumna columnaB = b.getInicio();
	        while(columnaB != null){//Mientras hayan elementos en la Columna B inicial
	            	
	        NodoFila filaB = columnaB.getCabeza();
	        while(filaB != null){//Mientras hayan elementos en la FilaB inicial
	             if(columnaA.getCol() == filaB.getFila()){//si los indices de colA = filB
	                    	
	                NodoFila filaA = columnaA.getCabeza();
	                while(filaA != null){//itera sobre la columna A
	                        	
	            	 NodoColumna nuevaColumna = new NodoColumna(columnaB.getCol());
	                 NodoFila nuevaFila = new NodoFila(filaA.getFila(),filaA.getValor()*filaB.getValor());
	                 if(r.getInicio() == null){
	                       r.setInicio(nuevaColumna);
	                       nuevaColumna.setCabeza(nuevaFila);
	                        }else{
	                         r.insertarColumnaR(nuevaColumna, nuevaFila, r.getInicio());
	                        }
	                            filaA = filaA.getAbajo();//aumenta fila A
	                 }
	             }
	             filaB = filaB.getAbajo();//aumenta fila B
	        }
	        columnaB = columnaB.getSiguiente();//aumenta columna B
	        }
	        columnaA = columnaA.getSiguiente(); //aumenta Columna A
	        }
	        
	        }else {
			//Los tama�os no son compatibles
			JOptionPane.showMessageDialog(null, "Los tama�os de las matrices no son compatibles");
		}
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMath) {
			enviarMatriz();
			max();		
			nuevaMultiplicacion();
			
		} else if (e.getSource() == btnShow) {
			// TODO
			// showc();
			showData();

		} else if (e.getSource() == btnClean) {
			// TODO
			cleanData();
		}
	}

	// Getters, Setters.
	public JPanel getOptionsPanel() {
		return optionsPanel;
	}

	public void setOptionsPanel(JPanel optionsPanel) {
		this.optionsPanel = optionsPanel;
	}

	public JPanel getMathPanel() {
		return mathPanel;
	}

	public void setMathPanel(JPanel mathPanel) {
		this.mathPanel = mathPanel;
	}

	public JButton getBtnMath() {
		return btnMath;
	}

	public void setBtnMath(JButton btnMath) {
		this.btnMath = btnMath;
	}

	public JTextPane getTxtMathProductAB() {
		return txtMathProductAB;
	}

	public JTextPane getTxtMathA() {
		return txtMathA;
	}

	public void setTxtMathA(JTextPane txtMathA) {
		this.txtMathA = txtMathA;
	}

	public JTextPane getTxtMathB() {
		return txtMathB;
	}

	public void setTxtMathB(JTextPane txtMathB) {
		this.txtMathB = txtMathB;
	}

	public JScrollPane getScrollMathA() {
		return scrollMathA;
	}

	public void setScrollMathA(JScrollPane scrollMathA) {
		this.scrollMathA = scrollMathA;
	}

	public JScrollPane getScrollMathB() {
		return scrollMathB;
	}

	public void setScrollMathB(JScrollPane scrollMathB) {
		this.scrollMathB = scrollMathB;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblMatrixA() {
		return lblMatrixA;
	}

	public void setLblMatrixA(JLabel lblMatrixA) {
		this.lblMatrixA = lblMatrixA;
	}

	public JLabel getLblMatrixB() {
		return lblMatrixB;
	}

	public void setLblMatrixB(JLabel lblMatrixB) {
		this.lblMatrixB = lblMatrixB;
	}

}