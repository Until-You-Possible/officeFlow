package generator;

import generator.TbWorkday;
import generator.TbWorkdayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbWorkdayDao {
    long countByExample(TbWorkdayExample example);

    int deleteByExample(TbWorkdayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbWorkday record);

    int insertSelective(TbWorkday record);

    List<TbWorkday> selectByExample(TbWorkdayExample example);

    TbWorkday selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbWorkday record, @Param("example") TbWorkdayExample example);

    int updateByExample(@Param("record") TbWorkday record, @Param("example") TbWorkdayExample example);

    int updateByPrimaryKeySelective(TbWorkday record);

    int updateByPrimaryKey(TbWorkday record);
}