package com.example.springboot.dao.mapper;

import com.example.springboot.dao.model.User;
import com.example.springboot.dao.model.UserExample;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);

    List<User> selectUsers(String username);
}