<template>
	<view class="page" v-if="checkPermission(['ROOT', 'EMPLOYEE:SELECT'])">
		<view class="header">
			<icon type="search" size="15" class="search-icon"></icon>
			<input type="text" confirm-type="search" v-model="keyword" @confirm="search" placeholder="输入要检索的员工姓名" maxlength="15" class="keyword" />
			<button class="btn" @tap="toPage" v-if="checkPermission(['ROOT', 'EMPLOYEE:INSERT'])">添加员工</button>
		</view>
		<view v-for="dept in list" :key="dept.id">
			<view class="list-title">{{ dept.deptName }}（{{ dept.count }}人）</view>
			<uni-list v-if="dept.count > 0">
				<uni-list-item v-for="emp in dept.members" :title="emp.name" :key="emp.userId" link :to="'user_info?userId=' + emp.userId"></uni-list-item>
			</uni-list>
		</view>
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
			list: [],
			keyword: null
		};
	},
	onShow: function() {
		this.loadData(this);
	},

	methods: {
		loadData: function(ref) {
			ref.ajax(ref.url.searchUserGroupByDept, 'POST', { keyword: ref.keyword }, function(resp) {
				let result = resp.data.result;
				ref.list = result;
			});
		},
		search: function() {
			if (this.keyword == '') {
				this.keyword = null;
			}
			if (this.keyword != null && /^[\u4e00-\u9fa5]{1,15}$/.test(this.keyword) == false) {
				uni.showToast({
					icon: 'none',
					title: '关键字必须是15个以内汉字'
				});
			} else {
				this.loadData(this);
			}
		},
		toPage: function() {
			uni.navigateTo({
				url: 'user_info?opt=insert'
			});
		}
	}
};
</script>

<style lang="less">
@import url('user_list.less');
</style>
