package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vues.VueConsulterClassement;

public class ControleurConsulterClassement implements ActionListener {

	private VueConsulterClassement vue;

	public ControleurConsulterClassement(VueConsulterClassement vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton button = (JButton) source;
			if (button.getText().equals("Fermer")) {
				this.vue.dispose();
			}
		}
	}
	
}
