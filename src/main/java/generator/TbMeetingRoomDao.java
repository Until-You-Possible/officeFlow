package generator;

import generator.TbMeetingRoom;
import generator.TbMeetingRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMeetingRoomDao {
    long countByExample(TbMeetingRoomExample example);

    int deleteByExample(TbMeetingRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbMeetingRoom record);

    int insertSelective(TbMeetingRoom record);

    List<TbMeetingRoom> selectByExample(TbMeetingRoomExample example);

    TbMeetingRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbMeetingRoom record, @Param("example") TbMeetingRoomExample example);

    int updateByExample(@Param("record") TbMeetingRoom record, @Param("example") TbMeetingRoomExample example);

    int updateByPrimaryKeySelective(TbMeetingRoom record);

    int updateByPrimaryKey(TbMeetingRoom record);
}