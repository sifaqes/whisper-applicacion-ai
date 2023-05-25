package api.whisper.whisper;

import java.awt.Dialog;

import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import interfaces.application_manager;

@SpringBootApplication
public class WhisperApplication {

	public static void main(String[] args) {
		
		
		if(application_manager.inizialization())
			{
				SpringApplication.run(WhisperApplication.class, args);
			}else{
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			};
	}

}
