package generator;

import generator.TbMeeting;
import generator.TbMeetingExample;
import generator.TbMeetingWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMeetingDao {
    long countByExample(TbMeetingExample example);

    int deleteByExample(TbMeetingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbMeetingWithBLOBs record);

    int insertSelective(TbMeetingWithBLOBs record);

    List<TbMeetingWithBLOBs> selectByExampleWithBLOBs(TbMeetingExample example);

    List<TbMeeting> selectByExample(TbMeetingExample example);

    TbMeetingWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbMeetingWithBLOBs record, @Param("example") TbMeetingExample example);

    int updateByExampleWithBLOBs(@Param("record") TbMeetingWithBLOBs record, @Param("example") TbMeetingExample example);

    int updateByExample(@Param("record") TbMeeting record, @Param("example") TbMeetingExample example);

    int updateByPrimaryKeySelective(TbMeetingWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbMeetingWithBLOBs record);

    int updateByPrimaryKey(TbMeeting record);
}