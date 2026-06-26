<template>
	<div>
		<el-container>
			<el-header style="padding: 0; position: fixed; width: 100%; z-index: 99;">
				<div class="nav-bar">
					<div class="mobile-menu-toggle" @click="mobileMenuOpen = !mobileMenuOpen">
						<i :class="mobileMenuOpen ? 'el-icon-close' : 'el-icon-menu'"></i>
					</div>
					<el-menu style="border-bottom:none; max-width: 1200px; margin: 0 auto; width: 100%; padding: 0 16px;" :default-active="$route.path"
						class="el-menu-demo" :class="{ 'mobile-open': mobileMenuOpen }" mode="horizontal" background-color="transparent" text-color="#ffffff"
						active-text-color="#ffd04b" router>
						<el-menu-item class="logo-item" index="/shop">
							<img src="../../assets/logo.png" alt="" style="width: 22px;" />
							<span class="logo-text">二手集市</span>
						</el-menu-item>
						<el-menu-item index="/shop">🏠 商城</el-menu-item>
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
			<el-main style="padding-top: 60px;">
				<router-view @refreshUser="getUser" />
			</el-main>
		</el-container>
		<el-backtop :bottom="100" :visibility-height="50"></el-backtop>
		<AppFooter />
	</div>

</template>

<script>
	import AppFooter from '/src/components/AppFooter.vue'
	export default {
		components: { AppFooter },
		data() {
			return {
				student_user: localStorage.getItem("student_user") ? JSON.parse(localStorage.getItem("student_user")) : [],
				fullscreenLoading: false,
				mobileMenuOpen: false,
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
		background: var(--color-primary);
		box-shadow: 0 4px 24px var(--color-primary-shadow);
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
		color: #ffd04b;
		margin-left: 6px;
	}
	/* ── 移动端汉堡菜单 ── */
	.mobile-menu-toggle {
		display: none;
		position: absolute;
		right: 16px;
		top: 18px;
		font-size: 22px;
		color: #fff;
		cursor: pointer;
		z-index: 101;
	}
	@media (max-width: 768px) {
		.mobile-menu-toggle {
			display: block;
		}
		.el-menu-demo .el-menu-item,
		.el-menu-demo .el-submenu {
			display: none;
		}
		.el-menu-demo .logo-item {
			display: block !important;
		}
		.el-menu-demo.mobile-open .el-menu-item,
		.el-menu-demo.mobile-open .el-submenu {
			display: block;
			float: none !important;
			width: 100%;
		}
		.el-menu-demo {
			height: auto !important;
			flex-direction: column;
		}
	}
</style>
