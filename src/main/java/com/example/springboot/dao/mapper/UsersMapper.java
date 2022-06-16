package com.example.springboot.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.dao.model.Users;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(String username);

    int insert(Users row);

    int insertSelective(Users row);

    Users selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Users row);

    int updateByPrimaryKey(Users row);
}