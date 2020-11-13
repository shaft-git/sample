package jp.co.sample.vo;

/**
 * ユーザー検索画面検索結果保持用レコードVo
 *
 */
public class UserSearchRecordVo {

	/** ユーザー姓 */
	private String sei;
	/** ユーザー名 */
	private String mei;
	/** ユーザー姓カナ */
	private String seiKana;
	/** ユーザー名カナ */
	private String meiKana;
	/** 性別 */
	private String sex;
	/** 都道府県 */
	private String state;
	
	public String getSei() {
		return sei;
	}
	public void setSei(String sei) {
		this.sei = sei;
	}
	public String getMei() {
		return mei;
	}
	public void setMei(String mei) {
		this.mei = mei;
	}
	public String getSeiKana() {
		return seiKana;
	}
	public void setSeiKana(String seiKana) {
		this.seiKana = seiKana;
	}
	public String getMeiKana() {
		return meiKana;
	}
	public void setMeiKana(String meiKana) {
		this.meiKana = meiKana;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
