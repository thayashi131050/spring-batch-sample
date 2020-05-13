package com.example.batchprocessing;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PersonMapper {

    public List<Person> select();

    public void save(Person person);

}