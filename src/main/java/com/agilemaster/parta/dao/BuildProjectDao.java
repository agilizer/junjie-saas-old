package com.agilemaster.parta.dao;

import java.util.List;

import com.agilemaster.parta.entity.BuildProject;

public interface BuildProjectDao {
List list();
BuildProject findById(Long id);
}
