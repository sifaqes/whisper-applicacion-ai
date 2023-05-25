package interfaces;


import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import helper.*;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class application_manager extends JFrame {

	/**
	 * 
	 */
	private static application_manager frame ;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static boolean inizialization() {
		try {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						int[] resulution = functions.get_resulution(0.5,0.5);
						frame = new application_manager();
						frame.setTitle("whisher application");
				        frame.setSize(resulution[0], resulution[1]);
						frame.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,e);
					}
				}
			});
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	/**
	 * Create the frame.
	 */
	private JLabel lblNewLabel;
	private static JFileChooser fileChooser;
	private Button button;
	
	private JLabel url_audio;
	private JLabel guidance;
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	
	public application_manager() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new Button("Upload Audio");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				get_audio();
			}
		});
		button.setBounds(10, 31, 119, 45);
		contentPane.add(button);
		
	    lblNewLabel = new JLabel("mp3 max 25 mb");
		lblNewLabel.setBounds(20, 11, 106, 14);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(135, 11, 588, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		url_audio = new JLabel("URL");
		url_audio.setBounds(10, 11, 568, 14);
		panel.add(url_audio);
		
		guidance = new JLabel("Guidance");
		guidance.setBounds(10, 36, 568, 14);
		panel.add(guidance);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 84, 713, 166);
		contentPane.add(panel_1);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
	}
	
	
	private void get_audio() {
		fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos MP3", "mp3");
        fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(new JFrame());
		if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            long size = helper.functions.get_size(selectedFile.getAbsolutePath());
            
            url_audio.setText("size: "+ size +" bytes, path: "+selectedFile.getAbsolutePath());
            
            whisher_api.send_request(selectedFile.getAbsolutePath());
            
        }
	}



}
