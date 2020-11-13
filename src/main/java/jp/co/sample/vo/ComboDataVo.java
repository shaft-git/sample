package jp.co.sample.vo;

/**
 * コンボボックスをはじめとして、ラジオボタンやチェックボックスの選択肢用Vo
 *
 */
public class ComboDataVo {

	/** 選択肢のvalue値 */
	private Integer valStr;
	/** 選択肢の画面表示値 */
	private String dispStr;
	
	/** コンストラクタ */
	public ComboDataVo(Integer valStr, String dispStr) {
		this.valStr = valStr;
		this.dispStr = dispStr;
	}
	
	public Integer getValStr() {
		return valStr;
	}
	public void setValStr(Integer valStr) {
		this.valStr = valStr;
	}
	public String getDispStr() {
		return dispStr;
	}
	public void setDispStr(String dispStr) {
		this.dispStr = dispStr;
	}
	
}
