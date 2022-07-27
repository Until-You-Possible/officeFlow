<template>
	<view>
		<image src="../static/banner-2.png" mode="widthFix" class="banner" />
		<view class="meeting" v-if="hasData">
			<view class="title">{{ title }}</view>
			<view class="row">
				<view class="attr">
					<image src="../static/icon-14.png" mode="widthFix" class="icon"></image>
					<text class="value">{{ start }} ~ {{ end }}</text>
				</view>
				<view class="attr">
					<image src="../static/icon-15.png" mode="widthFix" class="icon"></image>
					<text class="value">{{ num }}人</text>
				</view>
				<view class="attr">
					<image src="../static/icon-13.png" mode="widthFix" class="icon"></image>
					<text class="value">线下会议</text>
				</view>
			</view>
			<view class="row">
				<view class="attr">
					<image src="../static/icon-16.png" mode="widthFix" class="icon"></image>
					<text class="value">{{ place }}</text>
				</view>
			</view>
			<view class="row">
				<view class="attr">
					<image src="../static/icon-28.png" mode="widthFix" class="icon"></image>
					<text class="value">{{desc}}</text>
				</view>
			</view>
			<view class="operate">
				<button class="btn" v-if="!checkin" @tap="checkinMeeting()">我要签到</button>
				<button class="btn-disable" v-if="checkin">已签到</button>
			</view>
			<block v-if="present.length > 0">
				<view class="sub-title">出席员工列表</view>
				<view class="member-container">
					<view class="member" v-for="one in present">
						<image :src="one.photo" mode="widthFix"></image>
						<text class="name">{{ one.name }}</text>
					</view>
				</view>
			</block>
			<block v-if="unpresent.length > 0">
				<view class="sub-title">缺席员工列表</view>
				<view class="member-container">
					<view class="member" v-for="one in unpresent">
						<image :src="one.photo" mode="widthFix"></image>
						<text class="name">{{ one.name }}</text>
					</view>
				</view>
			</block>
		</view>
		<view class="notice" v-if="!hasData">当前时间无会议</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			meetingRoomId: null,
			hasData: false,
			meetingId: null,
			title: null,
			start: null,
			end: null,
			num: null,
			place: null,
			desc:null,
			checkin: false,
			present: [],
			unpresent: []
		};
	},
	methods: {
		loadData:function(ref){
			ref.ajax(ref.url.searchMeetingByScan, 'POST', { meetingRoomId: ref.meetingRoomId }, function(resp) {
				let result = resp.data.result;
				if (result != null) {
					ref.hasData = true;
					ref.meetingId = result.id;
					ref.title = result.title;
					ref.start = result.start;
					ref.end = result.end;
					ref.num = result.num;
					ref.desc=result.desc;
					ref.place = result.place;
					ref.checkin = result.checkin;
					ref.present = result.present != null ? JSON.parse(result.present) : [];
					ref.unpresent = result.unpresent != null ? JSON.parse(result.unpresent) : [];
				} else {
					ref.hasData = false;
				}
			});
		},
		checkinMeeting: function() {
			let that = this;
			that.ajax(that.url.updateMeetingPresent, 'POST', { meetingId: that.meetingId }, function(resp) {
				let result=resp.data.result
				if(result){
					uni.showToast({
						icon:"success",
						title:"签到成功",
					})
					setTimeout(function(){
						that.loadData(that)
					},2000)
					
				}
				else{
					uni.showToast({
						icon:"none",
						title:"签到失败"
					})
				}
			});
		}
	},
	onShow: function() {
		this.loadData(this)
	},
	onLoad: function(options) {
		this.meetingRoomId = options.meetingRoomId;
	}
};
</script>

<style lang="less">
@import url('meeting_checkin.less');
</style>
