package lwc.demo;

import java.util.Date;

import lwc.data.Text;
import lwc.ui.*;
import lwc.ui.event.ActionListener;

public class Global {
	public static final String Txt_UnitG  = "G";
	public static final String Txt_UnitM  = "M";
	public static final String Txt_UnitK  = "K";
	public static final String Txt_UnitB  = "B";
	public static final String Txt_TabStr = "  ";
	public static final String Txt_Inform = "Information";
	public static final String Txt_Sure   = "Are u sure to";
	public static final String Txt_QuestMark = "?";
	public static final String Txt_Success= "success";
	public static final String Txt_Failure= "failure";
	public static final String Txt_Folder = "folder";
	public static final String Txt_File   = "file";
	public static final String Txt_Path   = "path";
	public static final String Txt_ReadOnly= "Read only";
	public static final String Txt_Hidden  = "Hidden";
	public static final String Txt_Include= "include";
	public static final String Txt_Objects= "objects";
	public static final String Txt_Name   = "name";
	public static final int Int_Split     = 2;
	public static final int Int_ScrollWid = 6;

	public static final String DirTre_Txt_RootName = "root";
	public static final String DirTre_Txt_InitDir  = "/";
	public static final String DirTre_Txt_Loading  = "loading...";
	
	public static final int DirLst_Col_Name     = 0;
	public static final int DirLst_Col_Size     = 1;
	public static final int DirLst_Col_Type     = 2;
	public static final int DirLst_Col_Date     = 3;
	public static final String[] DirLst_Col_Tit = new String[] {"Name", "Size", "Type", "Date"};
	public static final int[] DirLst_Col_Siz    = new int[] {95, 35, 45, 95};
	public static final int DirLst_Wid          = DirLst_Col_Siz[DirLst_Col_Name]+
	                                              DirLst_Col_Siz[DirLst_Col_Size]+
	                                              DirLst_Col_Siz[DirLst_Col_Type]+
	                                              DirLst_Col_Siz[DirLst_Col_Date]+Int_ScrollWid*2;
	
	public static final String BarInf_Txt_Menu  = "Menu";
	public static final String BarInf_Txt_Total = "total";
	public static final String BarInf_Txt_Used  = "used";
	
	public static final int Menu               = -1;
	public static final int Menu_File          = 0;
	public static final int Menu_Edit          = 1;
	public static final int Menu_View          = 2;
	public static final int Menu_Option        = 3;
	public static final int Menu_Help          = 4;
	public static final int Menu_Option_       = Menu_Option;
	public static final int Menu_Help_         = Menu_Help;
	public static final String[] Menu_Tit      = new String[] {"File", "Edit", "View", "Option", "Help"};
	public static final int[] Menu_Rid         = new int[] {
		R.drawable.mnuvoid, R.drawable.mnuvoid, R.drawable.mnuvoid, R.drawable.lwc, R.drawable.mnuvoid};
	
	public static final int Menu_File_New      = 0;
	public static final int Menu_File_Open     = 1;
	public static final int Menu_File_Line_1   = 2;
	public static final int Menu_File_Delete   = 3;
	public static final int Menu_File_Rename   = 4;
	public static final int Menu_File_Property = 5;
	public static final int Menu_File_Line_2   = 6;
	public static final int Menu_File_Search   = 7;
	public static final int Menu_File_Compare  = 8;
	public static final int Menu_File_         = 100;
	public static final int Menu_File_New_     = Menu_File_ + Menu_File_New;
	public static final int Menu_File_Open_    = Menu_File_ + Menu_File_Open;
	public static final int Menu_File_Delete_  = Menu_File_ + Menu_File_Delete;
	public static final int Menu_File_Rename_  = Menu_File_ + Menu_File_Rename;
	public static final int Menu_File_Property_= Menu_File_ + Menu_File_Property;
	public static final int Menu_File_Search_  = Menu_File_ + Menu_File_Search;
	public static final int Menu_File_Compare_ = Menu_File_ + Menu_File_Compare;
	public static final String[] Menu_File_Tit = new String[] {"New...", "Open", "|", "Delete", "Rename...", "Property...", "|", "Search...", "Compare with..."};
	public static final int[] Menu_File_Rid    = new int[] {
		R.drawable.mnuvoid, R.drawable.mnuvoid, 0, R.drawable.lwc, R.drawable.mnuvoid, R.drawable.lwc, 0, R.drawable.lwc, R.drawable.lwc};
	
	public static final int Menu_Edit_Undo     = 0;
	public static final int Menu_Edit_Line_1   = 1;
	public static final int Menu_Edit_Cut      = 2;
	public static final int Menu_Edit_Copy     = 3;
	public static final int Menu_Edit_Paste    = 4;
	public static final int Menu_Edit_Line_2   = 5;
	public static final int Menu_Edit_CopyTo   = 6;
	public static final int Menu_Edit_MoveTo   = 7;
	public static final int Menu_Edit_Line_3   = 8;
	public static final int Menu_Edit_SelAll   = 9;
	public static final int Menu_Edit_NSel     = 10;
	public static final int Menu_Edit_         = 200;
	public static final int Menu_Edit_Undo_    = Menu_Edit_ + Menu_Edit_Undo;
	public static final int Menu_Edit_Cut_     = Menu_Edit_ + Menu_Edit_Cut;
	public static final int Menu_Edit_Copy_    = Menu_Edit_ + Menu_Edit_Copy;
	public static final int Menu_Edit_Paste_   = Menu_Edit_ + Menu_Edit_Paste;
	public static final int Menu_Edit_CopyTo_  = Menu_Edit_ + Menu_Edit_CopyTo;
	public static final int Menu_Edit_MoveTo_  = Menu_Edit_ + Menu_Edit_MoveTo;
	public static final int Menu_Edit_SelAll_  = Menu_Edit_ + Menu_Edit_SelAll;
	public static final int Menu_Edit_NSel_    = Menu_Edit_ + Menu_Edit_NSel;
	public static final String[] Menu_Edit_Tit = new String[] {"Undo", "|", "Cut", "Copy", "Paste", "|", "Copy to...", "Move to...", "|", "Select all", "Inverse select"};
	public static final int[] Menu_Edit_Rid    = new int[] {
		R.drawable.lwc, 0, R.drawable.lwc, R.drawable.lwc, R.drawable.lwc, 0, R.drawable.lwc, R.drawable.lwc, 0, R.drawable.mnuvoid, R.drawable.mnuvoid};

	public static final int Menu_View_ToolBar  = 0;
	public static final int Menu_View_StatusBar= 1;
	public static final int Menu_View_Line_1   = 2;
	public static final int Menu_View_SortName = 3;
	public static final int Menu_View_SortSize = 4;
	public static final int Menu_View_SortType = 5;
	public static final int Menu_View_SortDate = 6;
	public static final int Menu_View_Line_2   = 7;
	public static final int Menu_View_Back     = 8;
	public static final int Menu_View_Forward  = 9;
	public static final int Menu_View_UpLayer  = 10;
	public static final int Menu_View_Line_3   = 11;
	public static final int Menu_View_Refresh  = 12;
	public static final int Menu_View_         = 300;
	public static final int Menu_View_ToolBar_ = Menu_View_ + Menu_View_ToolBar;
	public static final int Menu_View_StatusBar_= Menu_View_ + Menu_View_StatusBar;
	public static final int Menu_View_SortName_ = Menu_View_ + Menu_View_SortName;
	public static final int Menu_View_SortSize_ = Menu_View_ + Menu_View_SortSize;
	public static final int Menu_View_SortType_ = Menu_View_ + Menu_View_SortType;
	public static final int Menu_View_SortDate_ = Menu_View_ + Menu_View_SortDate;
	public static final int Menu_View_Back_     = Menu_View_ + Menu_View_Back;
	public static final int Menu_View_Forward_  = Menu_View_ + Menu_View_Forward;
	public static final int Menu_View_UpLayer_  = Menu_View_ + Menu_View_UpLayer;
	public static final int Menu_View_Refresh_  = Menu_View_ + Menu_View_Refresh;
	public static final String[] Menu_View_Tit = new String[] {"ToolBar", "StatusBar", "|", "Sort by Name", "Sort by Size", "Sort by Type", "Sort by Date", "|", "Back", "Forward", "Up Layer", "|", "Refresh"};
	public static final int[] Menu_View_Rid    = new int[] {
		R.drawable.mnuvoid, R.drawable.mnuvoid, 0, 0, 0, 0, 0, 0, R.drawable.lwc, R.drawable.lwc, R.drawable.lwc, 0, R.drawable.lwc};

	public static final SpeedButton[] BarCmd     = new SpeedButton[10];
	public static final int BarCmd_Back          = 0;
	public static final int BarCmd_Forward       = 1;
	public static final int BarCmd_Refresh       = 2;
	public static final int BarCmd_Line_1        = 3;
	public static final int BarCmd_Cut           = 4;
	public static final int BarCmd_Copy          = 5;
	public static final int BarCmd_Paste         = 6;
	public static final int BarCmd_Undo          = 7;
	public static final int BarCmd_Line_2        = 8;
	public static final int BarCmd_Property      = 9;
	public static final String[] BarCmd_Tit      = new String[] {
		Menu_View_Tit[Menu_View_Back], Menu_View_Tit[Menu_View_Forward], Menu_View_Tit[Menu_View_Refresh], "|",
		Menu_Edit_Tit[Menu_Edit_Cut], Menu_Edit_Tit[Menu_Edit_Copy], Menu_Edit_Tit[Menu_Edit_Paste], Menu_Edit_Tit[Menu_Edit_Undo], "|",
		Menu_File_Tit[Menu_File_Property]};
	public static final int[] BarCmd_Rid         = new int[] {
		R.drawable.back, R.drawable.forward, R.drawable.refresh, 0,
		R.drawable.cut, R.drawable.copy, R.drawable.paste, R.drawable.undo, 0,
		R.drawable.property};
	public static final int[] BarCmd_Rid_        = new int[] {
		R.drawable.back_, R.drawable.forward_, R.drawable.refresh_, 0,
		R.drawable.cut_, R.drawable.copy_, R.drawable.paste_, R.drawable.undo_, 0,
		R.drawable.property_};
/*	
	public static StButton GenStBtn(int id,ActionListener al){
		StButton sbtn=new StButton();
		sbtn.tag=id;
		AdvViewMan m = new AdvViewMan();
		int rid=Global.BarCmd_Rid[id];
		m.put ("st.over", new ImgSetRender(Toolkit.getImage(Integer.toString(rid)), 0,0,16,16,3));
		m.put ("st.out", new ImgSetRender(Toolkit.getImage(Integer.toString(rid)), 0,0,16,16,3));
		m.put ("st.pressed", new ImgSetRender(Toolkit.getImage(Integer.toString(rid)), -1,-1,15,15,3));
        m.put ("st.disabled", new ImgSetRender(Toolkit.getImage(Integer.toString(Global.BarCmd_RidDis[id])), 0,0,16,16,3));
		sbtn.setViewMan (m);
		sbtn.addActionListener(al);
		return sbtn;
	}
*/	
	public static SpeedButton GenBtn(int id,ActionListener al){
		AdvViewMan man = new AdvViewMan();
		man.put("st.over", new ImgSetRender(Toolkit.getImage(Integer.toString(BarCmd_Rid[id])),0,0,39,19,3));
		man.put("st.out", new ImgSetRender(Toolkit.getImage(Integer.toString(BarCmd_Rid[id])),0,0,39,19,3));
		man.put("st.pressed", new ImgSetRender(Toolkit.getImage(Integer.toString(BarCmd_Rid_[id])),0,0,39,19,3));
		SpeedButton btn=new SpeedButton();//Toolkit.genImgLbl(Integer.toString(BarCmd_Rid[id]), BarCmd_Tit[id], Toolkit.VERTICAL));
		btn.setViewMan(man);
		btn.tag=id;
		btn.addActionListener(al);
		return btn;
	}
	
	public static String GetFileSize(long len) {
		if (len<=0)
			return "";
		else if (len<1024)
			return len+Txt_UnitB;
		else if (len>=1024 && len<1048576)
			return (len/1024)+Txt_UnitK;
		else if (len>=1048576 && len<1073741824)
			return (len/1048576)+Txt_UnitM;
		else if (len>=1073741824)
			return (len/1073741824)+Txt_UnitG;
		else
			return "";
	}

	public static String GetFileType(String fn) {
		int i=fn.lastIndexOf('.');
		if (i>0)
			return fn.substring(i+1)+" "+Txt_File;
		else
			return "";
	}
	
    public static String ForStr(String s, String f, int i) {
    	String fs = "";
    	for (int j=0; j<i; j++)
    		fs = fs + f;
    	String ss = fs + s;
    	return ss.substring(ss.length()-i, ss.length());
    }
    
    public static String ForTime(long t) {
    	Date dt=new Date(t);
    	return ""+(dt.getYear()+1900)+"-"+
    		   ForStr(Integer.toString(dt.getMonth()+1),"0",2)+"-"+
    	       ForStr(Integer.toString(dt.getDate()),"0",2)+" "+
    	       ForStr(Integer.toString(dt.getHours()),"0",2)+":"+
    	       ForStr(Integer.toString(dt.getMinutes()),"0",2);
    }
}
