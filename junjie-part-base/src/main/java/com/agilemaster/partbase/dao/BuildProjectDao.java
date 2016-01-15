package com.agilemaster.partbase.dao;

import java.util.List;

import com.agilemaster.partbase.entity.BuildProject;

public interface BuildProjectDao {
List list();
BuildProject findById(Long id);
}
