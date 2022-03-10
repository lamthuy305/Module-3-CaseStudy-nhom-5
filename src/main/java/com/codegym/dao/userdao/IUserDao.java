package com.codegym.dao.userdao;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.User;

import java.util.List;

public interface IUserDao extends IGeneralDao<User> {
     List<User> getAllGuestUser();
}
