<template>
	<!-- 获取一个数组，结构为[{date:'时间',info:'内容内容'}] -->
	<!-- @click事件返回点击标签元素的索引值 第一项为0 -->
	<view class="bg">
		<view class="steps">
			<view class="steps_item" v-for="(i, index) in infoList">
				<view class="s_r">
					<view class="line" :style="{backgroundColor:index != 0?backgroundColor:'rgba(0,0,0,0)'}"></view>
					<view class="index" :style="{backgroundColor:backgroundColor,color:color}">
						{{ index + 1 }}
					</view>
					<view class="line" :style="{backgroundColor:index != infoList.length-1?backgroundColor:'rgba(0,0,0,0)'}"></view>
				</view>
				<view class="s_l">
					<view class="info_item" @tap="topage(index)">
						<text>{{ i.date }}</text>
						<view :style="{WebkitLineClamp:lineNum!=0?lineNum:''}">{{ i.info }}</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'YSteps',
		props: {
			infoList: {
				type: Array,
				default: []
			},
			color: {
				type: String,
				default: '#fff'
			},
			backgroundColor: {
				type: String,
				default: '#ff3838'
			},
			lineNum: {
				type: Number,
				default: 0
			}
		},
		data() {
			return {};
		},
		onLoad(e) {
			//获取列表
		},
		methods: {
			topage(e) {
				this.$emit('click', e);
			}
		}
	};
</script>

<style lang="scss" scoped>
	page {
		background-color: #fff;
	}

	.bg {
		margin: 20upx 0;
		background-color: #fff;
		display: flex;
	}

	.steps {
		display: flex;
		flex-direction: column;
		flex-grow: 1;
		.steps_item {
			display: flex;

			.s_r {
				padding-top: 0;
				padding-bottom: 0;
				padding-left: 20rpx;
				padding-right: 30rpx;
				display: flex;
				flex-direction: column;
				align-items: center;

				.line {
					flex: 1;
					width: 5rpx;
					background-color: #fff;
				}

				.index {
					width: 50rpx;
					height: 50rpx;
					font-size: 28rpx;
					text-align: center;
					line-height: 50rpx;
					border-radius: 25rpx;
				}
			}

			.s_l {
				display: flex;
				flex-direction: column;
				padding: 20rpx 0;
				flex-grow: 1;
				.info_item {
					background-color: #FFFFFF;
					margin-right: 30upx;
					border-radius: 10upx;
					display: flex;
					flex-direction: column;
					justify-content: center;
					padding: 30upx;
					// box-shadow: 0 10rpx 30rpx #ddd;
					// border: solid 1rpx #f5f5f5;
					background-color: #f5f5f5;

					text {
						font-size: 32rpx;
						font-weight: 500;
						color: rgba(51, 51, 51, 1);
						line-height: 36rpx;
						margin-bottom: 10rpx;
					}

					view {
						font-size: 28rpx;
						font-weight: 400;
						color: #999;
						overflow: hidden;
						text-overflow: ellipsis;
						display: -webkit-box;

						flex-direction: column;
					}
				}

				// .info_item:active {
				// 	background-color: #f4f4f4;
				// }
			}
		}
	}

</style>
