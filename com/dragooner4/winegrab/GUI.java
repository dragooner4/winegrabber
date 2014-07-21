package com.dragooner4.winegrab;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.dragooner4.framework.*;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI extends MyContext {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	public static boolean initiate = false;
	public static boolean alchSelected = true
        public static boolean initiate = false;
	public static boolean alchSelected = true;
	public static String alchItemID = "";

	/**
	 * Launch the application.
	 */
	public GUI(MyContext ctx) {
		super(ctx);
		create();
	}

	/**
	 * Create the frame.
	 */
	public void create() {
		frame = new JFrame("Zamorak Wine Grabber");
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Zamorak Wine Grabber");
		lblNewLabel.setBounds(10, 11, 424, 62);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Pristina", Font.PLAIN, 51));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("By: Dragooner4");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Segoe Script", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(134, 60, 163, 23);
		contentPane.add(lblNewLabel_1);

		JCheckBox checkBox = new JCheckBox(
				"Check if you want to alch while waiting for the wine to spawn.");
		checkBox.setSelected(true);
		checkBox.setBounds(10, 94, 406, 23);
		checkBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					textField.setEnabled(false);
					alchSelected = false;
				} else {
					alchSelected = true;
					textField.setEnabled(true);
				}

			}
		});
		contentPane.add(checkBox);

		JLabel lblNoteWillNot = new JLabel(
				"Note: Will not grab the wine as fast as possible if in this mode. ");
		lblNoteWillNot.setBounds(36, 116, 364, 14);
		contentPane.add(lblNoteWillNot);

		JLabel lblBestIfUsed = new JLabel(
				"Best if used if you are the only person in the altar.");
		lblBestIfUsed.setBounds(34, 136, 343, 14);
		contentPane.add(lblBestIfUsed);

		textField = new JTextField();
		textField.setBounds(119, 161, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				alchItemID = textField.getText();

			}

		});

		JLabel lblItemToAlch = new JLabel("Item to alch ID:");
		lblItemToAlch.setBounds(22, 164, 118, 14);
		contentPane.add(lblItemToAlch);

		Button button = new Button("Start");
		button.setActionCommand("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Start")) {
					initiate = true;
					frame.dispose();
				}
			}
		});
		button.setBounds(149, 215, 86, 37);
		contentPane.add(button);
		frame.setVisible(true);
	}
}
