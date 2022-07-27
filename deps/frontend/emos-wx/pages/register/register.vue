<template>
	<view>
		<image src="../../static/logo-2.png" mode="widthFix" class="logo"></image>
		<view class="register-container">
			<input type="text" placeholder="输入你的邀请码" class="register-code" maxlength="6" v-model="registerCode" />
			<view class="register-desc">管理员创建员工证账号之后，你可以从你的个人邮箱中获得注册邀请码</view>
			<button class="register-btn" open-type="getUserInfo" @tap="register()">执行注册</button>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			registerCode: '',
			code:null
		};
	},
	methods: {
		register: function() {
			let that = this;
			if (that.registerCode == null || that.registerCode.length == 0) {
				uni.showToast({
					icon: 'none',
					title: '邀请码不能为空'
				});
				return;
			} else if (/^[0-9]{6}$/.test(that.registerCode) == false) {
				uni.showToast({
					icon: 'none',
					title: '邀请码必须是6位数字'
				});
				return;
			}
			uni.login({
				provider: 'weixin',
				success: function(resp) {
					let code = resp.code;
					that.code=code
				}
			});
			uni.getUserProfile({
				desc: '获取用户信息',
				success: function(resp) {
					let nickName = resp.userInfo.nickName;
					let avatarUrl = resp.userInfo.avatarUrl;
					// console.log(nickName);
					// console.log(avatarUrl);
					let data = {
						code: that.code,
						nickname: nickName,
						photo: avatarUrl,
						registerCode: that.registerCode
					};
					that.ajax(that.url.register, 'POST', data, function(resp) {
						let permission = resp.data.permission;
						let token = resp.data.token;
						uni.setStorageSync('token', token);
						uni.setStorageSync('permission', permission);
						uni.switchTab({
							url: '../index/index'
						});
					});
				}
			});
		}
	}
};
</script>

<style lang="less">
@import url('register.less');
</style>
