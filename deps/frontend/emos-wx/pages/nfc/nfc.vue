<template>
	<view>
		<image src="../../static/NFC.png" mode="widthFix" class="img"></image>
		<view class="desc">请把手机靠近读卡器或者NFC标签</view>
	</view>
</template>

<script>
export default {
	data() {
		return {};
	},
	methods: {
		Uint8ArrayToString: function(fileData) {
			var dataString = '';
			for (let i = 0; i < fileData.length; i++) {
				dataString += String.fromCharCode(fileData[i]);
			}
			return dataString;
		}
	},
	onLoad: function() {
		let that = this;

		let NFCAdapter = wx.getNFCAdapter();
		NFCAdapter.startDiscovery({
			success: function(resp) {
				// console.log('请将设备放入识别区NFC');
			},
			fail: function(error) {
				// console.error('刷新重试');
			}
		});
		NFCAdapter.onDiscovered(function(resp) {
			console.log('注册贴卡监听回调');
			let NfcA = NFCAdapter.getNfcA();
			NfcA.connect({
				success: function() {
					// console.log('已连接');
					let dataBytes = [0x3a, 0x07, 0x14];
					let dataBuffer = new Uint8Array(dataBytes).buffer;
					NfcA.transceive({
						data: dataBuffer,
						success: function(resp) {
							let temp = new Uint8Array(resp.data);
							temp = temp.slice(2);
							temp = temp.filter(function(one) {
								return one != 0 && one != 254;
							});
							temp = that.Uint8ArrayToString(temp);
							let module=temp.split("#")[0]
							let id=temp.split("#")[1]
							console.log(temp)
							if(module=="MRIF"){
								uni.navigateTo({
									url:"../../meeting/meeting_room?meetingRoomId="+id
								})
							}
							if(module=="MTCK"){
								uni.navigateTo({
									url:"../../meeting/meeting_checkin?meetingRoomId="+id
								})
							}
							
						},
						fail: function(error) {
							console.log('发送数据失败', error);
						},
						complete: function() {
							NfcA.close()
						}
					});
				}
			});
		});
	}
};
</script>

<style lang="less">
@import url('nfc.less');
</style>
