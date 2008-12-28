package com.jeecms.auxiliary.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.auxiliary.dao.MsgDao;
import com.jeecms.auxiliary.entity.Msg;
import com.jeecms.core.JeeCoreDaoImpl;

@Repository
public class MsgDaoImpl extends JeeCoreDaoImpl<Msg> implements MsgDao {
}