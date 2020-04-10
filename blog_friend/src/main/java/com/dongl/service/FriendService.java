package com.dongl.service;

import com.dongl.dao.FriendDao;
import com.dongl.dao.NoFriendDao;
import com.dongl.entity.FriendEntity;
import com.dongl.entity.NoFriendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/22 0:21
 * @Version: 1.0
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId,String friendid){
        // 1. 查看 userId 到 friendid 是否存在关联，若存在关联则说明已是好友关系，不可重复添加，返回 ：0。
        FriendEntity friendEntity = friendDao.findByUseridAndFriendid(userId,friendid);
        if (null != friendEntity){
            return 0;
        }
        // 2. 否则，实现 userId 到 friendid 的关联
        FriendEntity friend = new FriendEntity();
        friend.setUserid(userId);
        friend.setFriendid(friendid);
        friend.setIslike("0");

        // 3. 查看 friendid 到 userId 是否存在关联，若存在关联则说明已是好友关系，不可重复添加，返回 ：0。
        FriendEntity friendEntity1 = friendDao.findByUseridAndFriendid(friendid,userId);
        if (null != friendEntity1 ){
            friendDao.updateIsLike("0",userId,friendid);
            friendDao.updateIsLike("0",friendid,userId);
        }
        return 1;
    }

    public int addNoFriend(String userId,String friendid) {

      NoFriendEntity noFriendEntity =  noFriendDao.findByUseridAndFriendid(userId, friendid);
      // 判断是否为空，不为空即已是非好友
      if (null != noFriendEntity){
          return 0;
      }

      noFriendEntity = new NoFriendEntity();
      noFriendEntity.setUserid(userId);
      noFriendEntity.setFriendid(friendid);

      NoFriendEntity noFriendEntity_1 = noFriendDao.save(noFriendEntity);
      if (null == noFriendEntity_1){
          return 2;
      }
      return 1;
    }

}
