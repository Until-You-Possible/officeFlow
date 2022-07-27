<template>
	<view class="page" v-if="checkPermission(permission, ['ROOT', 'DEPT:SELECT'])">
		<view class="list">
			<view class="item" v-for="one in list" :key="one.id" @longpress="deleteDept(one.id, one.deptName)">
				<text class="key">{{ one.deptName }}</text>
				<text class="value" @tap="updateDept(one.id, one.deptName)" v-if="checkPermission(permission, ['ROOT', 'DEPT:UPDATE'])">修改</text>
			</view>
		</view>
		<button class="btn" @tap="insertDept" v-if="checkPermission(permission, ['ROOT', 'DEPT:INSERT'])">添加</button>
		<uni-popup ref="popupDept" type="dialog">
			<uni-popup-dialog mode="input" title="编辑部门名称" :value="deptName" placeholder="输入部门名称" @confirm="finishDept"></uni-popup-dialog>
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
			deptId: 0,
			deptName: '',
			opt: '',
			permission: uni.getStorageSync('permission')
		};
	},
	onShow: function() {
		this.loadData(this);
	},
	methods: {
		loadData: function() {
			let that = this;
			that.ajax(that.url.searchAllDept, 'GET', null, function(resp) {
				that.list = resp.data.result;
			});
		},
		insertDept: function() {
			this.deptName = '';
			this.$refs.popupDept.open(); //弹出对话框，输入部门名称
			this.opt = 'insert';
		},
		updateDept: function(id, deptName) {
			this.id = id;
			this.deptName = deptName;
			this.$refs.popupDept.open(); //弹出对话框，可以修改部门名称
			this.opt = 'update';
		},
		finishDept: function(done, value) {
			let that = this;
			let flag = false;
			for (let one of that.list) {
				//对话框中输入的部门名称不能跟已有部门名称相同
				if (one.deptName == value) {
					flag = true;
					break;
				}
			}
			//部门名称必须是汉字，如果有特殊要求可以修改正则表达式
			if (!/^[\u4e00-\u9fa5]{3,15}$/.test(value)) {
				uni.showToast({
					icon: 'none',
					title: '部门名称不符合要求'
				});
			} else if (flag) {
				uni.showToast({
					icon: 'none',
					title: '部门已经存在'
				});
			} else {
				that.deptName = value;
				let url = '';
				if (that.opt == 'insert') {
					url = that.url.insertDept;
				} else {
					url = that.url.updateDeptById;
				}
				let data = {
					id: that.id,
					deptName: that.deptName
				};
				that.ajax(url, 'POST', data, function(resp) {
					uni.showToast({
						icon: 'success',
						title: '保存成功',
						complete: function() {
							that.loadData(that);
						}
					});
				});
				done();
			}
		},
		deleteDept: function(id, deptName) {
			if (!this.checkPermission(this.permission, ['ROOT', 'DEPT:DELETE'])) {
				return;
			}
			let that = this;
			uni.showModal({
				title: '提示信息',
				content: '是否删除' + deptName + '？',
				success: function(resp) {
					if (resp.confirm) {
						that.ajax(that.url.deleteDeptById, 'POST', { id: id }, function(resp) {
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
@import url('dept_list.less');
</style>
