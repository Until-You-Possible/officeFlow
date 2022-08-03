package generator;

import generator.TbRole;
import generator.TbRoleExample;
import generator.TbRoleWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRoleDao {
    long countByExample(TbRoleExample example);

    int deleteByExample(TbRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRoleWithBLOBs record);

    int insertSelective(TbRoleWithBLOBs record);

    List<TbRoleWithBLOBs> selectByExampleWithBLOBs(TbRoleExample example);

    List<TbRole> selectByExample(TbRoleExample example);

    TbRoleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRoleWithBLOBs record, @Param("example") TbRoleExample example);

    int updateByExampleWithBLOBs(@Param("record") TbRoleWithBLOBs record, @Param("example") TbRoleExample example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByPrimaryKeySelective(TbRoleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbRoleWithBLOBs record);

    int updateByPrimaryKey(TbRole record);
}