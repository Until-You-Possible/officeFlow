<template>
	<view><uni-indexed-list :options="list" :showSelect="false" @click="clickContact"></uni-indexed-list></view>
</template>

<script>
import uniIndexedList from '@/components/uni-indexed-list/uni-indexed-list.vue';
export default {
	components: { uniIndexedList },
	data() {
		return {
			list: []
		};
	},
	methods: {
		clickContact: function(e) {
			let that = this;
			let name = e.item.name;
			let key = e.item.key;
			let tel;
			for (let one of that.list) {
				if (one.letter == key) {
					let i = one.data.indexOf(name);

					tel = one.tel[i];
					break;
				}
			}
			uni.showModal({
				title: '提示信息',
				content: '是否要打电话给' + name + '？',
				success: function(res) {
					if (res.confirm) {
						uni.makePhoneCall({
							phoneNumber: tel
						});
					}
				}
			});
		}
	},
	onShow: function() {
		let that = this;
		that.list.length = 0;
		that.ajax(that.url.searchUserContactList, 'GET', null, function(resp) {
			let result = resp.data.result;
			for (let key in result) {
				let nameArray = [];
				let telArray = [];
				for (let contact of result[key]) {
					let name = contact.name;
					let tel = contact.tel;
					let dept = contact.dept;
					dept = dept != '' ? '（' + dept + '）' : '';
					nameArray.push(`${name} ${dept}`);
					telArray.push(tel);
				}
				that.list.push({
					letter: key,
					data: nameArray,
					tel: telArray
				});
			}
		});
	}
};
</script>

<style lang="less">
@import url('contacts.less');
</style>
