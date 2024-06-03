package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.InductionScreenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 诱导屏 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface InductionScreenMapper extends BaseMapper<InductionScreenEntity> {


    /**
     * 获取所有诱导屏信息
     * @return List<InductionScreenEntity>
     */
    @Select("select induction_screen.*,pre_transaction_gantry_equipment.transaction_name as fatherName from pre_transaction_gantry_equipment,induction_screen where pre_transaction_gantry_equipment.transaction_id = induction_screen.transaction_id")
    List<InductionScreenEntity> getAllInductionScreen();
}
