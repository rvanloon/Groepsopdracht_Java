package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class BeheerQuizApplicatie {

	private static String xmlFile = "TextFiles\\settings.xml";
	private static String scoreStrategy = "scoreStrategy";
	private static String daoType = "DAO_type";
	private static Properties prop;

	/**
	 * Laad de xlm file met de properties
	 * 
	 * @throws InvalidPropertiesFormatException
	 * @throws IOException
	 */
	private static void laadProperties()
			throws InvalidPropertiesFormatException, IOException {
		prop = new Properties();
		InputStream stream = new FileInputStream(xmlFile);
		prop.loadFromXML(stream);
		stream.close();
	}

	/**
	 * Schrijft de properties weg naar een xml-file
	 * 
	 * @throws IOException
	 */
	private static void saveProperties() throws IOException {
		OutputStream stream = new FileOutputStream(xmlFile);
		prop.storeToXML(stream, "");
		stream.close();
	}

	/**
	 * geeft uit de settingsfile de scorestrategie.
	 * 
	 * @return string
	 * @throws InvalidPropertiesFormatException
	 * @throws IOException
	 */
	public static String getQuizscoreStrategy() throws IOException {
		laadProperties();
		return prop.getProperty(scoreStrategy);
	}

	/**
	 * schrijft de scorestrategie weg in de settingsfile.
	 * 
	 * @param quizscore
	 * @throws IOException
	 */
	public static void setQuizscoreStrategy(String quizscore)
			throws IOException {
		laadProperties();
		prop.setProperty(scoreStrategy, quizscore);
		saveProperties();
	}

	public static String getDaoType() throws IOException {
		laadProperties();
		return prop.getProperty(daoType);
	}

	public static void setDaoType(String daoTypeNaam) throws IOException {
		laadProperties();
		prop.setProperty(daoType, daoTypeNaam);
		saveProperties();
	}

}
