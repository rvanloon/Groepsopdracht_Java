package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ToevoegenQuizController;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class ToevoegenQuizView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ToevoegenQuizController controller;
	private JTextField textField_Onderwerp;
	private JTextField textField_MaxScore;
	private JTextField textField_TotScore;
	private JList list_Opdrachten;
	private JList list_QuizOpdrachten;
	private JComboBox comboBox_Categorie;
	private JComboBox comboBox_Type;
	private JCheckBox chckbx_IsTest;
	private JCheckBox chckbx_UniekeDeelname;
	private JComboBox comboBox_LeerjarenVan;
	private JComboBox comboBox_LeerjarenTot;
	private JComboBox comboBox_Auteur;
	private JComboBox comboBox_Status;

	/**
	 * Create the frame.
	 */
	public ToevoegenQuizView(ToevoegenQuizController toevoegenQuizController) {
		controller = toevoegenQuizController;

		setBounds(100, 100, 739, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button_VerwijderOpdracht = new JButton("<");
		button_VerwijderOpdracht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.verwijderOpdrachtVanQuiz();
			}
		});
		button_VerwijderOpdracht.setBounds(400, 345, 41, 23);
		contentPane.add(button_VerwijderOpdracht);

		JButton button_VoegOpdrachtToe = new JButton(">");
		button_VoegOpdrachtToe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.voegOpdrachtToeAanQuiz();
			}
		});
		button_VoegOpdrachtToe.setBounds(400, 311, 41, 23);
		contentPane.add(button_VoegOpdrachtToe);

		JLabel lblOnderwerp = new JLabel("Onderwerp:");
		lblOnderwerp.setBounds(10, 11, 77, 14);
		contentPane.add(lblOnderwerp);

		JLabel lblLeerjaren = new JLabel("Leerjaren");
		lblLeerjaren.setBounds(10, 36, 58, 14);
		contentPane.add(lblLeerjaren);

		JLabel lblVan = new JLabel("Van:");
		lblVan.setBounds(78, 36, 37, 14);
		contentPane.add(lblVan);

		JLabel lblTot = new JLabel("Tot:");
		lblTot.setBounds(78, 61, 37, 14);
		contentPane.add(lblTot);

		comboBox_LeerjarenVan = new JComboBox(controller.getLeerjaren());
		comboBox_LeerjarenVan.setBounds(110, 33, 50, 20);
		contentPane.add(comboBox_LeerjarenVan);

		comboBox_LeerjarenTot = new JComboBox(controller.getLeerjaren());
		comboBox_LeerjarenTot.setBounds(110, 58, 50, 20);
		contentPane.add(comboBox_LeerjarenTot);

		textField_Onderwerp = new JTextField();
		textField_Onderwerp.setBounds(86, 8, 275, 20);
		contentPane.add(textField_Onderwerp);
		textField_Onderwerp.setColumns(10);

		chckbx_IsTest = new JCheckBox("Is test?");
		chckbx_IsTest.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controller.IsTestIsChanged();
			}
		});
		chckbx_IsTest.setBounds(18, 82, 97, 23);
		contentPane.add(chckbx_IsTest);

		chckbx_UniekeDeelname = new JCheckBox("Unieke deelname?");
		chckbx_UniekeDeelname.setBounds(18, 108, 142, 23);
		contentPane.add(chckbx_UniekeDeelname);

		JLabel lblAuteur = new JLabel("Auteur:");
		lblAuteur.setBounds(10, 138, 58, 14);
		contentPane.add(lblAuteur);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 163, 58, 14);
		contentPane.add(lblStatus);

		comboBox_Auteur = new JComboBox(controller.getLeeraars());
		comboBox_Auteur.setBounds(78, 138, 142, 20);
		contentPane.add(comboBox_Auteur);

		comboBox_Status = new JComboBox(controller.getQuizStatussen());
		comboBox_Status.setBounds(78, 163, 142, 20);
		contentPane.add(comboBox_Status);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 188, 723, 8);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(426, 11, 2, 489);
		contentPane.add(separator_1);

		JButton btn_Registreer = new JButton("Registreer");
		btn_Registreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.voegQuizToeAanCatalogus();
			}
		});
		btn_Registreer.setBounds(559, 8, 111, 23);
		contentPane.add(btn_Registreer);

		JButton btn_Annuleer = new JButton("Annuleer");
		btn_Annuleer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toonMenu();
			}
		});
		btn_Annuleer.setBounds(559, 58, 111, 23);
		contentPane.add(btn_Annuleer);

		list_Opdrachten = new JList(controller.getOpdrachten().toArray());
		list_Opdrachten.setBounds(10, 268, 391, 210);
		contentPane.add(list_Opdrachten);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 194, 58, 14);
		contentPane.add(lblType);

		comboBox_Type = new JComboBox(controller.getOpdrachttypes());
		comboBox_Type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controller.herlaadOpdrachten();
			}
		});
		comboBox_Type.setBounds(10, 212, 105, 20);
		contentPane.add(comboBox_Type);

		JButton btn_AlleOpdrachten = new JButton("Alle opdrachten");
		btn_AlleOpdrachten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_Type.setSelectedItem(null);
				comboBox_Categorie.setSelectedItem(null);
			}
		});
		btn_AlleOpdrachten.setBounds(115, 211, 126, 23);
		contentPane.add(btn_AlleOpdrachten);

		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(242, 194, 68, 14);
		contentPane.add(lblCategorie);

		comboBox_Categorie = new JComboBox(controller.getOpdrachtCategorieen());
		comboBox_Categorie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controller.herlaadOpdrachten();
			}
		});
		comboBox_Categorie.setBounds(242, 212, 105, 20);
		contentPane.add(comboBox_Categorie);

		JLabel lblMaximumScore = new JLabel("Maximum score:");
		lblMaximumScore.setBounds(490, 195, 100, 14);
		contentPane.add(lblMaximumScore);

		textField_MaxScore = new JTextField();
		textField_MaxScore.setBounds(616, 192, 86, 20);
		contentPane.add(textField_MaxScore);
		textField_MaxScore.setColumns(10);

		JLabel lblTotaleScore = new JLabel("Totale score:");
		lblTotaleScore.setBounds(504, 220, 86, 14);
		contentPane.add(lblTotaleScore);

		textField_TotScore = new JTextField();
		textField_TotScore.setEditable(false);
		textField_TotScore.setColumns(10);
		textField_TotScore.setBounds(616, 215, 86, 20);
		contentPane.add(textField_TotScore);

		JLabel lblVraag = new JLabel("Vraag");
		lblVraag.setBounds(514, 245, 37, 14);
		contentPane.add(lblVraag);

		JLabel lblScore = new JLabel("max. score");
		lblScore.setBounds(634, 245, 68, 14);
		contentPane.add(lblScore);

		JLabel label = new JLabel("Vraag");
		label.setBounds(10, 243, 58, 14);
		contentPane.add(label);

		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setBounds(204, 244, 37, 14);
		contentPane.add(lblType_1);

		JLabel lblCategorie_1 = new JLabel("Categorie");
		lblCategorie_1.setBounds(297, 243, 64, 14);
		contentPane.add(lblCategorie_1);

		list_QuizOpdrachten = new JList();
		list_QuizOpdrachten.setBounds(438, 269, 275, 210);
		contentPane.add(list_QuizOpdrachten);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				controller.toonMenu();
			}
		});
	}

	public Object GetOpdrachtfilterCategorie() {
		Object o = comboBox_Categorie.getSelectedItem();
		return o;
	}

	public Object GetOpdrachtfilterType() {
		return comboBox_Type.getSelectedItem();
	}

	/**
	 * Geeft het geselecteerde object terug uit de opdrachtenlist.
	 * 
	 * @return
	 */
	public Object getSelectedOpdracht() {
		return list_Opdrachten.getSelectedValue();
	}

	/**
	 * Geeft het geselecteerde object terug uit de quizopdrachtenlist.
	 * 
	 * @return
	 */
	public Object getSelectedQuizOpdracht() {
		return list_QuizOpdrachten.getSelectedValue();
	}

	/**
	 * @return the textField_MaxScore
	 */
	public String getMaxScore() {
		return textField_MaxScore.getText();
	}

	public void setTotaleScore(String score) {
		textField_TotScore.setText(score);
	}

	public String getOnderwerp() {
		return textField_Onderwerp.getText();
	}

	public int getLeerjaarVan() {
		return Integer.parseInt((String) comboBox_LeerjarenVan
				.getSelectedItem());
	}

	public int getLeerjaarTot() {
		return Integer.parseInt((String) comboBox_LeerjarenTot
				.getSelectedItem());
	}

	public boolean isTest() {
		return chckbx_IsTest.isSelected();
	}

	public boolean isUniekeDeelname() {
		return chckbx_UniekeDeelname.isSelected();
	}

	public void setUniekeDeelname(boolean waarde) {
		chckbx_UniekeDeelname.setSelected(waarde);
	}

	public void setUniekeDeelnameEnabled(boolean waarde) {
		chckbx_UniekeDeelname.setEnabled(waarde);
	}

	public String getAuteur() {
		return (String) comboBox_Auteur.getSelectedItem();
	}

	public String getStatus() {
		return (String) comboBox_Status.getSelectedItem();
	}

	/**
	 * Vul de jlist opdrachten in met de inhoud van een arraylist
	 * 
	 * @param objecten
	 */
	public void vulOpdrachtenIn(ArrayList<Object> objecten) {
		DefaultListModel<Object> model = new DefaultListModel<Object>();
		for (Object opdracht : objecten) {
			model.addElement(opdracht);
		}
		list_Opdrachten.setModel(model);
	}

	/**
	 * Vul de jlist quizopdrachten in met de inhoud van een arraylist
	 * 
	 * @param objecten
	 */
	public void vulQuizOpdrachtenIn(ArrayList<Object> objecten) {
		DefaultListModel<Object> model = new DefaultListModel<Object>();
		for (Object opdracht : objecten) {
			model.addElement(opdracht);
		}
		list_QuizOpdrachten.setModel(model);
	}
}
