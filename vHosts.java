package vHostsAdd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.io.*;

public class vHosts {

	private JFrame frame;
	private JTextField txtName;
	private File filevHosts;
	private File fileHosts;
	private File dir;
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vHosts window = new vHosts();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vHosts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 73);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnvHosts = new JButton("vHosts");
		btnvHosts.setHorizontalAlignment(SwingConstants.LEFT);
		btnvHosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser vHosts = new JFileChooser();
		        vHosts.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnVal = vHosts.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            filevHosts=vHosts.getSelectedFile();
		        }
			}
		});
		
		txtName = new JTextField();
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnProject = new JButton("Project");
		btnProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser project = new JFileChooser();
				project.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int returnVal = project.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            dir=project.getCurrentDirectory();
		        }
			}
		});
		
		
		JButton btnHosts = new JButton("Hosts");
		btnHosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser hosts = new JFileChooser();
				hosts.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnVal = hosts.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            fileHosts=hosts.getSelectedFile();
		        }
			}
		});
		
		JButton btnOK = new JButton("Confirm");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( filevHosts.exists() && fileHosts.exists() && dir.exists()){ 
					try{
						FileWriter fwVH = new FileWriter(filevHosts.getAbsoluteFile(),true);
						FileWriter fwH = new FileWriter(fileHosts.getAbsoluteFile(),true);
						BufferedWriter bwVH = new BufferedWriter(fwVH);
						BufferedWriter bwH = new BufferedWriter(fwH);
						bwVH.write("\n<VirtualHost *:80>\n\tDocumentRoot \""+dir.getAbsolutePath()+"\"\n\tServerName "+txtName.getText()+"\n</VirtualHost>");
						bwH.write("\n127.0.0.1 "+txtName.getText());
						bwVH.close();
						bwH.close();
						fwVH.close();
						fwH.close();
						System.exit(0);
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		frame.getContentPane().add(btnHosts);
		frame.getContentPane().add(btnvHosts);
		frame.getContentPane().add(btnProject);
		frame.getContentPane().add(btnOK);
	}

}
