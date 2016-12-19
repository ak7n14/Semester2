/*
 * Author @Anish Katariya
 * This Programme creates Mouse Listener that adds and stores the
 * Favorite location of the user when the user clicks on it
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

class FavouriteListener implements MouseListener {
	private JComboBox<String> list;
	private ArrayList<favourite> favlist;
	private JSPanel panel;
	
	//initializing vales
	public FavouriteListener(JComboBox<String> list, ArrayList<favourite> favlist, JSPanel panel) {
		this.list = list;
		this.favlist = favlist;
		this.panel = panel;

	}
	//Adding values to DropDown menu and storing them in an array.
	public void mouseClicked(MouseEvent e) {
		favourite temp = new favourite(e.getX(), e.getY(), panel);
		favlist.add(temp);
		list.addItem(temp.getFavouriteNumber());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}