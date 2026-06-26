<template>
	<div style="margin-top: 100px;">
		<PageHeader v-if="student_user.length!=0" title="我的订单" />
		<EmptyState v-if="student_user.length==0" description="当前未登录">
			<template slot="action">
				<el-button type="primary" size="medium" @click="s_login">去登录</el-button>
			</template>
		</EmptyState>
		<div v-else style="margin: 0 auto; max-width: 1200px; width: 100%; padding: 0 16px;">
			<el-tabs v-model="activeName" type="border-card">
			    <el-tab-pane label="我的购买" name="first">
					<div style="display: flex;align-items: center;justify-content: center;padding-top: 40px;">
						<el-input style="width: 100%; max-width: 600px;" clearable size="medium" placeholder="请输入订单号" v-model="oid"
							class="input-with-select">
							<el-button slot="append" icon="el-icon-search" @click="load1"></el-button>
						</el-input>
					</div>
					<el-row style="margin: 0 auto;padding-top: 50px;padding-bottom: 80px;">
						<div style="padding-left: 30px;padding-bottom: 20px;">
							<EmptyState v-if="total==0" />
							<span v-else style="color: #949494;">共发现 {{total}} 个订单</span>
						</div>
						<OrderCard v-for="order_list in order_list" :key="order_list.oid" :order="order_list" @click="order_details" />
					</el-row>
				</el-tab-pane>
			    <el-tab-pane label="我的售出" name="second">
					<div style="display: flex;align-items: center;justify-content: center;padding-top: 40px;">
						<el-input style="width: 100%; max-width: 600px;" clearable size="medium" placeholder="请输入订单号" v-model="oid2"
							class="input-with-select">
							<el-button slot="append" icon="el-icon-search" @click="load2"></el-button>
						</el-input>
					</div>
					<el-row style="margin: 0 auto;padding-top: 50px;padding-bottom: 80px;">
						<div style="padding-left: 30px;padding-bottom: 20px;">
							<EmptyState v-if="total2==0" />
							<span v-else style="color: #949494;">共发现 {{total2}} 个订单</span>
						</div>
						<OrderCard v-for="order_list2 in order_list2" :key="order_list2.oid" :order="order_list2" @click="order_details2" />
					</el-row>
				</el-tab-pane>
			  </el-tabs>

		</div>
		<transition v-if="isLoading" name="fade">
			<div  class="loading"></div>
		</transition>
	</div>
</template>

<script>
	import PageHeader from '/src/components/PageHeader.vue'
	import EmptyState from '/src/components/EmptyState.vue'
	import OrderCard from '/src/components/OrderCard.vue'
	export default {
		components: {
			PageHeader,
			EmptyState,
			OrderCard,
		},
		data() {
			return {
				isLoading: true,
				student_user: localStorage.getItem("student_user") ? JSON.parse(localStorage.getItem("student_user")) : [],
				order_list: [],
			    oid: "",
				total: 0,
				order_list2: [],
				oid2: "",
				total2: 0,
				status: "",
				activeName: 'first'
			}
		},
		created() {
			if(this.student_user.length!=0)
			{
				this.load1()
				this.load2()
			}
			else
			{
				this.isLoading = false
			}

		},
		methods: {
			s_login() {
				this.$router.push("/s_login")
			},
			load1() {
				this.request.get("/order/getOneOrderBUY?BUYSid=" + this.student_user.sid + "&Oid=" + this.oid).then(
				res => {
					this.order_list = res.data
					this.total = res.total

					this.isLoading = false
				})
			},
			load2(){
				this.request.get("/order/getOneOrderSELL?SELLSid=" + this.student_user.sid + "&Oid=" + this.oid2).then(
				res => {
					this.order_list2 = res.data
					this.total2 = res.total

					this.isLoading = false
				})
			},
			order_details(id) {
				this.$router.push({
					path: '/my_buyorder_details',
					query: {
						Oid: id
					}
				});
			},
			order_details2(id) {
				this.$router.push({
					path: '/my_sellorder_details',
					query: {
						Oid: id
					}
				});
			},
		},
	}
</script>

<style>
	.fade-enter-active, .fade-leave-active {
	  transition: opacity .5s;
	}
	.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
	  opacity: 0;
	}

	.loading {
	  position: fixed;
	  left: 0;
	  top: 0;
	  background: url('../../assets/loading (2).gif') center center no-repeat #fff;
	  width: 100vw;
	  height: 100vh;

	}
</style>
