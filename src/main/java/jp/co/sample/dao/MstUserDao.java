package jp.co.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.co.sample.entity.MstUser;

/**
 * mst_userテーブル用リポジトリ
 */
public interface MstUserDao extends
	JpaRepository<MstUser, Integer>, JpaSpecificationExecutor<MstUser> {

}
