package com.example.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.video.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Japin
 * @description 针对表【category】的数据库操作Mapper
 * @createDate 2024-11-21 09:24:15
 * @Entity com.example.video.model.entity.Category
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {


}




