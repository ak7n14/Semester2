
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;



//Creating AuctionList
public class AuctionList extends JPanel  implements ActionListener, TableModelListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3791199483881776660L;
	MainPanel main;
	ArrayList<Item> items = new ArrayList<Item>();
	JTable table;
	private JButton getValue;



	public AuctionList(MainPanel main) {	
		this.main=main;
		// data to fill the tables
		Comms.sendMessage(new RequestAllAuctions());
		this.items= ((GetAllAuctions) Comms.receiveMessage()).getItemsList();

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		table = new JTable(new AuctionsTable());
		table.setFillsViewportHeight(true);

		//Create the scroll pane for table
		JScrollPane scrollPane = new JScrollPane(table);

		//Add the scroll pane to this panel.
		add(scrollPane);

		getValue = new JButton( "View Item" );


		getValue.addActionListener( this );


		this.add(getValue);



		init();



	}

	public void actionPerformed( ActionEvent e ) {
		int row = table.getSelectedRow();
		
		if ( e.getSource() == getValue ) {
			String itemName = String.valueOf( table.getValueAt(row,0) );
			String UserID = String.valueOf(table.getValueAt(row, 6));
			String itemID = String.valueOf(table.getValueAt(row, 7));
			System.out.println("Value at (" + row + "," + 0 + ") is " + "\'" + itemName);
		
		//Make a request to the server to receive a data of item using itemID
		RequestItemInfo requestItemInfo = new RequestItemInfo(Integer.parseInt(itemID));	
		Comms.sendMessage(requestItemInfo);
		SendItemInfo sendItemInfo= ((SendItemInfo) Comms.receiveMessage());
		
			
	//	if(answer)
		main.changePain(new ViewItemGUI(main, sendItemInfo.getItem(), sendItemInfo.getRelevantBids()));
			
		}
	}


	public void init(){
		this.setOpaque(true);
		if(Main.debug) System.out.print("The number of items displayed is: "+items.size()+"<<! ");
		
	}



	@SuppressWarnings("serial")
	class AuctionsTable extends AbstractTableModel{

		private String[] columnNames = {"Name",
				"Category",
				"Price",
				"Bid",
				"Start",
				"End",
				"Owner",
		"ItemID"};
		private Object[][] data = {
				{"Anish", "Fraser",
					"Piano", new Integer(6), new Boolean(false), "Jay",
				"Paino"},
				{"John", "Lara",
					"Snooker", new Integer(3), new Boolean(true), "Jasom",
				"Snoker"},
				{"Radhika", "Katariya",
					"Knitting", new Integer(2), new Boolean(false), "Anish",
				"Knitting"},
		};
		//Initializing Auction Table
		public AuctionsTable() {
			init();
		}

		public void init(){
			//first get the data to fill the tables
			Comms.sendMessage(new RequestAllAuctions());
			items= ((GetAllAuctions) Comms.receiveMessage()).getItemsList();

			Object[][] object = new Object[items.size()][columnNames.length];
			for(int i=0; i<items.size(); i++){
				Object[] tmp =	{items.get(i).getTitle(), items.get(i).getkeyWord(),items.get(i).getReservedPrice(), "Bids info", items.get(i).getStartTime(), items.get(i).getEndTime(),items.get(i).getUserID(), items.get(i).getItemID()};
				object[i]=  tmp;
			}

			setData(object); //filling table
		}


		//setters and getters
		public String[] getColumnNames() {
			return columnNames;
		}

		public void setColumnNames(String[] columnNames) {
			this.columnNames = columnNames;
		}

		public Object[][] getData() {
			return data;
		}

		public void setData(Object[][] data) {
			this.data = data;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}






	}



	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}

}