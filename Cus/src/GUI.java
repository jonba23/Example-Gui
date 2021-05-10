import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class GUI extends JFrame {
	/**
	 *
	 */
	
	static String url = "jdbc:mysql://localhost:3306/VC3?useTimezone=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "Stjohns1234.";
    static Connection connection = null;
	
	
	private static final long serialVersionUID = 1L;
	String namehold = "";
	ArrayList<Owner> owners = new ArrayList<Owner>();
	CardLayout cl = new CardLayout(0,0);
	Owner newowner = new Owner();
	VCC VehicleController =new VCC();
	
	JPanel container = new JPanel();
	
	
	JPanel mainmenu = (new JPanel(new GridBagLayout())); // various panels needed

	
	JFrame f;
	JFrame f1;
	JFrame f2;
	JFrame f3;
	
	JPanel clientmenu = (new JPanel(new GridBagLayout()));
	JPanel ownermenu = (new JPanel(new GridBagLayout()));
	JPanel mastermenu = (new JPanel(new GridBagLayout()));
	JPanel ownerregistration = (new JPanel(new GridBagLayout()));
	JPanel ownercars = (new JPanel(new GridBagLayout()));
	
	//AdminView
	JPanel AdminViewJobs = (new JPanel(new GridBagLayout()));
	JPanel AdminViewClients = (new JPanel(new GridBagLayout()));
	JPanel AdminViewOwners = (new JPanel(new GridBagLayout()));
	JPanel AdminCalculate = (new JPanel(new GridBagLayout()));
	
	
	final JFileChooser fc = new JFileChooser();
	
	
	
	
	
	JOptionPane ownerinfo = new JOptionPane();

	String[] carmakes = { "Please select your car's make", "Acura", "Alfa-Romeo", "Aston-Martin", "Audi", "Bentley",
			"BMW", "Bugatti", "Buick", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Dodge", "Ferrari", "Fiat",
			"Ford", "Geely", "Genesis", "GMC", "Honda", "Hyundai", "Infiniti", "Jaguar", "Jeep", "Kia", "Koenigsegg",
			"Lamborghini", "Lancia", "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati", "Maybach", "Mazda",
			"McLaren", "Mercedes", "Mini", "Mitsubishi", "Nissan", "Opel", "Pagani", "Puegeot", "Pontiac", "Porsche",
			"Ram", "Renault", "Rolls-Royce", "Skoda", "Smart", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen",
			"Volvo" };

	JPanel loginmenu = (new JPanel(new GridBagLayout()));
	JPanel clientregistrationmenu = (new JPanel(new GridBagLayout()));
	JPanel jobdescriptionmenu = (new JPanel(new GridBagLayout()));
	JPanel jobdescriptionmenuNext = (new JPanel(new GridBagLayout()));

	String[] states = { "Please select state", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "GU",

			"HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MH", "MA", "MI", "MN", "MS", "MO", "MT", "NE",

			"NV", "NH", "NJ", "NM", "NY", "NC", "ND",

			"MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV",
			"WI", "WY" };

	String[] months = { "Please select a month", "January", "February", "March", "April", "May", "June", "July",
			"August",

			"September", "October", "November", "December" };

	String[] days = { "Please select a day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20",

			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

	String[] years = { "Please select a year", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029" };

	JComboBox<String> month = new JComboBox<String>(months);
	JLabel monthmout = new JLabel("Choose a Month");
	JComboBox<String> year = new JComboBox<String>(years);
	JLabel yearmout = new JLabel("Choose a Year");
	JComboBox<String> day = new JComboBox<String>(days);
	JLabel daymout = new JLabel("Choose a Day");
	JLabel deadlineselect = new JLabel("Choose your Deadline");

	JLabel caryear = new JLabel("Please enter the year of the vehicle");
	JTextField caryearin = new JTextField();
	JLabel vinnum = new JLabel("Please enter the vehicle Vin#");
	JTextField carvinin = new JTextField();
	JLabel carmake = new JLabel("Please fill out this form");
	JLabel carmodel = new JLabel("Please type in the model of your car");
	JTextField carmodeltype = new JTextField();
	JComboBox<String> cars = new JComboBox<String>(carmakes);
	
	JTextArea JobNewLine = new JTextArea();
	JPanel jobpanel = new JPanel();
	
	
	JLabel caramount = new JLabel("Click continue to start registering your car(s)");
	JButton Continue = new JButton("continue");
	JButton add = new JButton("add");

	JTextField ownerResidencedate = new JTextField();
	JTextField ownerName = new JTextField();
	JTextField JobResidencedate = new JTextField();
	
	
	JTextField durationText = new JTextField();
	JLabel durationLabel = new JLabel("Please type duration of your job.");
//	JLabel jobResponse = new JLabel("Job Status: .");
	
	
	JLabel name = new JLabel("Please type First and Last name");
	JLabel date = new JLabel("Please  the date you will return to pick up your vehicle in the format mm/dd/yyyy");
	JButton closeButton = new JButton("Close");

	// various buttons needed
	JButton homeclient = new JButton("Return to Main Menu");
	JButton homeowner = new JButton("Return to Main Menu");
	JButton homemaster = new JButton("Return to Main Menu");

	JButton clientRegistrationButton = new JButton("Client Registration");
	JButton clientMenuButton = new JButton("Client Menu");
	JButton ownerMenuButton = new JButton("Owner Menu");
	JButton ownerRegistrationButton = new JButton("Owner Registration");

	JButton jobSubmissionButton = new JButton("Job Submission");
	JButton administrationButton = new JButton("Administration");
	JButton doneButton = new JButton("Done");

	JButton homeadmin = new JButton("Return to Main Menu");
	JButton homeadminview = new JButton("Return to Main Menu");
	JButton mastercontrol = new JButton("Log In");

	JButton Admin_Back = new JButton("Return to Admin Page");
	JButton submitpass = new JButton("Leave Admin View"); // admin view

	JButton viewowners = new JButton("View Owners");
	JButton viewclients = new JButton("View Clients");
	JButton viewjobs = new JButton("View Jobs");
	JButton calculate = new JButton("Calculate Residency");
	JTextArea CalculateResult = new JTextArea();
	JTextField CalculateResidency = new JTextField();
	JLabel CalculateResultLabel = new JLabel("Residency Calculation Status: ");
	
	
	
	
	JLabel clientname = new JLabel("Please Enter Your Name");

	JTextField clientNameText = new JTextField();

	JLabel clientLicense = new JLabel("Please Enter Your License#");

	JTextField clientLicenseText = new JTextField();


	JLabel zip = new JLabel("Please enter the your Zip Code");
	JTextField zipText = new JTextField();

	JComboBox<String> state = new JComboBox<String>(states);
	JLabel statemount = new JLabel("Choose your state");

	JButton clientregistrationreturn = new JButton("Return to Client Home Page");
	JButton clientregistrationreturn2 = new JButton("Return to Client Home Page");

	JButton jobdescriptionreturn = new JButton("Return to Client Home Page");

	JButton jobdescriptioncontinue = new JButton("Continue to Job Application");

	//JTextField msgarea = new JTextField();
	JButton jobdescriptioncontinue2 = new JButton("Finish Application");
	JButton jobdescriptioncontinue3 = new JButton("Return");
	
	JButton createclient = new JButton("Sign Up");

	JPanel vehicleadd = (new JPanel(new GridBagLayout()));
	JButton returnOwnerProfileButton = new JButton("Return to Owner Profile");
	JButton finishadd = new JButton("Done");

	String[] existingvehicles = { "Please select your existing vehicle", };
	JComboBox<String> exvehicle = new JComboBox<String>(existingvehicles);

	JLabel exmout = new JLabel("Choose an existing vehicle");

	JComboBox<String> exvehicle1 = new JComboBox<String>(existingvehicles);
	JLabel exmout1 = new JLabel("Choose an existing vehicle");

	JButton ownerProfileButton = new JButton("Owner Profile");
	JButton owneraddnew = new JButton("Add New Vehicle");

	JButton removevehicle = new JButton("Remove Existing Vehicle");
	JButton existingvehicleslist = new JButton("Check Status of Existing Vehicles");

	JButton returnownerprofile1 = new JButton("Return to Main Menu");
	JButton returnownerprofile2 = new JButton("Return to Owner Profile");
	JButton returnownerprofile3 = new JButton("Return to Owner Profile");
	JButton returnownerprofile4 = new JButton("Return to Owner Profile");
	JButton returnownerprofile5 = new JButton("Return to Owner Profile");

	JButton finishremoval = new JButton("Continue to removal");

	JButton lookup = new JButton("Search Vehicle");
	JPanel vehiclelookup = (new JPanel(new GridBagLayout())); // owner 3 Add

	JPanel existingowners = (new JPanel(new GridBagLayout())); // unique code panel
	JPanel ownersaddnewvehicles = (new JPanel(new GridBagLayout())); // owner 3 Add
	JPanel removeownervehicles = (new JPanel(new GridBagLayout())); // owner 4 Removal
	JPanel existingownervehicles = (new JPanel(new GridBagLayout())); // owner 5 Status
	JPanel ownersignin = (new JPanel(new GridBagLayout()));

	JLabel OwnerCode = new JLabel("Please enter the your Unique Client Code");
	JTextField ownercodetext = new JTextField();
	JButton signin = new JButton("Sign In");

	JLabel clientCode = new JLabel("Please enter the your Unique Client Code");
	JTextField clientCodeText = new JTextField();

	JLabel deadline = new JLabel("Please select a deadline for your job.");

	JButton ownerreturn = new JButton("Return to Owner Menu");

	JLabel vehicleadding = new JLabel("Please enter the your Vehicle's VIN");

	JTextField vehicleaddingtext = new JTextField();
	
	JLabel mainMenuLabel = new JLabel("Hover over a button to see its description.");
	JLabel ownerMenuLabel = new JLabel("Hover over a button to see its description.");
	JLabel clientMenuLabel = new JLabel("Hover over a button to see its description.");
	

	GUI() throws CloneNotSupportedException {
		
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints labelConstraints = new GridBagConstraints();
		container.setLayout(cl);
 
		
		
		
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		mainmenu.add(ownerMenuButton, gbc);

		gbc.gridy = 3;
		gbc.gridx = 1; // adding buttons to main menu panel
		mainmenu.add(clientMenuButton, gbc);

		gbc.gridy = 5;
		gbc.gridx = 1;
		mainmenu.add(administrationButton, gbc);

		gbc.gridy = 7;
		gbc.gridx = 1;
		mainmenu.add(closeButton, gbc);
		
		labelConstraints.gridy = 9;
		labelConstraints.gridx = 1;
		mainmenu.add(mainMenuLabel, labelConstraints);
		ownermenu.add(ownerMenuLabel, labelConstraints);
		clientmenu.add(clientMenuLabel, labelConstraints);
		
		labelConstraints.gridy = 9;
		labelConstraints.gridx = 1;
		mainmenu.add(mainMenuLabel, labelConstraints);
		
		mainmenu.setBackground(Color.pink);

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		clientmenu.add(clientRegistrationButton, gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		clientmenu.add(jobSubmissionButton, gbc);
		gbc.gridy = 5;
		gbc.gridx = 1;
		clientmenu.add(homeclient, gbc); // adding buttons to client menu
		clientmenu.setBackground(Color.orange);

		/// Start Of Client Registration

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		clientregistrationmenu.add(name, gbc);
		gbc.gridy = 10;
		clientregistrationmenu.add(clientregistrationreturn, gbc);
		gbc.gridy = 5;
		clientregistrationmenu.add(state, gbc);
		gbc.gridy = 1;
		clientregistrationmenu.add(clientname, gbc);
		gbc.gridy = 2;
		clientregistrationmenu.add(clientNameText, gbc);
		gbc.gridy = 3;
		clientregistrationmenu.add(clientLicense, gbc);
		gbc.gridy = 4;
		clientregistrationmenu.add(clientLicenseText, gbc);
		gbc.gridy = 6;
		clientregistrationmenu.add(zip, gbc);
		gbc.gridy = 7;
		clientregistrationmenu.add(zipText, gbc);
		gbc.gridy = 9;
		clientregistrationmenu.add(createclient, gbc);

		/// Job Description Menu

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 2;
		jobdescriptionmenu.add(clientCode, gbc);
		gbc.gridy = 3;
		jobdescriptionmenu.add(clientCodeText, gbc);
		gbc.gridy = 4;
		jobdescriptionmenu.add(jobdescriptioncontinue, gbc);
		gbc.gridy = 5;
		jobdescriptionmenu.add(clientregistrationreturn2, gbc);

		// Job Description Application

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 2;
		jobdescriptionmenuNext.add(deadlineselect, gbc);
		
		gbc.gridy = 3;
		jobdescriptionmenuNext.add(JobResidencedate,gbc);
		
		gbc.gridy = 4;
		jobdescriptionmenuNext.add(durationLabel, gbc);
		gbc.gridy = 5;
		jobdescriptionmenuNext.add(durationText, gbc);
		//gbc.gridy = 5;
		
		gbc.gridy = 6;

	//	jobdescriptionmenuNext.add(jobResponse, gbc);
		
		//gbc.gridy = 7;
		//jobdescriptionmenuNext.add(msgarea, gbc);
		
		gbc.gridy = 8;
		jobdescriptionmenuNext.add(jobdescriptioncontinue2, gbc);
		
		gbc.gridy = 9;
		jobdescriptionmenuNext.add(jobdescriptioncontinue3, gbc);
		

		/// Owner Menu

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		ownermenu.add(ownerRegistrationButton, gbc);
		gbc.gridy = 4;
		ownermenu.add(homeowner, gbc);
		gbc.gridy = 3;
		ownermenu.add(ownerProfileButton, gbc);
		// adding buttons to owner menu
		ownermenu.setBackground(Color.LIGHT_GRAY);

		// Master Menu

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 5;
		gbc.gridx = 1;
		mastermenu.add(mastercontrol, gbc); // adding buttons to master menu
		gbc.gridy = 6;
		gbc.gridx = 1;
		mastermenu.setBackground(Color.yellow);
		gbc.gridy = 7;
		gbc.gridx = 1;
		mastermenu.add(homemaster, gbc);
		gbc.gridy = 1;
		gbc.gridx = 1;
		JLabel userLabel = new JLabel("User:"); // setting up Log IN
		mastermenu.add(userLabel, gbc);
		gbc.gridy = 2;
		gbc.gridx = 1;
		JTextField userText = new JTextField(20);
		mastermenu.add(userText, gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		JLabel passLabel = new JLabel("Password:");
		mastermenu.add(passLabel, gbc);
		gbc.gridy = 4;
		gbc.gridx = 1;
		JPasswordField passText = new JPasswordField(20);
		mastermenu.add(passText, gbc);

		// Admin View

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.ipadx = 350; 
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		//loginmenu.add(viewowners, gbc); // adding buttons to master menu
		gbc.gridy = 5;
		gbc.gridx = 1;
		loginmenu.setBackground(Color.orange);
		gbc.gridy = 2;
		gbc.gridx = 1;
		//loginmenu.add(viewclients, gbc);
	//	gbc.gridy = 3;
		//gbc.gridx = 1;
		//loginmenu.add(viewjobs, gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		loginmenu.add(calculate, gbc);
		gbc.gridy = 4;
		gbc.gridx = 1;
		loginmenu.add(CalculateResultLabel, gbc);
	
		gbc.gridy = 5;
		gbc.gridx = 1;
		loginmenu.add(CalculateResult, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 1;
		loginmenu.add(submitpass, gbc);

		// Owner Registration
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5; // adding buttons to owner registration menu
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		ownerregistration.add(name, gbc);
		gbc.gridy = 3;
		ownerregistration.add(ownerName, gbc);
		gbc.gridy = 5;
		ownerregistration.add(caramount, gbc);
		gbc.gridy = 6;
		ownerregistration.add(Continue, gbc);
		gbc.gridy = 7;
		ownerregistration.add(ownerreturn, gbc);

		// Owner Profile

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5; // adding buttons to owner registration menu
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 3;
		existingowners.add(owneraddnew, gbc);
		gbc.gridy = 4;
		existingowners.add(removevehicle, gbc);
		gbc.gridy = 5;
		existingowners.add(existingvehicleslist, gbc);
		gbc.gridy = 6;
		existingowners.add(returnownerprofile1, gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5; // adding buttons to owner registration menu
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 3;
		removeownervehicles.add(exmout1, gbc);
		gbc.gridy = 4;
		removeownervehicles.add(exvehicle1, gbc);
		gbc.gridy = 5;
		removeownervehicles.add(finishremoval, gbc);// this needs to be a JOptionPane
		gbc.gridy = 6;
		removeownervehicles.add(returnownerprofile2, gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5; // adding buttons to owner registration menu
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 3;
		existingownervehicles.add(exmout, gbc);
		gbc.gridy = 4;
		existingownervehicles.add(exvehicle, gbc);
		// this needs to be a JOptionPane

		gbc.gridy = 6;

		existingownervehicles.add(returnownerprofile3, gbc);

		gbc.gridy = 5;

		existingownervehicles.add(lookup, gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5; // adding buttons to owner registration menu
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 3;
		vehiclelookup.add(returnownerprofile4, gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 3;
		ownersignin.add(OwnerCode, gbc);
		gbc.gridy = 4;
		ownersignin.add(ownercodetext, gbc);
		gbc.gridy = 5;
		ownersignin.add(signin, gbc);
		gbc.gridy = 6;
		ownersignin.add(returnownerprofile5, gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 3;
		vehicleadd.add(vehicleadding, gbc);

		gbc.gridy = 4;
		vehicleadd.add(vehicleaddingtext, gbc);

		gbc.gridy = 5;
		vehicleadd.add(finishadd, gbc);

		gbc.gridy = 6;
		vehicleadd.add(returnOwnerProfileButton, gbc);

		// Ownercars
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.ipadx = 350;
		gbc.ipady = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		ownercars.add(cars, gbc);
		gbc.gridy = 1;
		ownercars.add(carmodel, gbc); // owner car
		gbc.gridy = 2;
		ownercars.add(carmodeltype, gbc);
		gbc.gridy = 3;
		ownercars.add(vinnum, gbc);
		gbc.gridy = 4;
		ownercars.add(carvinin, gbc);
		gbc.gridy = 5;
		ownercars.add(caryear, gbc);
		gbc.gridy = 6;
		ownercars.add(caryearin, gbc);
		gbc.gridy = 7;
		ownercars.add(date, gbc);
		gbc.gridy = 8;
		ownercars.add(ownerResidencedate, gbc);
		gbc.gridy = 10;
		ownercars.add(add, gbc);
		gbc.gridy = 11;
		ownercars.add(doneButton, gbc);

		container.add(mainmenu, "mainmenu"); // adding panels to the panel container
		container.add(clientmenu, "clientmenu");
		container.add(ownermenu, "ownermenu");
		container.add(mastermenu, "mastermenu");
		container.add(ownercars, "ownercars");
		container.add(ownerregistration, "ownerregistration");
		container.add(loginmenu, "loginmenu");
		container.add(clientregistrationmenu, "clientregistrationmenu");
		container.add(jobdescriptionmenu, "jobdescriptionmenu");
		container.add(jobdescriptionmenuNext, "jobdescriptionmenuNext");
		container.add(existingowners, "existingowners");
		container.add(ownersaddnewvehicles, "ownersaddnewvehicles");
		container.add(removeownervehicles, "removeownervehicles");
		container.add(existingownervehicles, "existingownervehicles");
		container.add(vehiclelookup, "vehiclelookup");
		container.add(vehicleadd, "vehicleadd");
		container.add(ownersignin, "ownersignin");
		container.add(AdminViewJobs, "AdminViewJobs");
		container.add(AdminViewClients, "AdminViewClients");
		container.add(AdminViewOwners, "AdminViewOwners");
		container.add(jobpanel, "jobpanel");
		container.add(AdminCalculate,"AdminCalculate");
		
		
		JTextArea JobNewLine = new JTextArea();
		JPanel jobpanel = new JPanel();
		
		jobpanel.add(JobNewLine);
		
		
		cl.show(container, "mainmenu"); // startup
		this.add(container);

		ScreenChange change = new ScreenChange(); // gives buttons actions through action listener
		closeButton.addActionListener(change);
		clientMenuButton.addActionListener(change);
		ownerMenuButton.addActionListener(change);
		administrationButton.addActionListener(change);
		homeclient.addActionListener(change);
		homeowner.addActionListener(change);
		homemaster.addActionListener(change);
		ownerRegistrationButton.addActionListener(change);
		Continue.addActionListener(change);
		add.addActionListener(change);
		doneButton.addActionListener(change);
		mastercontrol.addActionListener(change);
		homeadmin.addActionListener(change);
		Admin_Back.addActionListener(change);
		submitpass.addActionListener(change);
		clientregistrationreturn.addActionListener(change);
		jobdescriptionreturn.addActionListener(change);
		clientRegistrationButton.addActionListener(change);
		jobSubmissionButton.addActionListener(change);
		createclient.addActionListener(change);
		jobdescriptioncontinue.addActionListener(change);
		clientregistrationreturn2.addActionListener(change);
		jobdescriptioncontinue2.addActionListener(change);
		jobdescriptioncontinue3.addActionListener(change);
		ownerreturn.addActionListener(change);
		owneraddnew.addActionListener(change);
		removevehicle.addActionListener(change);
		existingvehicleslist.addActionListener(change);
		returnownerprofile1.addActionListener(change);
		returnownerprofile2.addActionListener(change);
		returnownerprofile3.addActionListener(change);
		returnownerprofile4.addActionListener(change);
		returnownerprofile5.addActionListener(change);
		returnOwnerProfileButton.addActionListener(change);
		ownerProfileButton.addActionListener(change);
		finishremoval.addActionListener(change);
		lookup.addActionListener(change);
		signin.addActionListener(change);
		finishadd.addActionListener(change);
		viewclients.addActionListener(change); // these should open a text file as a j option pane
		viewjobs.addActionListener(change);
		viewowners.addActionListener(change);
		
		calculate.addActionListener(change);
		
		
		
		validate();
		this.setResizable(true);
		this.setUndecorated(false);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		GraphicsEnvironment display = GraphicsEnvironment.getLocalGraphicsEnvironment(); // sets the frame to
																							// undecorated,makes the
																							// frame not resizable and
																							// makes the frame visible
		defineTooltips();

		GraphicsDevice screen = display.getDefaultScreenDevice();
	DisplayMode screenMode = screen.getDisplayMode();
		int w = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width/2;
		int l = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height/2; 
		DisplayMode displayMode = new DisplayMode(w, l, screenMode.getBitDepth(), screenMode.getRefreshRate());
      
    //   screen.setDisplayMode(displayMode);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen.setFullScreenWindow(this);
		
		this.setVisible(true);
	} 
	
	private void defineTooltips() {
		String defaultDescription = "Hover over a button to see its description.";
		
		ownerMenuButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				mainMenuLabel.setText("Access the owner panel to register a car or view an owner's profile.");
			}

			public void mouseExited(MouseEvent me) {
				mainMenuLabel.setText(defaultDescription);
			}
		});
		
		clientMenuButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				mainMenuLabel.setText("Register your new client or view job submissions.");
			}

			public void mouseExited(MouseEvent me) {
				mainMenuLabel.setText(defaultDescription);
			}
		});
		
		administrationButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				mainMenuLabel.setText("Access the administration panel.");
			}

			public void mouseExited(MouseEvent me) {
				mainMenuLabel.setText(defaultDescription);
			}
		});
		
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				mainMenuLabel.setText("Close the application.");
			}

			public void mouseExited(MouseEvent me) {
				mainMenuLabel.setText(defaultDescription);
			}
		});
		
		ownerRegistrationButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				ownerMenuLabel.setText("Register a new car owner.");
			}

			public void mouseExited(MouseEvent me) {
				ownerMenuLabel.setText(defaultDescription);
			}
		});
		
		ownerProfileButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				ownerMenuLabel.setText("View an owner profile based on code entered.");
			}

			public void mouseExited(MouseEvent me) {
				ownerMenuLabel.setText(defaultDescription);
			}
		});
		
		homeowner.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				ownerMenuLabel.setText("Return to the main menu to access other actions.");
			}

			public void mouseExited(MouseEvent me) {
				ownerMenuLabel.setText(defaultDescription);
			}
		});
		
		clientRegistrationButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				clientMenuLabel.setText("Register a new client.");
			}

			public void mouseExited(MouseEvent me) {
				clientMenuLabel.setText(defaultDescription);
			}
		});
		
		jobSubmissionButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				clientMenuLabel.setText("Access the job submission panel.");
			}

			public void mouseExited(MouseEvent me) {
				clientMenuLabel.setText(defaultDescription);
			}
		});
		
		homeclient.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				clientMenuLabel.setText("Return to the main menu to access other actions.");
			}

			public void mouseExited(MouseEvent me) {
				clientMenuLabel.setText(defaultDescription);
			}
		});
		
	}

	private void clearInputs() {
		caryearin.setText("");
		carvinin.setText("");
		carmodeltype.setText("");
		ownerResidencedate.setText("");
		ownerName.setText("");
		JobResidencedate.setText("");
	}

	private class ScreenChange implements ActionListener // action listener for the various JButtons
	{
		public void actionPerformed(ActionEvent action) {
			if (action.getSource() == closeButton) {
				System.exit(0);
			}
			if (action.getSource() == homeclient) {
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == homeowner) {
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == homemaster) {
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == clientMenuButton) {
				cl.show(container, "clientmenu");
			}
			if (action.getSource() == ownerMenuButton) {
				cl.show(container, "ownermenu");
			}
			if (action.getSource() == administrationButton) {
				cl.show(container, "loginmenu");
			}
			if (action.getSource() == ownerRegistrationButton) {

				cl.show(container, "ownerregistration");
			}
			if (action.getSource() == Continue) {

				namehold = ownerName.toString();
				newowner.setName(namehold);
				owners.add(newowner);
				cl.show(container, "ownercars");
			}
			//Owner List
			
			if (action.getSource() == add) {
				LocalDate now = LocalDate.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
				LocalDate end = LocalDate.parse(ownerResidencedate.getText(), dtf);
				long residency2 = now.until(end, ChronoUnit.DAYS);
				

				LocalDateTime timeNow = LocalDateTime.now();
				ZonedDateTime zonedDateTime = ZonedDateTime.of(timeNow, ZoneId.of("America/New_York"));
				String formattedTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime);
			
				String make2= cars.getSelectedItem().toString();
				String year2= caryearin.getText();
				String vin2= carvinin.getText();
				
	          
	            
				Vehicle vehicle = new Vehicle( make2 , year2 , vin2 , residency2 );
				
				try (Socket s= new Socket("localhost", 8888)){
	     			
	     			String msgin = "";
	     			DataInputStream din = new DataInputStream(s.getInputStream());
	     			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	     			
	     			try {
	        			String msgout = "";
	        			msgout=  "Vehicle "+ "\nTime Submitted "+ formattedTime+"\nOwner Name "+ ownerName.getText() + "\nVin "+ vehicle.getVin()+ "\nYear "+ vehicle.getYear()+ "\nMake "+ vehicle.getMake()+"\nResidency "+ vehicle.getResidency() +"\n"+ "Accept or Reject this Vehicle".trim(); //"ID " + job.getJobId()+ "\nDeadline "+job.getResidency()+ "\nDuration "+job.getDuration()+ "\n"+ "Accept or Reject".trim();
	        			dout.writeUTF(msgout);
	        		}
	        			catch(Exception E) {
	        				E.printStackTrace();
	        			}
	     			
	     			while(!msgin.equals("exit")) {
	     				msgin=din.readUTF().trim();
	     				System.out.println(msgin);
	     				
	     				if (msgin.equals("Accepted")) {
	     					JOptionPane.showMessageDialog(f3, "Vehicle Has Been Accepted");
	     					try {
	     			
	     						String vin =vehicle.getVin();
	     						
	     						String caryear =vehicle.getYear();
	     						String cartype =vehicle.getMake();
	     						long Residency = vehicle.getResidency();
	     						String TimeSubmitted = formattedTime;
	     						String ownername=ownerName.getText();
	     						
	     					//	long Duration = Vehicle.getDuration();
	     						
	     			            //declares a connection to your database
	     			            connection = DriverManager.getConnection(url, username, password);
	     			            //creates an insert query
	     			            String sql = "INSERT INTO vehicleowners" + "(vin , cartype, caryear, Residency, TimeSubmitted, ownername)" + "VALUES (?, ?, ?, ?, ?, ?)";
	     			            //establishes the connection session
	     			            PreparedStatement statement = connection.prepareStatement(sql);
	     			            statement.setString(1, vin);
	     			            statement.setString(2, cartype);
	     			            statement.setString(3, caryear);
	     			            statement.setLong(4, Residency);
	     			            statement.setString(5, TimeSubmitted);
	     			           statement.setString(6, ownername);
	     			        //  statement.setLong(5, Duration);
	     			            
	     			            //executes the query 
	     			            int row = statement.executeUpdate();
	     			            //the return value is the indication of success or failure of the query execution
	     			            if (row > 0)
	     			                System.out.println("Data was inserted!");

	     			            connection.close();

	     			        } catch (SQLException e) {
	     			            e.getMessage();

	     			        }

	        				newowner.addCar(new Vehicle(cars.getSelectedItem().toString(), caryearin.getText(),
	        						carvinin.getText().toLowerCase(), residency2));

	        				String data = "Time of Registration: " + formattedTime + '\n' + "Owner Name: " + ownerName.getText()
	        						+ '\n' + "Car Type: " + cars.getSelectedItem().toString() + "\n" + "Car Year In: "
	        						+ caryearin.getText() + '\n' + "Car VIN: " + carvinin.getText().toLowerCase() + '\n'
	        						+ "Residency: " + residency2 ;
	        				String fileName = "data.txt";
	        				//add your elements
	        				caryearin.setText("");
	        				carvinin.setText("");
	        				carmodeltype.setText("");
	        				ownerResidencedate.setText("");
	        				ownerName.setText("");
	        				JobResidencedate.setText("");

	        				BufferedWriter writer;
	        				try {
	        					writer = new BufferedWriter(new FileWriter(fileName, true));
	        					writer.write(data);
	        					writer.newLine();
	        					writer.newLine();
	        					writer.close();
	        				} catch (IOException e) {
	        					e.printStackTrace();
	        				}
	        				
	        				cl.show(container, "ownercars");

	     					break;
	     				}
	     				
	     				else if (msgin.equals("Rejected")) {
	     					JOptionPane.showMessageDialog(f2, "Job Has Been Rejected");
	        				caryearin.setText("");
	        				carvinin.setText("");
	        				carmodeltype.setText("");
	        				ownerResidencedate.setText("");
	        				ownerName.setText("");
	        				JobResidencedate.setText("");
	     					break;
	     				}
	     				
	     			}
	     			
	     			}
	     			catch (Exception e) {
	     				e.printStackTrace();
	     			}


				
			}
			
			if (action.getSource() == doneButton) {
				namehold = "";
				clearInputs();
				cl.show(container, "mainmenu");
			}
			
			//prints client sign up info to "clientdata" text file 
       	 if(action.getSource()== createclient) {
              String clientdata = "Client Name: " + clientNameText.getText() + '\n' +
                      "Client License #: " + clientLicenseText.getText() + '\n' + "State: " + 
           		   state.getSelectedItem().toString() + '\n' +
                      "Client Zip: " + zipText.getText() + '\n';       
              try {
           	   FileWriter clientStream = new FileWriter("clientdata.txt",true);
           		  BufferedWriter writer = new BufferedWriter(clientStream);         		                         
           	   	writer.write(clientdata);
           	   	writer.close();
           	   	
           	   	
           	   	
           	   	
              } catch (IOException e) {
             e.printStackTrace();
              }
       	 }
			
			//Creating a Job List to display the deadline and connecting the Unique Code of the Client
			//jobdescriptioncontinue2
			//ClientCodeText
       	 
       	 if(action.getSource()== jobdescriptioncontinue2) {
         
       		LocalDate now = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
			LocalDate end = LocalDate.parse(JobResidencedate.getText(), dtf);
			long residency1 = now.until(end, ChronoUnit.DAYS);
			long duration = Long.parseLong(durationText.getText());
       				 
			LocalDateTime timeNow = LocalDateTime.now();
			ZonedDateTime zonedDateTime = ZonedDateTime.of(timeNow, ZoneId.of("America/New_York"));
			String formattedTime1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime);

			String JobId=Client.IDgenerator();
            Job job = new Job(JobId,residency1,duration);
            
         	

         		
         		
         	
     		try (Socket s= new Socket("localhost", 8888)){
     			
     			String msgin = "";
     			
     		
     			
     			
     			DataInputStream din = new DataInputStream(s.getInputStream());
     			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
     			
     			try {
        			String msgout = "";
        			msgout= "Job "+"\nTime Submitted: "+ formattedTime1 +"\nID: " + job.getJobId()+ "\nDeadline: "+job.getResidency()+ "\nDuration: "+job.getDuration()+ "\n"+ "Accept or Reject".trim();
        			dout.writeUTF(msgout);
        		}
        			catch(Exception E) {
        				E.printStackTrace();
        			}
     			
     			while(!msgin.equals("exit")) {
     				msgin=din.readUTF().trim();
     				System.out.println(msgin);
     				
     				
     				if (msgin.equals("Accepted")) {
     				
     					JOptionPane.showMessageDialog(f, "Job Has Been Accepted");
     					VehicleController.registerJob(job);
     					
     					
     					
     					try {
     						
     						String JobID=job.getJobId();
     						long Residency = job.getResidency();
     						long Duration = job.getDuration();
     						String timesubmitted = formattedTime1;
     						
     			            //declares a connection to your database
     			            connection = DriverManager.getConnection(url, username, password);
     			            //creates an insert query
     			            String sql = "INSERT INTO table1" + "(JobID , Residency, Duration, timesubmitted)" + "VALUES (?, ?, ?, ?)";
     			            //establishes the connection session
     			            PreparedStatement statement = connection.prepareStatement(sql);
     			            statement.setString(1, JobID);
     			            statement.setLong(2, Residency);
     			            statement.setLong(3, Duration);
     			            statement.setString(4, timesubmitted);
     			            //executes the query 
     			            int row = statement.executeUpdate();
     			            //the return value is the indication of success or failure of the query execution
     			            
     			            if (row > 0)
     			                System.out.println("Data was inserted!");

     			            connection.close();

     			        } catch (SQLException e) {
     			            e.getMessage();

     			        }
     					JobResidencedate.setText("");
     					ownercodetext.setText("");
        				vehicleaddingtext.setText("");
        				clientCodeText.setText("");
        			
        				durationText.setText("");
        			
         			
         				
     					String string= "\nTime Submitted "+ formattedTime1 + "\nID " + job.getJobId()+ "\nDeadline "+job.getResidency()+ "\nDuration "+job.getDuration();
     				
     					try {
     		           	   FileWriter clientStream = new FileWriter("JobData.txt",true);
     		           		  BufferedWriter writer = new BufferedWriter(clientStream);         		                         
     		           	  // 	writer.write(string);
     		           	   	writer.append(string);
     		           	//   	writer.write(string);
     		           	   	writer.newLine();
     		           	   	writer.close();
     		           	   	
     		              } catch (IOException e) {
     		             e.printStackTrace();
     		              }
     					
     					
     		     
     					break;
     				}
     				
     				else if (msgin.equals("Rejected")) {
     					JOptionPane.showMessageDialog(f1, "Job Has Been Rejected");
     					JobResidencedate.setText("");
     					ownercodetext.setText("");
        				vehicleaddingtext.setText("");
        				clientCodeText.setText("");
        		
        				durationText.setText("");
         			 
     					break;
     				}
     				
     			}
     			
     			}
     			catch (Exception e) {
     				e.printStackTrace();
     			}
	
     		
		
       		
      
      	 }
			
       
       	
       	 

			if (action.getSource() == mastercontrol) // this is where the password authenticator needs to be
			{
				cl.show(container, "loginmenu");
			}
			if (action.getSource() == Admin_Back) {
				cl.show(container, "mastermenu");
			}
			if (action.getSource() == submitpass) {
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == clientRegistrationButton) {
				cl.show(container, "clientregistrationmenu");
			}
			if (action.getSource() == clientregistrationreturn) {
				cl.show(container, "clientmenu");
			}
			if (action.getSource() == jobSubmissionButton) {
				cl.show(container, "jobdescriptionmenu");
			}
			if (action.getSource() == jobdescriptionreturn) {
				cl.show(container, "clientmenu");
			}
			if (action.getSource() == createclient) {
				JOptionPane.showMessageDialog(clientregistrationmenu,
						clientLicenseText.getText() + state.getSelectedItem());
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == clientregistrationreturn2) {
				cl.show(container, "clientmenu");
			}
			if (action.getSource() == jobdescriptioncontinue) {
				cl.show(container, "jobdescriptionmenuNext");
			}
	
			if (action.getSource() == jobdescriptioncontinue3) { //ok button in job registration
				
				cl.show(container, "clientmenu");
			}
			
			
			if (action.getSource() == ownerreturn) {
				cl.show(container, "ownermenu");
			}
			if (action.getSource() == ownerreturn) {
				cl.show(container, "ownermenu");
			}
			if (action.getSource() == ownerProfileButton) {
				cl.show(container, "ownersignin");
			}
			if (action.getSource() == signin) // this is where you should put the sign in button
			{
				cl.show(container, "existingowners");
			}
			if (action.getSource() == returnownerprofile5) // this is where you should put the sign in button
			{
				cl.show(container, "ownermenu");
			}
			if (action.getSource() == returnownerprofile1) {
				cl.show(container, "mainmenu");
			}
			if (action.getSource() == removevehicle) {
				cl.show(container, "removeownervehicles");
			}
			if (action.getSource() == returnownerprofile2) {
				cl.show(container, "existingowners");
			}
			if (action.getSource() == finishremoval) {
				JOptionPane.showMessageDialog(removeownervehicles, "Removal Complete");
				cl.show(container, "existingowners");
			}
			if (action.getSource() == existingvehicleslist) {
				cl.show(container, "existingownervehicles");
			}
			if (action.getSource() == returnownerprofile3) {
				cl.show(container, "existingowners");
			}
			if (action.getSource() == lookup) {
				cl.show(container, "vehiclelookup");
			}
			if (action.getSource() == returnownerprofile4) {
				cl.show(container, "existingowners");
			}
			if (action.getSource() == owneraddnew) {
				cl.show(container, "vehicleadd");
			}
			if (action.getSource() == returnOwnerProfileButton) {
				cl.show(container, "existingowners");
			}
			if (action.getSource() == finishadd) {
				JOptionPane.showMessageDialog(vehicleadd, "Vehicle Added");
				cl.show(container, "existingowners");
			}
			
			if (action.getSource() == calculate) {
				for(Job b: VehicleController.getJobQueue()) {
				String a = b.getJobId().toString() +" "+ VehicleController.getCompletionTime(b.getJobId());
				
						CalculateResult.append(a);
						CalculateResult.append("\n");

				}
	
				
			}

		}

	
	}
	}

