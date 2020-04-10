package com.dongl.dao;

import com.dongl.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/22 0:06
 * @Version: 1.0
 */
public interface FriendDao extends JpaRepository<FriendEntity,String> {

    public FriendEntity findByUseridAndFriendid(String userId,String friendId);

    @Modifying
    @Query(value = "UPDATE tb_friend set islike = ? where userid = ? and friendid = ?",nativeQuery = true)
    public void updateIsLike(String islike,String userId,String friendId);
}
