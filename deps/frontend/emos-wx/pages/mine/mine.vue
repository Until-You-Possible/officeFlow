<template>
	<view class="page">
		<view class="user-info">
			<view class="border-outer">
				<view class="border-inner"><image :src="photo" mode="widthFix" class="photo"></image></view>
			</view>
			<view class="summary">
				<view>
					<text class="title">姓名</text>
					<text class="value">{{ name }}</text>
				</view>
				<view>
					<text class="title">部门</text>
					<text class="value">{{ deptName }}</text>
				</view>
				<view>
					<text class="title">状态</text>
					<text class="value">在职</text>
				</view>
			</view>
		</view>
		<view class="list-title">用户信息</view>
		<uni-list>
			<uni-list-item title="个人资料" link to=""></uni-list-item>
			<uni-list-item title="我的考勤" link to="/pages/my_checkin/my_checkin"></uni-list-item>
			<uni-list-item title="罚款记录" link to=""></uni-list-item>
			<uni-list-item title="扫一扫" clickable @click="scanQRCode"></uni-list-item>
			<uni-list-item title="NFC" clickable @click="scanNFC"></uni-list-item>
		</uni-list>
		<view class="list-title">系统管理</view>
		<uni-list>
			<uni-list-item
				title="员工管理"
				v-show="checkPermission(['ROOT', 'EMPLOYEE:SELECT'])"
				link
				to="../../user/user_list"
			></uni-list-item>
			<uni-list-item
				title="部门管理"
				v-show="checkPermission(['ROOT', 'DEPT:SELECT'])"
				link
				to="../dept_list/dept_list"
			></uni-list-item>
			<uni-list-item
				title="权限管理"
				v-show="checkPermission(['ROOT', 'ROLE:SELECT'])"
				link
				to="../role_list/role_list"
			></uni-list-item>
		</uni-list>
	</view>
</template>

<script>
import uniList from '@/components/uni-list/uni-list.vue';
import uniListItem from '@/components/uni-list-item/uni-list-item.vue';
export default {
	components: {
		uniList,
		uniListItem
	},
	data() {
		return {
			name: '',
			deptName: '',
			photo: ''
		};
	},
	onShow: function() {
		let that = this;
		that.ajax(that.url.searchUserSummary, 'GET', null, function(resp) {
			let result = resp.data.result;
			that.name = result.name;
			that.deptName = result.deptName;
			that.photo = result.photo;
		});
	},
	methods: {
		scanQRCode: function() {
			let that = this;
			uni.scanCode({
				onlyFromCamera: true,
				success: function(resp) {
					let temp = resp.result.split('@@@');
					let module = temp[0];
					let id = temp[1];
					if (module == 'login') {
						uni.showModal({
							title: '提示信息',
							content: '是否登陆Emos管理系统？',
							success: function(resp) {
								if (resp.confirm) {
									uni.login({
										provider: 'weixin',
										success: function(resp) {
											let code = resp.code;
											that.ajax(that.url.checkQrCode, 'POST', { uuid: id, code: code }, function(
												resp
											) {
												if (resp.data.result) {
													uni.showToast({
														title: '登陆成功',
														icon: 'success'
													});
												} else {
													uni.showToast({
														title: '登陆失败',
														icon: 'none'
													});
												}
											});
										}
									});
								}
							}
						});
					} else if (module == 'MRIF') {
						uni.navigateTo({
							url: '../../meeting/meeting_room?meetingRoomId=' + id
						});
					} else if (module == 'MTCK') {
						uni.navigateTo({
							url: '../../meeting/meeting_checkin?meetingRoomId=' + id
						});
					}
				}
			});
		},
		scanNFC: function() {
			uni.navigateTo({
				url: '../nfc/nfc'
			});
		}
	}
};
</script>

<style lang="less">
@import url('mine.less');
</style>
