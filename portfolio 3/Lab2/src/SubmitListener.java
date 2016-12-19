import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class SubmitListener implements ActionListener{
		JCheckBox bold;
		JCheckBox italic;
		JComboBox<String> fontsList;
		JTextField text;
		JTextField size;
		
		
		public SubmitListener(JCheckBox bold,JCheckBox italic,JComboBox<String> fontsList,JTextField text,JTextField size){
			this.bold=bold;
			this.italic=italic;
			this.fontsList=fontsList;
			this.text=text;
			this.size=size;
		}
		public void actionPerformed(ActionEvent e) {
			Font font;
			if(bold.isSelected()&&italic.isSelected()){
				font= new Font(String.valueOf(fontsList.getSelectedItem()),Font.BOLD+Font.ITALIC,Integer.valueOf(size.getText()));

			}
			else if (bold.isSelected()){
				font= new Font(String.valueOf(fontsList.getSelectedItem()),Font.BOLD,Integer.valueOf(size.getText()));
			}
			else if (italic.isSelected()){
				font= new Font(String.valueOf(fontsList.getSelectedItem()),Font.ITALIC,Integer.valueOf(size.getText()));
			}
			else{
				font= new Font(String.valueOf(fontsList.getSelectedItem()),Font.PLAIN,Integer.valueOf(size.getText()));
			}
			text.setText(text.getText());
			text.setFont(font);
		}
		
}

