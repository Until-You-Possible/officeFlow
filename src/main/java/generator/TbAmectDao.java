package generator;

import generator.TbAmect;
import generator.TbAmectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAmectDao {
    long countByExample(TbAmectExample example);

    int deleteByExample(TbAmectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAmect record);

    int insertSelective(TbAmect record);

    List<TbAmect> selectByExample(TbAmectExample example);

    TbAmect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAmect record, @Param("example") TbAmectExample example);

    int updateByExample(@Param("record") TbAmect record, @Param("example") TbAmectExample example);

    int updateByPrimaryKeySelective(TbAmect record);

    int updateByPrimaryKey(TbAmect record);
}