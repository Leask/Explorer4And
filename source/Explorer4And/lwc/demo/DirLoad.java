package lwc.demo;

import java.io.File;

import lwc.data.*;
import lwc.ui.*;
import lwc.ui.notab.*;

public class DirLoad implements DirTreeListener{
	private DirTreeItem rootitem;
	protected TreeModel treeModel;
	protected DirTree tree;
	private ScrollPan sp;
	
	protected File root;
	protected boolean onlyfolder;
	
	public DirLoad(boolean onlyfolder) {
		this(Global.DirTre_Txt_InitDir,onlyfolder);
	}
	
	public DirLoad(String rootdir,boolean onlyfolder) {
		super();
		this.onlyfolder=onlyfolder;
		root = new File(rootdir);
		Init();
	}
	
	public DirLoad(File root,boolean onlyfolder) {
		super();
		this.onlyfolder=onlyfolder;
		this.root = root;
		Init();
	}

	protected void Init() {
		rootitem = new DirTreeItem(TreeNT.TYP_Root,root.getName().length()==0?Global.DirTre_Txt_RootName:root.getAbsolutePath(),root.getAbsolutePath(),!root.canWrite(),root.isHidden());
		treeModel = new TreeModelImpl(rootitem);
		tree = new DirTree(this,treeModel,onlyfolder);
		//tree.setBackground(lwc.port.Color.white);
	    sp = new ScrollPan(tree);
	    tree.Load(rootitem);
		tree.select(rootitem);
	}
	
	public ScrollPan GetSp() {
		return sp;
	}
	
	public DirTree GetTree() {
		return tree;
	}

	public void requestFocus() {
		tree.requestFocus();
	}
	
	public void select(DirTreeItem ti) {
	}
}
