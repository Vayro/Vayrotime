package com.lawranta.Globals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lawranta.initializers.MainClass;
import com.lawranta.panels.AdminPinPanel;
import com.lawranta.popups.ErrorDialog;
import com.lawranta.popups.SuccessDialog;
import com.lawranta.services.EmployeeService;

public class Global {
	public static final double VERSION = 1.0;
	public static final String type = "lite";

	public final static File pathFile = new File(
			MainClass.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	static String path = pathFile.getAbsolutePath();
	// Fonts
	public static Font headerFont = new Font("TimesRoman", Font.PLAIN, 64);
	public static Font header2Font = new Font("TimesRoman", Font.PLAIN, 32);
	public static String analogFontPath = "/fonts/Analog.ttf";

	
	public static Font defaultFont = new Font("TimesRoman", Font.PLAIN, 32);
	public static Font analogFont= new Font("TimesRoman", Font.PLAIN, 32);
	public static Font analogFont32f= new Font("TimesRoman", Font.PLAIN, 32);
	public static Font analogFont16f= new Font("TimesRoman", Font.PLAIN, 16);
	public static Font analogFont12f= new Font("TimesRoman", Font.PLAIN, 12);

	/*
	 * public static Font analogFont32f = SetGlobalFont.loadFontFromFile(
	 * (MainClass.class.getResource(fontFile.getAbsolutePath()).toString()), 32f);
	 * public static Font analogFont16f =
	 * SetGlobalFont.loadFontFromFile((MainClass.class.getResource(fontFile.
	 * getAbsolutePath()).toString()), 16f); public static Font analogFont12f =
	 * SetGlobalFont.loadFontFromFile((MainClass.class.getResource(fontFile.
	 * getAbsolutePath()).toString()), 12f);
	 * 
	 * 
	 * /*public static Font analogFont32f = new Font("TimesRoman", Font.PLAIN, 32);
	 * public static Font analogFont16f = new Font("TimesRoman", Font.PLAIN, 16);
	 * public static Font analogFont12f = new Font("TimesRoman", Font.PLAIN, 12);
	 */

	public static Font timesFont12 = new Font("TimesRoman", Font.PLAIN, 12);

	// date and time
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static LocalDateTime now = LocalDateTime.now();

	public static String today = dtf.format(now);

	// Paths

	public final static String cfgPath = "null.cfg";
	public static String dbPath = "records.db";
	public static File dbFile = new File(dbPath);

	// sound sources
	public static String inSndPath = "/sounds/open.wav";
	public static String outSndPath = "/sounds/close.wav";

	// image sources

	public static String errorImgPath = "/images/error.png";
	public static String succImgPath = "/images/gear.png";
	public static String saveImgPath = "/images/save_1046452.64_pressed.png";
	public static String savePressedImgPath = "/images/save_1046452.64.png";

	// properties
	public static String adminPin;
	// loaded from crumb.dat
	public static String lastPath = new String();

	public static Component padding(int i) {
		JLabel padding = new JLabel(" ");
		padding.setPreferredSize(new Dimension(420, i));
		padding.setMinimumSize(new Dimension(420, i));
		padding.setMaximumSize(new Dimension(420, i));
		padding.setSize(new Dimension(420, i));
		padding.setVisible(true);
		padding.setAlignmentX(Component.CENTER_ALIGNMENT);
		padding.setAlignmentY(Component.CENTER_ALIGNMENT);
		return padding;
	}

//Global error dialog

	public static void showError(String e) {

		ErrorDialog errorDialog = new ErrorDialog(e);
		errorDialog.setVisible(true);

	}

//Global success dialog
	public static void showSuccess(String e) {

		SuccessDialog SuccessDialog = new SuccessDialog(e);
		SuccessDialog.setVisible(true);

	}



	public static boolean sanitizePin(String str, int Primarykey) {

		if (str.length() == 6) {
			// Traverse the string from
			// start to end
			for (int i = 0; i < 6; i++) {

				// Check if character is
				// not a digit between 0-9
				// then return false
				if (str.charAt(i) < '0' || str.charAt(i) > '9') {
					showError("pin must only contain numbers");
					return false;
				}
			}
			// If we reach here, that means
			// all characters were digits.
			if (!EmployeeService.checkDuplicatePin(str, Primarykey)) {
				return true;
			} else {
				return false;
			}
		}
		showError("pin must be 6 digits");
		return false;
	}

}
