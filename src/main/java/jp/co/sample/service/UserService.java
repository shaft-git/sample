package jp.co.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.dao.MstUserDao;
import jp.co.sample.entity.MstUser;
import jp.co.sample.form.UserSearchForm;
import jp.co.sample.vo.ComboDataVo;
import jp.co.sample.vo.UserSearchRecordVo;

/**
 * ユーザー検索画面用サービス
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private MstUserDao mstUserDao;
	
	/** 性別ラジオボタン選択肢用Enum */
	private enum Sex{
		
		全て(null, "全て"), 男(1, "男"), 女(2, "女");
		
		private Integer val;
		private String dispStr;
		
		private Sex(Integer val, String dispStr) {
			this.val = val;
			this.dispStr = dispStr;
		}
		
		private static Sex getElement(Integer value) {
			for(Sex e: values()) {
				if(e.val.equals(value)) {
					return e;
				}
			}
			return null;
		}
	};
	
	/** 都道府県コンボボックス選択肢用Enum */
	private enum State{
		全て(null, "全て"),
		北海道(1,"北海道"),
		青森県(2,"青森県"),
		岩手県(3,"岩手県"),
		宮城県(4,"宮城県"),
		秋田県(5,"秋田県"),
		山形県(6,"山形県"),
		福島県(7,"福島県"),
		茨城県(8,"茨城県"),
		栃木県(9,"栃木県"),
		群馬県(10,"群馬県"),
		埼玉県(11,"埼玉県"),
		千葉県(12,"千葉県"),
		東京都(13,"東京都"),
		神奈川県(14,"神奈川県"),
		新潟県(15,"新潟県"),
		富山県(16,"富山県"),
		石川県(17,"石川県"),
		福井県(18,"福井県"),
		山梨県(19,"山梨県"),
		長野県(20,"長野県"),
		岐阜県(21,"岐阜県"),
		静岡県(22,"静岡県"),
		愛知県(23,"愛知県"),
		三重県(24,"三重県"),
		滋賀県(25,"滋賀県"),
		京都府(26,"京都府"),
		大阪府(27,"大阪府"),
		兵庫県(28,"兵庫県"),
		奈良県(29,"奈良県"),
		和歌山県(30,"和歌山県"),
		鳥取県(31,"鳥取県"),
		島根県(32,"島根県"),
		岡山県(33,"岡山県"),
		広島県(34,"広島県"),
		山口県(35,"山口県"),
		徳島県(36,"徳島県"),
		香川県(37,"香川県"),
		愛媛県(38,"愛媛県"),
		高知県(39,"高知県"),
		福岡県(40,"福岡県"),
		佐賀県(41,"佐賀県"),
		長崎県(42,"長崎県"),
		熊本県(43,"熊本県"),
		大分県(44,"大分県"),
		宮崎県(45,"宮崎県"),
		鹿児島県(46,"鹿児島県"),
		沖縄県(47,"沖縄県");
		
		private Integer val;
		private String dispStr;
		
		private State(Integer val, String dispStr) {
			this.val = val;
			this.dispStr = dispStr;
		}
		
		private static State getElement(Integer value) {
			for(State e: values()) {
				if(e.val.equals(value)) {
					return e;
				}
			}
			return null;
		}
	};
	
	/**
	 * 初期表示時に必要な項目を設定する
	 */
	public void setInitData(UserSearchForm userSearchForm) {
		// 性別ラジオボタン選択肢用リストの生成
		List<ComboDataVo> sexComboDataVoList = new ArrayList<ComboDataVo>();
		for(Sex sex: Sex.values()) {
			ComboDataVo sexComboDataVo = new ComboDataVo(sex.val, sex.dispStr);
			sexComboDataVoList.add(sexComboDataVo);
		}
		// 生成したリストをセット
		userSearchForm.setSexComboDataVoList(sexComboDataVoList);
		
		// 都道府県コンボボックス選択肢用リストの生成
		List<ComboDataVo> stateComboDataVoList = new ArrayList<ComboDataVo>();
		for(State state: State.values()) {
			ComboDataVo stateComboDataVo = new ComboDataVo(state.val, state.dispStr);
			stateComboDataVoList.add(stateComboDataVo);
		}
		// 生成したリストをセット
		userSearchForm.setStateComboDataVoList(stateComboDataVoList);
	}
	
	/**
	 * ユーザー検索実行
	 * @param userSearchForm ユーザー検索画面用フォーム
	 */
	public void execSearch(UserSearchForm userSearchForm) {
		// 検索条件
		Specification<MstUser> spec = makeSpecification(userSearchForm);
		// ソート条件
		Sort sort = this.makeSort();
		// DB検索結果を取得
		List<MstUser> mstUserList = mstUserDao.findAll(spec, sort);
		// 検索結果出力レコードリストを生成
		List<UserSearchRecordVo> resultRecordList = makeResultList(mstUserList);
		userSearchForm.setResultRecordList(resultRecordList);
	}
	
	/**
	 * ユーザー検索時にDBを検索する時の検索条件を作成する
	 * @param userSearchForm ユーザー検索画面用フォーム
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> makeSpecification(UserSearchForm userSearchForm){
		String keyword = userSearchForm.getKeyword();
		String sei = userSearchForm.getSei();
		String mei = userSearchForm.getMei();
		Integer sexCd = userSearchForm.getSexCd();
		Integer stateCd = userSearchForm.getStateCd();
		return Specification.where(
				this.keyword(keyword))
				.and(this.sei(sei))
				.and(this.mei(mei))
				.and(this.sexCd(sexCd))
				.and(this.stateCd(stateCd));
	}
	
	/**
	 * DBを検索する時の検索条件(キーワード)
	 * @param keyword キーワード
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> keyword(String keyword) {
		return keyword == null || keyword.isEmpty() ? null
				: (root, query, cb) ->
					cb.or(
							cb.like(root.<String>get("sei"), "%" + keyword + "%")
							, cb.like(root.<String>get("mei"), "%" + keyword + "%")
							, cb.like(root.<String>get("seiKana"), "%" + keyword + "%")
							, cb.like(root.<String>get("meiKana"), "%" + keyword + "%")
							);
	}
	
	/**
	 * DBを検索する時の検索条件(姓)
	 * @param sei 姓
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> sei(String sei) {
		return sei == null || sei.isEmpty() ? null
				: (root, query, cb) -> cb.like(root.<String>get("sei"), "%" + sei + "%");
	}
	
	/**
	 * DBを検索する時の検索条件(名)
	 * @param mei 名
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> mei(String mei) {
		return mei == null || mei.isEmpty() ? null
				: (root, query, cb) -> cb.like(root.<String>get("mei"), "%" + mei + "%");
	}
	
	/**
	 * DBを検索する時の検索条件(性別コード)
	 * @param sexCd 性別コード
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> sexCd(Integer sexCd) {
		return sexCd == null ? null
				: (root, query, cb) -> cb.equal(root.<Integer>get("sexCd"), sexCd);
	}
	
	/**
	 * DBを検索する時の検索条件(都道府県コード)
	 * @param stateCd 都道府県コード
	 * @return 検索条件オブジェクト
	 */
	private Specification<MstUser> stateCd(Integer stateCd) {
		return stateCd == null ? null
				: (root, query, cb) -> cb.equal(root.<Integer>get("stateCd"), stateCd);
	}
	
	/**
	 * ユーザー検索時にDBを検索する時のソート条件を作成する
	 * @param userSearchForm ユーザー検索画面用フォーム
	 * @return ソート条件 オブジェクト
	 */
	private Sort makeSort() {
		Sort sort = new Sort(Direction.ASC, "id");
		return sort;
	}
	
	/**
	 * エンティティリストから出力結果を生成する
	 * @param mstUserList mstUserの検索結果
	 * @return ユーザー検索結果表示用レコードVoリスト
	 */
	private List<UserSearchRecordVo> makeResultList(List<MstUser> mstUserList) {
		List<UserSearchRecordVo> resultRecordList = new ArrayList<>();
		for(MstUser mstUser : mstUserList) {
			UserSearchRecordVo recordVo = new UserSearchRecordVo();
			recordVo.setSei(mstUser.getSei());
			recordVo.setMei(mstUser.getMei());
			recordVo.setSeiKana(mstUser.getSeiKana());
			recordVo.setMeiKana(mstUser.getMeiKana());
			Integer sexCd = mstUser.getSexCd();
			Sex sex = Sex.getElement(sexCd);
			recordVo.setSex(sex.dispStr);
			Integer stateCd = mstUser.getStateCd();
			State state = State.getElement(stateCd);
			recordVo.setState(state.dispStr);
			resultRecordList.add(recordVo);
		}
		return resultRecordList;
	}
}
