package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import controller.BeheerApplicatieController;

public class BeheerApplicatieView extends JFrame {

	private JPanel contentPane;
	private BeheerApplicatieController controller;
	private JComboBox comboBox_Scores;
	private JRadioButton rdbtnLokaaltextbestand;
	private JRadioButton rdbtnNetwerkdatabank;

	/**
	 * Create the frame.
	 */
	public BeheerApplicatieView(
			BeheerApplicatieController beheerApplicatieController) {

		controller = beheerApplicatieController;

		setBounds(100, 100, 296, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOpslag = new JLabel("Opslag:");
		lblOpslag.setBounds(10, 11, 43, 16);
		contentPane.add(lblOpslag);

		rdbtnLokaaltextbestand = new JRadioButton("lokaal (textbestand)");
		rdbtnLokaaltextbestand.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controller.setDao();
			}
		});
		rdbtnLokaaltextbestand.setBounds(20, 31, 137, 18);
		contentPane.add(rdbtnLokaaltextbestand);

		rdbtnNetwerkdatabank = new JRadioButton("netwerk (databank)");
		rdbtnNetwerkdatabank.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				controller.setDao();
			}
		});
		rdbtnNetwerkdatabank.setBounds(20, 50, 137, 18);
		contentPane.add(rdbtnNetwerkdatabank);

		ButtonGroup groep = new ButtonGroup();
		groep.add(rdbtnLokaaltextbestand);
		groep.add(rdbtnNetwerkdatabank);

		JLabel lblTypeScoreberekening = new JLabel("Type scoreberekening:");
		lblTypeScoreberekening.setBounds(10, 92, 125, 16);
		contentPane.add(lblTypeScoreberekening);

		comboBox_Scores = new JComboBox(controller.getSoortenScores());
		comboBox_Scores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controller.setSoortScores();
			}
		});
		comboBox_Scores.setBounds(20, 119, 231, 26);
		contentPane.add(comboBox_Scores);

		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toonMenu();
			}
		});
		btnOk.setBounds(89, 184, 90, 28);
		contentPane.add(btnOk);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				controller.toonMenu();
			}
		});
	}

	public void setSoortScores() {
		comboBox_Scores.setSelectedItem(controller.getSoortSores());
	}

	public String getSoortScores() {
		return comboBox_Scores.getSelectedItem().toString();
	}

	public boolean isLokaalTextbestandSelected() {
		boolean b = rdbtnLokaaltextbestand.isSelected();
		return b;
	}

	public void setLokaalTextbestandSelected(boolean b) {
		rdbtnLokaaltextbestand.setSelected(b);
	}

	public boolean isNetwerDatabankSelected() {
		boolean b = rdbtnNetwerkdatabank.isSelected();
		return b;
	}

	public void setNetwerDatabankSelected(boolean b) {
		rdbtnNetwerkdatabank.setSelected(b);
	}
}
