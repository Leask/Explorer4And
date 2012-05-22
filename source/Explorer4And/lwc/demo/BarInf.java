package lwc.demo;

import lwc.port.Graphics;
import lwc.ui.*;
import lwc.ui.action.Action;
import lwc.ui.action.ActionCheckbox;
import lwc.ui.action.ActionLabel;
import lwc.ui.event.ActionListener;

public class BarInf extends Panel implements ActionListener {
	private static StatusBar statusbar;
    private static MenuBar menubar;
    private static SpeedButton menubtn;
    private static Menu menu,menuFile,menuEdit,menuView;
	
	public BarInf(Label inflbl) {
		setPSSize(-1, 20);
		//getViewMan(true).setBorder("br.raised");
		initStatusBar(inflbl);
		initMenuBar();
		setLayout(new RasterLayout());//BorderLayout());
		statusbar.setLocation(64, 1);
		statusbar.setSize(Toolkit.disw-64, 18);
		add(statusbar);//BorderLayout.CENTER, statusbar);
		menubar.setLocation(8, 1);
		menubar.setSize(52, 18);
		add(menubar);//BorderLayout.WEST, menubar);
	}
	   
	private void initStatusBar(Label inflbl) {
		statusbar = new StatusBar(0);
		//statusbar.setInsets(1, 1, 1, 1);
		statusbar.add(new Integer(55), inflbl);
        android.os.StatFs fs=new android.os.StatFs("/system");
        statusbar.add(new Integer(50), new Label(Global.BarInf_Txt_Total+" "+Global.GetFileSize(fs.getBlockCount()*fs.getBlockSize())+
        		" / "+Global.BarInf_Txt_Used+" "+Global.GetFileSize(fs.getFreeBlocks()*fs.getBlockSize())));
	}

	private void initMenuBar()
	{
		menubar = new MenuBar();
		menubar.setOpaque(false);
		menubar.setInsets(0,0,0,0);
		menubar.setView(MenuBar.OFF_BR_VIEW, Toolkit.getView("br.none"));
		menubar.setView(MenuBar.ON_BR_VIEW, Toolkit.getView("br.none"));
	    
		AdvViewMan man = new AdvViewMan();
		man.put("st.over", new ImgSetRender(Toolkit.getImage(Integer.toString(R.drawable.menu)),0,0,47,16,3));
		man.put("st.out", new ImgSetRender(Toolkit.getImage(Integer.toString(R.drawable.menu)),0,0,47,16,3));
		man.put("st.pressed", new ImgSetRender(Toolkit.getImage(Integer.toString(R.drawable.menu)),0,0,47,16,3));
		menubtn=new SpeedButton();//Toolkit.genImgLbl(Integer.toString(R.drawable.run), Global.BarInf_Txt_Menu, Toolkit.HORIZONTAL));
		menubtn.setOpaque(false);
		menubtn.setViewMan(man);
		//menubtn.setPSSize(56, Toolkit.Int_Itm_H);

		menu=new Menu(Global.Menu);
		for (int i=0; i<Global.Menu_Tit.length; i++)
			menu.addItem(new ActionLabel(Global.Menu_Tit[i]), new ImgCanvas(Toolkit.getImage(Integer.toString(Global.Menu_Rid[i]))));
		//menu.getViewMan(true).setView(new ImgSetRender(Graphics.alfImage(Toolkit.getImage(Integer.toString(R.drawable.bg_aurora_borealis)),3), 0, 0, 50, 50,ImgRender.STRETCH));
		menubar.add (menubtn, menu);
		menubtn.addActionListener(this);

		menuFile = new Menu(Global.Menu_File);
		for (int i=0; i<Global.Menu_File_Tit.length; i++)
			if (Global.Menu_File_Tit[i].equalsIgnoreCase("|"))
				menuFile.addItem(new Line(Toolkit.HORIZONTAL|Border.SUNKEN2), true);
			else
				menuFile.addItem(new ActionLabel(Global.Menu_File_Tit[i], Global.Menu_File_+i), new ImgCanvas(Toolkit.getImage(Integer.toString(Global.Menu_File_Rid[i]))));
		//menuFile.getViewMan(true).setView(new ImgSetRender(Graphics.alfImage(Toolkit.getImage(Integer.toString(R.drawable.bg_aurora_borealis)),3), 0, 0, 50, 50,ImgRender.STRETCH));
		menu.setSubMenu(Global.Menu_File, menuFile);

		menuEdit = new Menu(Global.Menu_Edit);
		for (int i=0; i<Global.Menu_Edit_Tit.length; i++)
			if (Global.Menu_Edit_Tit[i].equalsIgnoreCase("|"))
				menuEdit.addItem(new Line(Toolkit.HORIZONTAL|Border.SUNKEN2), true);
			else
				menuEdit.addItem(new ActionLabel(Global.Menu_Edit_Tit[i]), new ImgCanvas(Toolkit.getImage(Integer.toString(Global.Menu_Edit_Rid[i]))));
		//menuEdit.getViewMan(true).setView(new ImgSetRender(Graphics.alfImage(Toolkit.getImage(Integer.toString(R.drawable.bg_aurora_borealis)),3), 0, 0, 50, 50,ImgRender.STRETCH));
		menu.setSubMenu(Global.Menu_Edit, menuEdit);

		menuView = new Menu(Global.Menu_View);
	    Group gSort = new Group();
		for (int i=0; i<Global.Menu_View_Tit.length; i++)
			if (Global.Menu_View_Tit[i].equalsIgnoreCase("|"))
				menuView.addItem(new Line(Toolkit.HORIZONTAL|Border.SUNKEN2), true);
			else if (i==Global.Menu_View_SortName || i==Global.Menu_View_SortSize || i==Global.Menu_View_SortType || i==Global.Menu_View_SortDate) {
				menuView.addItem (new ActionCheckbox(Global.Menu_View_Tit[i], Checkbox.RADIO));
			    ((Checkbox)menuView.fetchContentComp(i)).setSwitchManager(gSort);
			}
			else
				menuView.addItem(new ActionLabel(Global.Menu_View_Tit[i]), new ImgCanvas(Toolkit.getImage(Integer.toString(Global.Menu_View_Rid[i]))));
		//menuView.getViewMan(true).setView(new ImgSetRender(Graphics.alfImage(Toolkit.getImage(Integer.toString(R.drawable.bg_aurora_borealis)),3), 0, 0, 50, 50,ImgRender.STRETCH));
		menu.setSubMenu(Global.Menu_View, menuView);
		gSort.setSelected((Checkbox)menuView.fetchContentComp(Global.Menu_View_SortName));

		menu.addSelectionListener(this);
		menuFile.addSelectionListener(this);
		menuEdit.addSelectionListener(this);
		menuView.addSelectionListener(this);
	}

	public void actionPerformed(Object src, Object data) {
		if (src == menubtn)
			menubar.select(menubtn);
		else if (src instanceof Menu) {
			int selectedIndex = ((Menu)src).getSelectedIndex();
			if (selectedIndex<0) return;
			int tag=(((Widget)((Menu)src).getItem(selectedIndex))).tag;
			if (tag > -1 && Explorer.actions.get(tag) != null)
				Explorer.actions.get(tag).execute(src, data);
		}
	}
}
