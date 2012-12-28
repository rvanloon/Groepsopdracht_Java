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
	private static Properties prop;

	/**
	 * Laad de xlm file met de properties
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
	 * @throws IOException
	 */
	private static void saveProperties() throws IOException {
		OutputStream stream = new FileOutputStream(xmlFile);
		prop.storeToXML(stream, "");
		stream.close();
	}

	public static String getQuizscoreStrategy()
			throws InvalidPropertiesFormatException, IOException {
		laadProperties();
		return prop.getProperty(scoreStrategy);
	}

	public static void setQuizscoreStrategy(String quizscore)
			throws IOException {
		laadProperties();
		prop.setProperty(scoreStrategy, quizscore);
		saveProperties();
	}

}
