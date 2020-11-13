package jp.co.sample.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="mst_user")
@NamedQuery(name="MstUser.findAll", query="SELECT m FROM MstUser m")
@EntityListeners(AuditingEntityListener.class)
public class MstUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** 姓 */
	private String sei;
	/** 名 */
	private String mei;
	/** 姓カナ */
	@Column(name="sei_kana")
	private String seiKana;
	/** 名カナ */
	@Column(name="mei_kana")
	private String meiKana;
	/** 性別コード */
	@Column(name="sex_cd")
	private Integer sexCd;
	/** 都道府県コード */
	@Column(name="state_cd")
	private Integer stateCd;
	
	@CreatedBy
	@Column(name="create_user_id")
	private Integer createUserId;
	
	@LastModifiedBy
	@Column(name="update_user_id")
	private Integer updateUserId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name="create_datetime")
	private Date createDatetime;
	
	@Column(name="active_flg")
	private int activeFlg = 1;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_timestamp")
	@LastModifiedDate
	private Date updateTimestamp;
	
	@Version
	private int version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSexCd() {
		return sexCd;
	}

	public void setSexCd(Integer sexCd) {
		this.sexCd = sexCd;
	}

	public Integer getStateCd() {
		return stateCd;
	}

	public void setStateCd(Integer stateCd) {
		this.stateCd = stateCd;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public int getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
