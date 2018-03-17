package vHostsAdd;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	 * @throws IOException 
	 */
	public vHosts() throws IOException {
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
		            dir=project.getSelectedFile();
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
				try{
					if(filevHosts!=null && fileHosts!=null) {
						FileWriter fwConfig = new FileWriter(new File("config.txt"));
						BufferedWriter bwConfig = new BufferedWriter(fwConfig);
						bwConfig.write(filevHosts.getAbsoluteFile()+"\n"+fileHosts.getAbsoluteFile());
						bwConfig.close();
						fwConfig.close();
					}
					String linevHosts = Files.readAllLines(Paths.get("config.txt")).get(0);
					String lineHosts = Files.readAllLines(Paths.get("config.txt")).get(1);
					FileWriter fwVH = new FileWriter(new File(linevHosts),true);
					FileWriter fwH = new FileWriter(new File(lineHosts),true);
					BufferedWriter bwVH = new BufferedWriter(fwVH);
					BufferedWriter bwH = new BufferedWriter(fwH);
			
					bwVH.write("\n<VirtualHost *:80>\n\tDocumentRoot \""+dir.toString()+"\"\n\tServerName "+txtName.getText()+"\n</VirtualHost>\n");
					bwH.write("\n127.0.0.1 "+txtName.getText()+"\n");
					bwVH.close();
					bwH.close();
					fwVH.close();
					fwH.close();
					
					System.exit(0);
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		frame.getContentPane().add(btnHosts);
		frame.getContentPane().add(btnvHosts);
		frame.getContentPane().add(btnProject);
		frame.getContentPane().add(btnOK);
	}

}
