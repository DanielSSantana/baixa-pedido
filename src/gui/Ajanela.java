package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import codigo.Processamento;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Ajanela extends JFrame {

	private JPanel contentPane;
	private JTextField textNpedido;
	private JTextField textRetorno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajanela frame = new Ajanela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ajanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 214, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero Pedido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 51, 116, 14);
		panel.add(lblNewLabel);
		
		textNpedido = new JTextField();
		textNpedido.setBounds(67, 76, 86, 20);
		panel.add(textNpedido);
		textNpedido.setColumns(10);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Processamento processo = new Processamento();
				
				try {
					textRetorno.setText(processo.processo(textNpedido.getText()));
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFinalizar.setBounds(16, 123, 89, 23);
		panel.add(btnFinalizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Processamento processo = new Processamento();
				
				try {
					textRetorno.setText(processo.cancelar(textNpedido.getText()));
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancelar.setBounds(115, 123, 89, 23);
		panel.add(btnCancelar);
		
		textRetorno = new JTextField();
		textRetorno.setEditable(false);
		textRetorno.setBounds(0, 179, 214, 20);
		panel.add(textRetorno);
		textRetorno.setColumns(10);
	}
}
