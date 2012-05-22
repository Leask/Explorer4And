package lwc.demo;

import lwc.ui.*;
import lwc.ui.event.ActionListener;
import lwc.ui.notab.*;
import lwc.util.DialogAsk;

public class DialogProperty extends DialogAsk {
	private String path;
	private long len;
	private String type;
	private String date;
	private boolean readonly;
	private boolean hidden;
	
	private Label lblPathV;
	private Label lblSizeV;
	private Label lblTypeV;
	private Label lblDateV;
	private CheckboxNT cbReadOnly;
	private CheckboxNT cbHidden;
	
	public DialogProperty(int icon, String tit, Widget fcp, ActionListener al,
			String path,long len,String type,String date,boolean readonly,boolean hidden) {
		super(icon,tit,fcp,al);
		this.path=path;
		this.len=len;
		this.type=type;
		this.date=date;
		this.readonly=readonly;
		this.hidden=hidden;
	}

	@Override
	protected void genOptPnl(Panel panel) {
		panel.setLayout(new GridLayout(6,2,Toolkit.HORIZONTAL|Toolkit.VERTICAL|Toolkit.LEFT));
		
		//Name
		Label lblName=new Label(Global.DirLst_Col_Tit[Global.DirLst_Col_Name]);
		lblName.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblName);
	    lblPathV=new Label(path);
	    lblPathV.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblPathV);
		//Size 
		Label lblSize=new Label(Global.DirLst_Col_Tit[Global.DirLst_Col_Size]);
		lblSize.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblSize);
	    lblSizeV=new Label(Global.GetFileSize(len));
	    lblSizeV.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblSizeV);
		//Type
		Label lblType=new Label(Global.DirLst_Col_Tit[Global.DirLst_Col_Type]);
		lblType.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblType);
	    lblTypeV=new Label(type);
	    lblTypeV.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblTypeV);
		//Date 
		Label lblDate=new Label(Global.DirLst_Col_Tit[Global.DirLst_Col_Date]);
		lblDate.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblDate);
	    lblDateV=new Label(date);
	    lblDateV.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(lblDateV);
		//Hidden
		panel.add(new Label(Global.Txt_Hidden));
	    cbHidden=new CheckboxNT(Toolkit.ncanvas,"",Checkbox.CHECK);
	    cbHidden.setState(hidden);
	    cbHidden.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(cbHidden);
		//ReadOnly
		panel.add(new Label(Global.Txt_ReadOnly));
	    cbReadOnly=new CheckboxNT(Toolkit.ncanvas,"",Checkbox.CHECK);
	    cbReadOnly.setState(readonly);
	    cbReadOnly.setPSSize(Toolkit.Int_Itm_W, Toolkit.Int_Itm_H);
		panel.add(cbReadOnly);
	}
	
	public void update(String path,long len,String type,String date,boolean readonly,boolean hidden) {
		lblPathV.setText(path);
		lblSizeV.setText(Global.GetFileSize(len));
		lblTypeV.setText(type);
		lblDateV.setText(date);
		cbReadOnly.setState(readonly);
		cbHidden.setState(hidden);
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean getReadOnly() {
		return cbReadOnly.getState();
	}
	
	public boolean getHidden() {
		return cbHidden.getState();
	}
}
