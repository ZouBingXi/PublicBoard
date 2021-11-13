package cn.szuer.publicboard.mapper;

import org.springframework.stereotype.Repository;

import cn.szuer.publicboard.model.User;

@Repository
public interface UserMapper {
    
    User getById(Integer id);
}
