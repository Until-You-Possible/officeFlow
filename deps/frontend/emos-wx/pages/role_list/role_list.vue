<template>
	<view class="page" v-if="checkPermission(['ROOT', 'ROLE:SELECT'])">
		<view class="list">
			<view
				class="item"
				v-for="one in list"
				:key="one.id"
				@longpress="deleteRole(one.id, one.roleName, one.systemic)"
				@tap="toRolePage(one.id)"
			>
				<text class="key">{{ one.roleName }}</text>
				<image src="../../static/icon-2.png" mode="widthFix" class="icon"></image>
			</view>
		</view>
		<button class="btn" @tap="insertRole" v-if="checkPermission(['ROOT', 'ROLE:INSERT'])">添加</button>
		<uni-popup ref="popupRole" type="dialog">
			<uni-popup-dialog
				mode="input"
				title="编辑角色名称"
				:value="roleName"
				placeholder="输入角色名称"
				@confirm="finishRole"
			></uni-popup-dialog>
		</uni-popup>
	</view>
</template>

<script>
import uniPopup from '@/components/uni-popup/uni-popup.vue';
import uniPopupMessage from '@/components/uni-popup/uni-popup-message.vue';
import uniPopupDialog from '@/components/uni-popup/uni-popup-dialog.vue';
export default {
	components: {
		uniPopup,
		uniPopupMessage,
		uniPopupDialog
	},
	data() {
		return {
			list: [],
			id: 0,
			roleName: ''
		};
	},
	onShow: function() {
		this.loadData(this);
	},
	methods: {
		loadData: function() {
			let that = this;
			that.ajax(that.url.searchAllRole, 'GET', null, function(resp) {
				that.list = resp.data.result;
			});
		},
		insertRole: function() {
			this.deptName = '';
			this.$refs.popupRole.open();
		},
		finishRole: function(done, value) {
			let that = this;
			if (value == null || value.length == 0) {
				uni.showToast({
					icon: 'none',
					title: '角色不能为空'
				});
			} else {
				that.roleName = value;
				let roleName = that.roleName;
				setTimeout(function() {
					uni.navigateTo({
						url: '../role/role?opt=insert&roleName=' + roleName
					});
				}, 1000);
				that.roleName = null;
				done();
			}
		},
		toRolePage: function(id) {
			if (id == 0) {
				uni.showToast({
					icon: 'none',
					title: '超级管理员拥有全部权限，且无法修改'
				});
			} else {
				uni.navigateTo({
					url: '../role/role?opt=select&id=' + id
				});
			}
		},
		deleteRole: function(id, deptName,systemic) {
			if (!this.checkPermission(['ROOT', 'ROLE:DELETE'])||systemic) {
				return;
			}
			let that = this;
			uni.showModal({
				title: '提示信息',
				content: '是否删除' + deptName + '？',
				success: function(resp) {
					if (resp.confirm) {
						that.ajax(that.url.deleteRoleById, 'POST', { id: id }, function(resp) {
							uni.showToast({
								icon: 'success',
								title: '删除成功',
								complete: function() {
									that.loadData(that);
								}
							});
						});
					}
				}
			});
		}
	}
};
</script>

<style lang="less">
@import url('role_list.less');
</style>
