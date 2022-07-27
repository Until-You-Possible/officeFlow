<template>
	<view>
		<image src="../static/banner-1.jpg" mode="widthFix" class="banner"></image>
		<view class="room">
			<view class="header">
				<text class="title">{{ name }}</text>
				<text class="status">{{ meeting.title != null ? '使用中' : '空闲' }}</text>
			</view>
			<view class="attr-container">
				<view class="row">
					<view class="attr">
						<image src="../static/icon-21.png" mode="widthFix"></image>
						<text>规模：{{ scale }}</text>
					</view>
					<view class="attr">
						<image src="../static/icon-22.png" mode="widthFix"></image>
						<text>人数：{{ max }}人</text>
					</view>
				</view>
				<view class="row">
					<view class="attr">
						<image src="../static/icon-23.png" mode="widthFix"></image>
						<text v-if="meeting.start == null || meeting.end == null">无 （空闲中）</text>
						<text v-if="meeting.start != null && meeting.end != null">
							{{ meeting.start }} ~ {{ meeting.end }}
						</text>
					</view>
				</view>
			</view>
			<view class="meeting-container" v-if="meeting.title != null">
				<view class="row">
					<image src="../static/icon-24.png" mode="widthFix"></image>
					<view class="right">
						<text class="title">当前会议</text>
						<text class="desc">{{ meeting.title }}</text>
					</view>
				</view>
				<view class="row">
					<image src="../static/icon-25.png" mode="widthFix"></image>
					<view class="right">
						<text class="title">相关部门</text>
						<text class="desc">{{ meeting.dept }}</text>
					</view>
				</view>
				<view class="row">
					<image src="../static/icon-26.png" mode="widthFix"></image>
					<view class="right">
						<text class="title">参会人员</text>
						<text class="desc">总计{{ meeting.num }}人</text>
					</view>
				</view>
				<view class="sub-title">
					<span>会议成员列表</span>
				</view>
				<view class="member-container">
					
					<view class="member" v-for="one in meeting.members" :key="one.name">
						<image :src="one.photo" mode="widthFix"></image>
						<text class="name">{{ one.name }}</text>
					</view>
				</view>
			</view>
			<view class="meeting-list">
				<text class="title">今日会议列表</text>
				<YSteps
					v-if="meetings.length > 0"
					lineNum="0"
					color="#fff"
					backgroundColor="#3474FF"
					:infoList="meetings"
				></YSteps>
				<text v-if="meetings.length == 0" class="desc">本会议室今日没有会议，您可以在线申请并使用本会议室</text>
			</view>
		</view>
	</view>
</template>

<script>
import YSteps from '../components/Y-Steps/Y-Steps.vue';

export default {
	components: {
		YSteps
	},
	data() {
		return {
			meetingRoomId: null,
			name: null,
			scale: null,
			max: null,
			meeting: {
				title: null,
				start: null,
				end: null,
				hours: null,
				dept: null,
				num: null,
				members: []
			},
			meetings: []
		};
	},
	methods: {},
	onShow: function() {
		let that = this;
		that.ajax(that.url.searchMeetingRoomByScan, 'POST', { meetingRoomId: that.meetingRoomId }, function(resp) {
			let result = resp.data.result;
			that.name = result.name;
			that.scale = result.scale;
			(that.max = result.max), (that.meeting.start = result.start);
			that.meeting.end = result.end;
			that.meeting.title = result.title;
			that.meeting.dept = result.dept;
			that.meeting.num = result.num;

			that.meeting.members = result.members != null ? JSON.parse(result.members) : [];
			let meetings = result.meetings != null ? JSON.parse(result.meetings) : [];
			let temp = [];
			for (let one of meetings) {
				temp.push({ date: one.start + ' ~ ' + one.end, info: one.title });
			}
			that.meetings = temp;
		});
	},
	onLoad: function(options) {
		this.meetingRoomId = options.meetingRoomId;
	}
};
</script>

<style lang="less">
@import url('meeting_room.less');
</style>
