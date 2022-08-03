package generator;

import generator.TbReim;
import generator.TbReimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbReimDao {
    long countByExample(TbReimExample example);

    int deleteByExample(TbReimExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbReim record);

    int insertSelective(TbReim record);

    List<TbReim> selectByExampleWithBLOBs(TbReimExample example);

    List<TbReim> selectByExample(TbReimExample example);

    TbReim selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbReim record, @Param("example") TbReimExample example);

    int updateByExampleWithBLOBs(@Param("record") TbReim record, @Param("example") TbReimExample example);

    int updateByExample(@Param("record") TbReim record, @Param("example") TbReimExample example);

    int updateByPrimaryKeySelective(TbReim record);

    int updateByPrimaryKeyWithBLOBs(TbReim record);

    int updateByPrimaryKey(TbReim record);
}