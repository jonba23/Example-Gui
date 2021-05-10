import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class VCC_Server implements Serializable{

	
	 
	private static final long serialVersionUID = 1L;
	private static JFrame serverdisplay= new JFrame();
	private static JPanel contentPane=new JPanel();
	private static JTextField msg_text=new JTextField();
	private JTextField msg_field=new JTextField();
	private static String msgin="";
	
	static ServerSocket ss=null;
	static Socket s=null;
	static DataInputStream din=null;
	static DataOutputStream dout=null;
	
	
	
	public static void main(String[] args)  {
		
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
				try {
					
					serverdisplay.setVisible(true);
					serverdisplay.setBounds(100, 100, 450, 300);
				
				}
				finally {
					if(ss!=null)
						try {
							ss.close();
						}
					catch(IOException e) {
						e.printStackTrace();
					}
}

		}
		
	});
	
	

	serverdisplay.setVisible(true);
	serverdisplay.setBounds(500,500,500,500);
	serverdisplay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	serverdisplay.add(contentPane);
	contentPane.doLayout();
	GridBagConstraints gbc= new GridBagConstraints();
	msg_text.setBounds(10,216,295,34); 
	msg_text.setColumns(10);
	JTextArea msg_Area=new JTextArea(10,30);
	gbc.gridy = 5;
	gbc.gridx = 1;
	gbc.ipadx = 350;
	gbc.ipady = 5;
	gbc.insets = new Insets(10, 10, 10, 10);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.CENTER;
	contentPane.add(msg_Area, gbc);

	
	msg_Area.setColumns(10);
	
	
	JButton Job_Accept=new JButton("Accept");
	Job_Accept.addActionListener(new ActionListener( ) {
	public void actionPerformed(ActionEvent e) {
	    String confirm="Message Sent";
	    msg_Area.setText(confirm);
	    try {
	        String msgout="Accepted".trim();
	        dout.writeUTF(msgout);
	    }
	    catch(Exception E) {
	        E.printStackTrace();
	    }
	    }
	});
	gbc.gridy = 1;
	gbc.gridx = 1;
	gbc.ipadx = 350;
	gbc.ipady = 5;
	gbc.insets = new Insets(10, 10, 10, 10);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.CENTER;
	contentPane.add(Job_Accept, gbc);

	JButton Job_Deny=new JButton("Reject");
	Job_Deny.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String confirm="Message Sent".trim();
			msg_Area.setText(confirm);
			try {
				String msgout="Rejected".trim();
				dout.writeUTF(msgout);
			}
			catch(Exception E) {
				E.printStackTrace();
			}
		}
	});
	gbc.gridy = 1;
	gbc.gridx = 2;
	gbc.ipadx = 350;
	gbc.ipady = 5;
	gbc.insets = new Insets(10, 10, 10, 10);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.CENTER;
	contentPane.add(Job_Deny, gbc);

	
	
	 
	 
		try (ServerSocket ss= new ServerSocket(8888)){
		
			
			while(!msgin.equals("exit")) { //server
				s = ss.accept(); 
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				//server will accept connection
				msgin=din.readUTF().trim();
				msg_Area.setText(msgin);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				
	
	}
}

