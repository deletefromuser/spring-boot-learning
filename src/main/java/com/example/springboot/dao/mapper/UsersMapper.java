package com.example.springboot.dao.mapper;

import com.example.springboot.dao.model.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(String username);

    int insert(Users row);

    int insertSelective(Users row);

    Users selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Users row);

    int updateByPrimaryKey(Users row);
}