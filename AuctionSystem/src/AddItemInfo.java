import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class AddItemInfo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1622872870640859823L;
	
	//Item Details
	
	
	JLabel itemName;
	JTextField itemNameText;
	JLabel keyWord;
	JComboBox<String> keyWordBox;
	JLabel startAuction;
	JTextField startText;
	JSpinner startText1;
	JSpinner endText1;
	JLabel endAuction;
	JTextField endText;
	JLabel description;
	JTextField descriptionText;
	JLabel reservedPrice;
	JTextField reservedPriceText;
	JButton submit;
	MainPanel main;


	public AddItemInfo(MainPanel main) {
		//initializes GUI and Frame
		go();
		this.main=main;
	}

	public void go(){
		// Adds space to make gui more presentable
		Border eb = new EmptyBorder(100,100,100,100);
		this.setBorder(eb);//sets border

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constrains = new GridBagConstraints();
		this.setLayout(gridbag);
		//Initializing Labels and background information
		itemName = new JLabel("Item Name");
		itemNameText = new JTextField(20);
		keyWord = new JLabel("keyWord");
		String[] keyWordStrings = { "arts", "clothes", "antiquites", "furniture", "other" };
		keyWordBox = new JComboBox<String>(keyWordStrings);
		startAuction=new JLabel("Start Date (h:mm a MM/dd/yy)");
		startText = new JTextField(20);
		SpinnerDateModel model1 = new SpinnerDateModel();
		model1.setCalendarField(Calendar.MINUTE);
		//Spinner to submit start details
		startText1= new JSpinner();
		startText1.setModel(model1);
		startText1.setEditor(new JSpinner.DateEditor(startText1,"h:mm a MM/dd/yy"));
		endAuction=new JLabel("End Date (h:mm a MM/dd/yy)");
		endText = new JTextField(20);
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		//spinner to submit end details
		endText1= new JSpinner();
		endText1.setModel(model);
		endText1.setEditor(new JSpinner.DateEditor(endText1,"h:mm a MM/dd/yy"));
		reservedPrice = new JLabel("Reserved Price: ");
		reservedPriceText = new JTextField(20);
		description=new JLabel("Description");
		descriptionText = new JTextField(20);
		submit = new JButton("Submit");

		//Adding components to the panel
		addComponent(itemName, gridbag, constrains, 0,0);
		addComponent(itemNameText, gridbag, constrains, 0,1);
		addComponent(keyWord, gridbag, constrains, 1,0);
		addComponent(keyWordBox, gridbag, constrains, 1,1);
		addComponent(startAuction, gridbag, constrains, 2,0);
		addComponent(startText1, gridbag, constrains, 2,1);
		addComponent(endAuction, gridbag, constrains, 3,0);
		addComponent(endText1, gridbag, constrains, 3,1);
		addComponent(reservedPrice, gridbag, constrains, 4,0);
		addComponent(reservedPriceText, gridbag, constrains, 4,1);
		addComponent(description, gridbag, constrains, 5,0); 
		constrains.ipady=40;
		constrains.gridwidth = 2;
		addComponent(descriptionText, gridbag, constrains, 5,1); constrains.ipady=0; constrains.gridwidth=0;
		addComponent(submit, gridbag, constrains, 6,1);

		//Adding listeners
		ButtonListener buttonlistener= new ButtonListener();
		submit.addActionListener(buttonlistener);


	}



	//add component to the grid layout
	public void addComponent(Component com, GridBagLayout gridbag, GridBagConstraints c, int row, int colum){
		c.gridx=colum;
		c.gridy=row;
		
		
		gridbag.setConstraints(com, c);
		add(com);
	}

	//button listener
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Submit")){
				
					System.out.println("Pressed Submit"+(String) keyWordBox.getSelectedItem());

				Item item = new Item(Client.getUserID(), itemNameText.getText(), Integer.parseInt(reservedPriceText.getText()), (String) keyWordBox.getSelectedItem(), startText1.getValue().toString(), endText1.getValue().toString(),descriptionText.getText());
				AddItemMessage aim = new AddItemMessage(item);
				Comms.sendMessage(aim);
				Boolean addedSuccesfully = (Boolean) ((BooleanMessage)Comms.receiveMessage()).getBoolean();

				System.out.println(addedSuccesfully);


			}

		}
	}


}
