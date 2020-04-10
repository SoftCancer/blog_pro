package com.dongl.dao;

import com.dongl.entity.FriendEntity;
import com.dongl.entity.NoFriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/22 0:06
 * @Version: 1.0
 */
public interface NoFriendDao extends JpaRepository<NoFriendEntity,String> {

    public NoFriendEntity findByUseridAndFriendid(String userId, String friendId);

}
