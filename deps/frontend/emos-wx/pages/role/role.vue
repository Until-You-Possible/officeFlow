<template>
	<view class="page" v-if="checkPermission(['ROOT', 'ROLE:SELECT'])">
		<view v-for="one in list" :key="one.moduleName">
			<view class="list-title">{{ one.moduleName }}</view>
			<uni-list v-if="one.action.length > 0">
				<uni-list-item
					v-for="action in one.action"
					:title="action.actionName"
					:key="action"
					:show-switch="true"
					:switchChecked="action.selected"
					@switchChange="switchChange"
					:dataId="action.id"
					:disabled="action.acquiescent"
				></uni-list-item>
			</uni-list>
		</view>
		<button class="btn" @tap="save" v-if="checkPermission(['ROOT', 'ROLE:INSERT', 'ROLE:UPDATE'])">保存</button>
	</view>
</template>

<script>
import uniList from '@/components/uni-list/uni-list.vue';
import uniListItem from '@/components/uni-list-item/uni-list-item.vue';
import uniPopup from '@/components/uni-popup/uni-popup.vue';

export default {
	components: {
		uniList,
		uniListItem
	},
	data() {
		return {
			opt: null,
			roleName: null,
			list: [],
			id: 0,
			selected: {}
		};
	},
	methods: {
		switchChange: function(e) {
			let id = e.target.dataset.id;
			if (e.detail.value) {
				this.selected[id] = true;
			} else {
				delete this.selected[id];
			}
		},
		save: function() {
			let that = this;
			let url;
			if (that.opt == 'insert') {
				url = that.url.insertRole;
			} else {
				url = that.url.updateRolePermissions;
			}

			let data = {
				id: that.id,
				roleName: that.roleName,
				permissions: '[' + Object.keys(that.selected) + ']'
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
	},
	onShow: function() {
		
		let that = this;
		let url;
		let method;
		if (that.opt == 'insert') {
			url = that.url.searchAllPermission;
			method = 'GET';
		} else {
			url = that.url.searchRoleOwnPermission;
			method = 'POST';
		}
		that.ajax(url, method, { id: that.id }, function(resp) {
			that.list = resp.data.result;
			for (let one of that.list) {
				console.log(one)
				for (let action of one.action) {
					if (action.selected == true) {
						that.selected[action.id] = true;
					}
				}
			}
		});
	},
	onLoad: function(options) {
		this.opt = options.opt;
		if (options.hasOwnProperty('roleName')) {
			this.roleName = options.roleName;
		}
		if (options.hasOwnProperty('id')) {
			this.id = options.id;
		}
	}
};
</script>

<style lang="less">
@import url('role.less');
</style>
