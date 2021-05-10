import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ClientChat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	static String msgin = "";
	
	Scanner kb;
	static String jobInfo="";
	
	private static JFrame displayFrame=new JFrame();
	private static JPanel contentPane1=new JPanel();
	

public static void main(String[] args)  {
		
		EventQueue.invokeLater(new Runnable() {
		
		public void run() {
				try {
					 displayFrame.setVisible(true);
					 displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					displayFrame.setBounds(500,500,500,500);
					
					
				}
	catch(Exception e){
		e.printStackTrace();
	}
			
		}
			});

	
	// public static String Display(Job job) throws IOException{
		// String JobData = job.getResidency() + ","+job.getDeadline()+","+job.getJobId();     


		 displayFrame.setVisible(true);
		 displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		displayFrame.setBounds(500,500,500,500);
		
		
		contentPane1.setBorder(new EmptyBorder(5,5,5,5));
		displayFrame.add(contentPane1);
		contentPane1.doLayout();
		displayFrame.validate();
		GridBagConstraints gbc1= new GridBagConstraints();
		JTextField clientinput=new JTextField();
		gbc1.gridy = 1;
		gbc1.gridx = 1;
		gbc1.ipadx = 350;
		gbc1.ipady = 5;
		gbc1.insets = new Insets(10, 10, 10, 10);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.anchor = GridBagConstraints.CENTER;
		contentPane1.add(clientinput, gbc1);
	
		
		JButton clientsend=new JButton("Send");
		clientsend.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)	{
			try {
			String msgout = "";
			msgout= jobInfo + "\n"+ "Accept or Reject this Job".trim();
			dout.writeUTF(msgout);
		}
			catch(Exception E) {
				E.printStackTrace();
			}
		}
		});
		gbc1.gridy = 7;
		gbc1.gridx = 1;
		gbc1.ipadx = 350;
		gbc1.ipady = 5;
		gbc1.insets = new Insets(10, 10, 10, 10);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.anchor = GridBagConstraints.CENTER;
		contentPane1.add(clientsend, gbc1);
	
		
		JTextField msgarea=new JTextField();
		
		contentPane1.add(msgarea,gbc1);
		
		try (Socket s= new Socket("localhost", 8888)){
			msgarea.setText("Sending Job Application to Server "+ "\n"+"JobData");
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while(!msgin.equals("exit")) {
				msgin=din.readUTF().trim();
				msgarea.setText(msgin);
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
		//return msgin;
	// }
	 

	public static Socket getS() {
		return s;
	}
	public static void setS(Socket s) {
		ClientChat.s = s;
	}
	
	public static DataInputStream getDin() {
		return din;
	}
	
	public static void setDin(DataInputStream din) {
		ClientChat.din = din;
	}
	public static DataOutputStream getDout() {
		return dout;
	}
	public static void setDout(DataOutputStream dout) {
		ClientChat.dout = dout;
	}
	
	
		}

