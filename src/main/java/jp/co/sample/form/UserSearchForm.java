package jp.co.sample.form;

import java.util.List;

import jp.co.sample.vo.ComboDataVo;
import jp.co.sample.vo.UserSearchRecordVo;

/**
 * ユーザー検索画面用Form
 *
 */
public class UserSearchForm {

	/** キーワード */
	private String keyword;
	/** ユーザー姓 */
	private String sei;
	/** ユーザー名 */
	private String mei;
	/** 性別ラジオボタン選択値 */
	private Integer sexCd;
	/** 性別ラジオボタン選択肢用リスト */
	private List<ComboDataVo> sexComboDataVoList;
	/** 都道府県コンボボックス選択値 */
	private Integer stateCd;
	/** 都道府県コンボボックス選択肢用リスト */
	private List<ComboDataVo> stateComboDataVoList;
	
	/** 検索結果リスト */
	private List<UserSearchRecordVo> resultRecordList;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

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

	public Integer getSexCd() {
		return sexCd;
	}

	public void setSexCd(Integer sexCd) {
		this.sexCd = sexCd;
	}

	public List<ComboDataVo> getSexComboDataVoList() {
		return sexComboDataVoList;
	}

	public void setSexComboDataVoList(List<ComboDataVo> sexComboDataVoList) {
		this.sexComboDataVoList = sexComboDataVoList;
	}

	public Integer getStateCd() {
		return stateCd;
	}

	public void setStateCd(Integer stateCd) {
		this.stateCd = stateCd;
	}

	public List<ComboDataVo> getStateComboDataVoList() {
		return stateComboDataVoList;
	}

	public void setStateComboDataVoList(List<ComboDataVo> stateComboDataVoList) {
		this.stateComboDataVoList = stateComboDataVoList;
	}

	public List<UserSearchRecordVo> getResultRecordList() {
		return resultRecordList;
	}

	public void setResultRecordList(List<UserSearchRecordVo> resultRecordList) {
		this.resultRecordList = resultRecordList;
	}

}
