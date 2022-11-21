package com.buinam.redisdemo.repository;

import com.buinam.redisdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private RedisTemplate redisTemplate;

    // this Key is used to store all the users
    private static final String KEY = "USER";
    @Override
    public boolean saveUser(User user) {
        try {
            // save the data to redis database
            redisTemplate.opsForHash().put(KEY,user.getId().toString(),user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User fetchUserById(Long id) {
        User user;
        user = (User) redisTemplate.opsForHash().get(KEY,id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(Long id, User user) {
        try {
            redisTemplate.opsForHash().put(KEY,id.toString(),user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
