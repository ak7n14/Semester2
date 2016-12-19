import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class ViewFiles {
	public static void main(String[] args) {
		ViewFilesFrame window = new ViewFilesFrame();
	}
}
//Creates class frame
class ViewFilesFrame extends JFrame {
	private static final long serialVersionUID = -7944719375764988214L;
	private JTree fileTree;
	private FileSystemModel fileSystemModel;
	private JTextArea fileContent = new JTextArea();

	public ViewFilesFrame() {
		super("Local file system viewer");
		fileSystemModel = new FileSystemModel(new File("."));
		fileTree = new JTree(fileSystemModel);
		//Tree listener
		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				File file = (File) fileTree.getLastSelectedPathComponent();
				getFileContent(file);
			}
		});
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, new JScrollPane(fileTree), new JScrollPane(fileContent));
		getContentPane().add(splitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}
//Reads from file
	private void getFileContent(File file) {
		fileContent.setText("");
		if(!file.isDirectory()){
			String fileLine;
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
				while((fileLine = reader.readLine()) != null){
					fileContent.append(fileLine+"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class FileSystemModel implements TreeModel {
	private File rootDirectory;
	private ArrayList<TreeModelListener> treeListeners = new ArrayList<TreeModelListener>();

	public FileSystemModel(File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public File getRoot() {
		return rootDirectory;
	}

	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] children = directory.list();
		return new TreeFile(directory, children[index]);
	}

	public int getChildCount(Object parent) {
		File file = (File) parent;
		if (file.isDirectory()) {
			String[] fileList = file.list();
			if (fileList != null)
				return file.list().length;
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		File file = (File) node;
		return file.isFile();
	}

	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File file = (File) child;
		String[] children = directory.list();
		for (int i = 0; i < children.length; i++) {
			if (file.getName().equals(children[i])) {
				return i;
			}
		}
		return -1;
	}

	public void valueForPathChanged(TreePath path, Object value) {
		File oldFile = (File) path.getLastPathComponent();
		String fileParentPath = oldFile.getParent();
		String newFileName = (String) value;
		File targetFile = new File(fileParentPath, newFileName);
		oldFile.renameTo(targetFile);
		File parent = new File(fileParentPath);
		int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
		Object[] changedChildren = { targetFile };
		fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices,
				changedChildren);

	}

	private void fireTreeNodesChanged(TreePath parentPath, int[] indices,Object[] children) {
		TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
		
		for(TreeModelListener treeListener: treeListeners)
			treeListener.treeNodesChanged(event);
	}

	public void addTreeModelListener(TreeModelListener listener) {
		treeListeners.add(listener);
	}

	public void removeTreeModelListener(TreeModelListener listener) {
		treeListeners.remove(listener);
	}

	private class TreeFile extends File {
		public TreeFile(File parent, String child) {
			super(parent, child);
		}

		public String toString() {
			return getName();
		}
	}
}