package com.example.demo.mapper;

import com.example.demo.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.VO.StudentTestNoticeVO;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-03
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
