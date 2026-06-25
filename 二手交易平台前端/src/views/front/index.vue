<template>
	<div>
		<el-container>
			<el-header style="padding: 0 0;position: fixed;width: 100vw;min-width: 1400px; z-index: 99;">
				<div class="nav-bar">
					<el-menu style="border-bottom:none;width: 1400px;margin: 0 auto;" :default-active="$route.path"
						class="el-menu-demo" mode="horizontal" background-color="transparent" text-color="#ffffff"
						active-text-color="#ffd04b" router>
						<el-menu-item class="logo-item" index="/shop">
							<img src="../../assets/logo.png" alt="" style="width: 22px;" />
							<span class="logo-text">二手集市</span>
						</el-menu-item>
						<el-submenu v-if="student_user.length!=0" index="title"
							style="float: right;margin-left: 60px;">
							<template slot="title">
								<span>👋 {{student_user.susername}}</span>
								<span style="margin-left: 5px;">
									<el-avatar size="medium" v-if="student_user.savatar==null" style="background: linear-gradient(135deg, #ffd04b, #ff9a76);">
										{{student_user.susername}}</el-avatar>
									<el-avatar size="medium" v-else :src="student_user.savatar"></el-avatar>
								</span>
							</template>
							<el-menu-item index="/my_personal">👤 个人信息</el-menu-item>
							<el-menu-item index="/my_leave">💬 我的留言</el-menu-item>
							<el-menu-item index="" @click="exit">
								<span v-loading.fullscreen.lock="fullscreenLoading">🚪 退出</span>
							</el-menu-item>
						</el-submenu>
						<el-menu-item v-if="student_user.length==0" style="float: right;" index="/login">🔧 管理员后台
						</el-menu-item>
						<el-menu-item v-if="student_user.length==0" style="float: right;margin-left: 60px;"
							index="/s_login">🔑 登录 / 注册</el-menu-item>
						<el-menu-item style="float: right;" index="/my_college">⭐ 收藏夹</el-menu-item>
						<el-menu-item style="float: right;" index="/my_order">📋 我的订单</el-menu-item>
						<el-menu-item style="float: right;" index="/my_goods">📦 我的商品</el-menu-item>
						<el-menu-item style="float: right;" index="/release">🚀 发布商品</el-menu-item>
					</el-menu>
				</div>
			</el-header>
			<el-main style="min-width: 1400px;">
				<router-view @refreshUser="getUser" />
			</el-main>
		</el-container>
		<el-backtop :bottom="100" :visibility-height="50"></el-backtop>
	</div>

</template>

<script>
	export default {
		data() {
			return {
				student_user: localStorage.getItem("student_user") ? JSON.parse(localStorage.getItem("student_user")) : [],
				fullscreenLoading: false,
			};
		},
		watch: {
			$route() {
				let i = this.$route.path;
				setTimeout(() => { //路由跳转
					this.$refs.menu = i
				}, 100)
			}
		},
		created() {
			localStorage.removeItem("user") //清空缓存
		},
		methods: {
			exit() {
				const loading = this.$loading({
					lock: true,
					text: '请稍等',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				setTimeout(() => {
					this.$router.push('/shop')
					/* this.$notify({
					          title: '',
					          message: '退出成功',
					          type: 'success'
					        }); */
					this.student_user = []
					this.$message.success("退出成功")
					localStorage.removeItem("student_user") //清空缓存
					loading.close();
				}, 1000);
			},
			getUser() {
				this.student_user = JSON.parse(localStorage.getItem("student_user"))
			}
		}
	}
</script>

<style scoped>
	.el-card__body, .el-main {
	    padding: 0px;
	}
	.input-with-select .el-input-group__prepend {
		background-color: #fff;
	}
	.nav-bar {
		line-height: 60px;
		display: flex;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 40%, #f093fb 100%);
		box-shadow: 0 4px 24px rgba(102, 126, 234, 0.35);
	}
	.nav-bar .el-menu-demo {
		background: transparent !important;
	}
	.nav-bar .el-menu-item, .nav-bar .el-submenu__title {
		transition: all 0.2s ease;
	}
	.nav-bar .el-menu-item:hover, .nav-bar .el-submenu__title:hover {
		background: rgba(255,255,255,0.1) !important;
	}
	.logo-item {
		font-size: 18px !important;
		font-weight: 700 !important;
		letter-spacing: 0.03em;
	}
	.logo-text {
		background: linear-gradient(90deg, #ffd04b, #ff9a76);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
		background-clip: text;
		margin-left: 6px;
	}
</style>
