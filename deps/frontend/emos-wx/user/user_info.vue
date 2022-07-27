<template>
	<view class="page">
		<view class="list">
			<view class="item" @tap="editName">
				<text class="key">姓名</text>
				<text class="value">{{ name }}</text>
			</view>
			<view class="item">
				<text class="key">性别</text>
				<picker v-if="canEdit" :value="sexIndex" :range="sexArray" @change="sexChange">{{ sexArray[sexIndex] }}</picker>
				<text v-if="!canEdit" class="value">{{ sexArray[sexIndex] }}</text>
			</view>
			<view class="item">
				<text class="key">所在部门</text>
				<picker v-if="canEdit" :value="deptIndex" :range="deptArray" @change="deptChange">{{ deptArray[deptIndex] }}</picker>
				<text v-if="!canEdit" class="value">{{ deptArray[deptIndex] }}</text>
			</view>
			<view class="item" @tap="editTel">
				<text class="key">联系电话</text>
				<text class="value">{{ tel }}</text>
			</view>
			<view class="item" @tap="editEmail">
				<text class="key">电子信箱</text>
				<text class="value">{{ email }}</text>
			</view>
			<view class="item">
				<text class="key">入职日期</text>
				<picker v-if="canEdit" mode="date" :value="hiredate" @change="hiredateChange">
					<view class="uni-input">{{ hiredate }}</view>
				</picker>
				<text v-if="!canEdit" class="value">{{ hiredate }}</text>
			</view>
			<view class="item" @tap="selectRoles">
				<text class="key">隶属角色</text>
				<text class="value">{{ roles }}</text>
			</view>
			<view class="item">
				<text class="key">当前状态</text>
				<picker v-if="canEdit" :value="statusIndex" :range="statusArray" @change="statusChange">{{ statusArray[statusIndex] }}</picker>
				<text v-if="!canEdit" class="value">{{ statusArray[statusIndex] }}</text>
			</view>
		</view>
		<uni-popup ref="popupName" type="dialog">
			<uni-popup-dialog mode="input" title="编辑文字内容" :value="name" placeholder="输入姓名" @confirm="finishName"></uni-popup-dialog>
		</uni-popup>
		<uni-popup ref="popupTel" type="dialog">
			<uni-popup-dialog mode="input" title="编辑文字内容" :value="tel" placeholder="输入电话" @confirm="finishTel"></uni-popup-dialog>
		</uni-popup>
		<uni-popup ref="popupEmail" type="dialog">
			<uni-popup-dialog mode="input" title="编辑文字内容" :value="email" placeholder="输入邮箱" @confirm="finishEmail"></uni-popup-dialog>
		</uni-popup>
		<view class="shadow" v-if="showRoleDialog">
			<scroll-view class="dialog" scroll-y="true">
				<view class="title">角色列表</view>
				<text class="confirm" @tap="confirm">确定</text>
				<checkbox-group @change="roleChange">
					<label class="list-item" v-for="one in roleArray" :key="one.id">
						<checkbox :value="one.id" :checked="roles.split('，').indexOf(one.roleName) != -1" />
						<text>{{ one.roleName }}</text>
					</label>
				</checkbox-group>
			</scroll-view>
		</view>
		<button class="save-btn" v-if="opt != 'read' && checkPermission(['ROOT', 'EMPLOYEE:INSERT', 'EMPLOYEE:UPDATE'])" @tap="save">保存</button>
		<button class="delete-btn" v-if="opt != 'read' && checkPermission(['ROOT', 'EMPLOYEE:DELETE'])" @tap="deleteUser">删除</button>
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
			userId: null,
			temp: '',
			name: '',
			nickname: '',
			photo: '',
			tel: '',
			email: '',
			dept: '',
			hiredate: '',
			roles: '',
			sexArray: ['男', '女'],
			sexIndex: 0,
			deptArray: [],
			deptIndex: 0,
			statusArray: ['在职', '离职'],
			statusIndex: 0,
			showRoleDialog: false,
			roleArray: [],
			selectedRole: [],
			canEdit: false,
			opt: null
		};
	},
	onLoad: function(options) {
		if (options.hasOwnProperty('userId')) {
			this.userId = Number(options.userId);
		}
		if (options.hasOwnProperty('opt')) {
			this.opt = options.opt;
		}
		if (this.opt == 'read') {
			this.canEdit = false;
		} else {
			this.canEdit = this.checkPermission(['ROOT', 'EMPLOYEE:INSERT', 'EMPLOYEE:UPDATE']);
		}
	},
	onShow: function() {
		let that = this;
		if (that.opt == 'insert') {
			let now = new Date();
			let year = now.getFullYear();
			let month = now.getMonth() + 1;
			month = month < 10 ? '0' + month : month;
			let date = now.getDate();
			date = date < 10 ? '0' + date : date;
			that.hiredate = year + '-' + month + '-' + date;
			that.loadDeptData(that);
			that.loadRoleData(that);
			return;
		}

		let url;
		let method;
		if (that.userId == null) {
			(url = this.url.searchUserSelfInfo), (method = 'GET');
		} else {
			if (!that.checkPermission(['ROOT', 'EMPLOYEE:SELECT'])) {
				return;
			}
			(url = this.url.searchUserInfo), (method = 'POST');
		}
		that.ajax(url, method, { userId: that.userId }, function(resp) {
			let result = resp.data.result;
			that.name = result.name;
			that.nickname = result.nickname;
			that.photo = result.photo;
			that.tel = result.tel;
			that.email = result.email;
			that.dept = result.dept;
			that.hiredate = result.hiredate;
			that.roles = result.roles;
			that.sexIndex = that.sexArray.indexOf(result.sex);
			that.statusIndex = that.statusArray.indexOf(result.status);
			that.loadDeptData(that);
			that.loadRoleData(that);
		});
	},
	methods: {
		editName: function() {
			if (!this.canEdit) {
				return;
			}
			this.$refs.popupName.open();
		},
		finishName: function(done, value) {
			if (!this.checkValidName(value, '姓名')) {
				this.name = value;
				done();
			}
		},
		sexChange: function(e) {
			this.sexIndex = e.detail.value;
		},
		deptChange: function(e) {
			this.deptIndex = e.detail.value;
		},
		editTel: function() {
			if (!this.canEdit) {
				return;
			}
			this.$refs.popupTel.open();
		},
		finishTel: function(done, value) {
			if (!this.checkValidTel(value, '手机号码')) {
				this.tel = value;
				done();
			}
		},
		editEmail: function() {
			if (!this.canEdit) {
				return;
			}
			this.$refs.popupEmail.open();
		},
		finishEmail: function(done, value) {
			if (!this.checkValidEmail(value, '邮箱')) {
				this.email = value;
				done();
			}
		},
		statusChange: function(e) {
			this.statusIndex = e.detail.value;
		},
		hiredateChange: function(e) {
			this.hiredate = e.detail.value;
		},
		selectRoles: function() {
			if (!this.canEdit) {
				return;
			}
			this.showRoleDialog = true;
		},
		roleChange: function(e) {
			let that = this;
			that.selectedRole = e.detail.value;
			let temp = [];
			for (let one of that.roleArray) {
				if (that.selectedRole.indexOf(one.id + '') != -1) {
					temp.push(one.roleName);
				}
			}
			that.roles = temp.join('，');
		},
		confirm: function() {
			if (this.roles.length == 0) {
				uni.showToast({
					icon: 'none',
					title: '至少选择一个角色'
				});
			} else {
				this.showRoleDialog = false;
			}
		},
		deleteUser: function() {
			let that = this;
			uni.showModal({
				title: '提示信息',
				content: '是否删除' + that.name + '？',
				success: function(resp) {
					if (resp.confirm) {
						that.ajax(that.url.deleteUserById, 'POST', { id: that.userId }, function(resp) {
							uni.showToast({
								icon: 'success',
								title: '删除成功',
								complete: function() {
									setTimeout(function() {
										uni.navigateBack({
											delta: 1
										});
									}, 2000);
								}
							});
						});
					}
				}
			});
		},
		loadDeptData: function(ref) {
			ref.ajax(ref.url.searchAllDept, 'GET', null, function(resp) {
				let result = resp.data.result;
				let deptArray = [];
				for (let one of result) {
					deptArray.push(one.deptName);
				}
				ref.deptArray = deptArray;

				if (ref.opt == 'insert') {
					ref.deptIndex = 0;
				} else {
					ref.deptIndex = ref.deptArray.indexOf(ref.dept);
				}
			});
		},
		loadRoleData: function(ref) {
			ref.ajax(ref.url.searchAllRole, 'GET', null, function(resp) {
				let result = resp.data.result;
				ref.roleArray = result;
			});
		},
		save: function() {
			let that = this;
			if (that.opt == 'insert') {
				if (
					that.checkValidName(that.name, '姓名') ||
					that.checkValidTel(that.tel, '手机号码') ||
					that.checkValidEmail(that.email, '邮箱') ||
					that.selectedRole.length == 0
				) {
					return;
				}
			}

			uni.showModal({
				title: '提示信息',
				content: '是否保存用户信息？',
				success: function(e) {
					if (e.confirm) {
						let sex = that.sexArray[that.sexIndex];
						let deptName = that.deptArray[that.deptIndex];
						let roles = [];
						for (let one of that.roleArray) {
							if (that.roles.split('，').indexOf(one.roleName) != -1) {
								roles.push(one.id);
							}
						}

						let status = that.statusArray[that.statusIndex];
						status = status == '在职' ? 1 : 2;
						let url;
						//判断添加新员工还是修改员工信息
						if (that.opt == 'insert') {
							url = that.url.insertUser;
						} else {
							url = that.url.updateUserInfo;
						}
						let data = {
							name: that.name,
							sex: sex,
							deptName: deptName,
							tel: that.tel,
							email: that.email,
							hiredate: that.hiredate,
							role: JSON.stringify(roles),
							status: status,
							userId: that.userId
						};
						that.ajax(url, 'POST', data, function(resp) {
							uni.showToast({
								icon: 'success',
								title: '保存成功',
								complete: function() {
									setTimeout(function() {
										uni.navigateBack({
											delta: 1
										});
									}, 2000);
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
@import url('user_info.less');
</style>
