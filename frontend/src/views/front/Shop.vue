<template>

	<div style="margin-top: 60px;">
		<!-- 校园横幅 -->
		<div class="campus-banner">
			<!-- 装饰浮动元素 -->
			<div class="banner-float banner-float-1">📚</div>
			<div class="banner-float banner-float-2">💡</div>
			<div class="banner-float banner-float-3">🎓</div>
			<div class="banner-float banner-float-4">✨</div>
			<div class="banner-float banner-float-5">🚀</div>
			<div class="banner-float banner-float-6">💻</div>
			<div class="campus-banner-inner">
				<div class="campus-banner-text">
					<h1>🛒 校园闲置<span class="highlight">焕发新生</span></h1>
					<p>课本 · 电子 · 生活好物 — 在同校同学之间温暖流转 💫</p>
					<div class="banner-tags">
						<span class="banner-tag">🔥 好物捡漏</span>
						<span class="banner-tag">🤝 校友信任</span>
						<span class="banner-tag">⚡ 闪电交易</span>
					</div>
				</div>
				<div class="campus-banner-stats">
					<div class="banner-stat">
						<span class="banner-stat-icon">📦</span>
						<span class="banner-stat-num">{{total}}</span>
						<span class="banner-stat-label">件好物在售</span>
					</div>
				</div>
			</div>
		</div>

		<!-- 搜索 + 排序栏 -->
		<div class="search-bar">
			<div class="search-bar-inner">
				<el-input
					style="width: 480px;"
					clearable
					size="medium"
					placeholder="搜一搜课本、电子、生活好物…"
					v-model="gname"
					class="search-input"
					@keyup.enter.native="load"
					@input="onSearchInput"
				>
					<el-button slot="append" icon="el-icon-search" @click="load">搜索</el-button>
				</el-input>
				<div class="search-sort">
					<span class="sort-label">排序：</span>
					<el-radio-group v-model="sortBy" size="small" @change="handleSortChange">
						<el-radio-button label="default">默认</el-radio-button>
						<el-radio-button label="price_asc">价格↑</el-radio-button>
						<el-radio-button label="price_desc">价格↓</el-radio-button>
						<el-radio-button label="newest">最新</el-radio-button>
					</el-radio-group>
				</div>
			</div>
		</div>

		<!-- 分类标签 -->
		<div class="category-bar">
			<div class="category-bar-inner">
				<span
					v-for="cat in categories"
					:key="cat.value"
					:class="['category-tag', { 'category-tag--active': activeCategory === cat.value }]"
					@click="handleCategoryChange(cat.value)"
				>{{cat.label}}</span>
			</div>
		</div>

		<!-- 商品网格 -->
		<div class="goods-section">
			<div class="goods-section-inner">
				<div class="goods-header">
					<EmptyState v-if="total===0 && !isLoading" />
					<span v-else-if="total>0" class="goods-count">
						共 <b>{{total}}</b> 个商品
						<span v-if="activeCategory"> · {{getCategoryLabel(activeCategory)}}</span>
					</span>
				</div>

				<!-- 骨架屏 -->
				<el-row v-if="isLoading && total===0" :gutter="16" class="goods-grid">
					<el-col :xs="12" :sm="8" :md="6" :lg="4" v-for="n in 12" :key="'skeleton-'+n" class="goods-col">
						<SkeletonCard />
					</el-col>
				</el-row>

				<el-row v-if="total>0" :gutter="16" class="goods-grid">
					<el-col
						:xs="12" :sm="8" :md="6" :lg="4"
						v-for="goods_list in goods_list"
						:key="goods_list.gid"
						class="goods-col"
					>
						<ProductCard :goods="goods_list" @click="goods_details" />
					</el-col>
				</el-row>
			</div>
		</div>

		<!-- 分页 -->
		<div v-if="total>0" class="pagination-bar">
			<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="pageNum"
				background
				:page-sizes="[12, 24, 48]"
				:page-size="pageSize"
				layout="total, sizes, prev, pager, next, jumper"
				:total="total"
			/>
		</div>

		<transition name="fade">
			<loading v-if="isLoading && total>0"></loading>
		</transition>

	</div>
</template>

<script>
	import Loading from '/src/components/loading.vue'
	import ProductCard from '/src/components/ProductCard.vue'
	import EmptyState from '/src/components/EmptyState.vue'
	import SkeletonCard from '/src/components/SkeletonCard.vue'
	export default {
		components:{
			Loading,
			ProductCard,
			EmptyState,
			SkeletonCard,
		},
		data() {
			return {
				isLoading: true,
				goods_list: [],
				total: 0,
				pageNum: 1,
				pageSize: 12,
				gname: "",
				sortBy: "default",
				activeCategory: "",
				categories: [
					{ label: "全部", value: "" },
					{ label: "教辅教材", value: "教材" },
					{ label: "电子产品", value: "电子" },
					{ label: "生活用品", value: "生活" },
					{ label: "服饰鞋包", value: "服饰" },
					{ label: "运动户外", value: "运动" },
					{ label: "其他好物", value: "其他" },
				],
			}
		},
		created() {
			this.load()
		},
		methods: {
			load() {
				this.isLoading = true
				this.request.get("/goods/getGoodsPage", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						Gname: this.gname,
						sortBy: this.sortBy,
						category: this.activeCategory
					}
				}).then(res => {
					this.goods_list = res.data || []
					this.total = res.total || 0
					this.isLoading = false
				}).catch(() => {
					this.goods_list = []
					this.total = 0
					this.isLoading = false
				})
			},
			goods_details(id) {
				this.$router.push({
					path: '/goods_details',
					query: {
						gid: id
					}
				});
				
				/* const routeData = this.$router.resolve({
					path: '/goods_details',
					query: {
						gid: id
					}
				})
				window.open(routeData.href, '_blank') */
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize
				this.load()
			},
			handleCurrentChange(pageNum) {
				this.pageNum = pageNum
				this.load()
			},
			onSearchInput() {
				// 手动输入搜索时，取消分类筛选
				if (this.activeCategory !== '') {
					this.activeCategory = ''
				}
			},
			handleSortChange() {
				this.pageNum = 1
				this.load()
			},
			handleCategoryChange(value) {
				this.activeCategory = value
				this.pageNum = 1
				// 分类筛选和关键词搜索互相独立，不再覆盖gname
				this.load()
			},
			getCategoryLabel(value) {
				const cat = this.categories.find(c => c.value === value)
				return cat ? cat.label : value
			}
		},
	}
</script>

<style>
/* ── 校园横幅 ── */
.campus-banner {
	background: linear-gradient(135deg, var(--color-primary) 0%, var(--color-primary-deep) 100%);
	margin: 0 auto;
	max-width: 1200px;
	border-radius: 20px;
	overflow: hidden;
	position: relative;
	box-shadow: 0 20px 60px var(--color-primary-shadow);
}
.campus-banner-inner {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 52px 56px;
	position: relative;
	z-index: 1;
}
/* 浮动动画元素 */
.banner-float {
	position: absolute;
	font-size: 40px;
	opacity: 0.15;
	z-index: 0;
	animation: floatAround 8s ease-in-out infinite;
	pointer-events: none;
}
.banner-float-1 { top: 10%; left: 5%; animation-delay: 0s; font-size: 48px; }
.banner-float-2 { top: 60%; left: 12%; animation-delay: 1.2s; font-size: 36px; }
.banner-float-3 { top: 15%; right: 15%; animation-delay: 2.4s; font-size: 44px; }
.banner-float-4 { top: 70%; right: 8%; animation-delay: 0.6s; font-size: 38px; }
.banner-float-5 { top: 40%; left: 45%; animation-delay: 1.8s; font-size: 34px; }
.banner-float-6 { top: 20%; right: 35%; animation-delay: 3s; font-size: 42px; }
@keyframes floatAround {
	0%, 100% { transform: translateY(0) rotate(0deg); }
	33% { transform: translateY(-15px) rotate(5deg); }
	66% { transform: translateY(10px) rotate(-5deg); }
}
.campus-banner-text h1 {
	color: #fff;
	font-size: 34px;
	font-weight: 800;
	margin: 0 0 8px 0;
	letter-spacing: 0.03em;
}
.campus-banner-text h1 .highlight {
	background: linear-gradient(90deg, #ffd04b, #ff9a76);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	background-clip: text;
}
.campus-banner-text p {
	color: rgba(255,255,255,0.85);
	font-size: 16px;
	margin: 0 0 14px 0;
}
.banner-tags {
	display: flex; gap: 10px;
}
.banner-tag {
	display: inline-block;
	padding: 5px 14px;
	background: rgba(255,255,255,0.18);
	backdrop-filter: blur(10px);
	border-radius: 20px;
	font-size: 12px;
	color: rgba(255,255,255,0.9);
	border: 1px solid rgba(255,255,255,0.2);
}
.banner-stat {
	text-align: center;
	background: rgba(255,255,255,0.15);
	border-radius: 16px;
	padding: 20px 36px;
	backdrop-filter: blur(8px);
	border: 1px solid rgba(255,255,255,0.2);
}
.banner-stat-icon {
	display: block; font-size: 28px; margin-bottom: 4px;
}
.banner-stat-num {
	display: block;
	color: #ffd04b;
	font-size: 40px;
	font-weight: 900;
	line-height: 1.2;
}
.banner-stat-label {
	color: rgba(255,255,255,0.8);
	font-size: 14px;
}

/* ── 搜索栏 ── */
.search-bar {
	display: flex;
	justify-content: center;
	padding: 24px 0 0 0;
}
.search-bar-inner {
	display: flex;
	align-items: center;
	gap: 20px;
	max-width: 1200px;
	width: 100%;
	padding: 0 20px;
	justify-content: center;
	flex-wrap: wrap;
}
.search-input .el-input__inner {
	border-radius: 24px 0 0 24px;
	border: 2px solid #e8e8e8;
	transition: border-color 0.3s;
}
.search-input .el-input__inner:focus {
	border-color: var(--color-primary);
}
.search-input .el-input-group__append {
	border-radius: 0 24px 24px 0;
	background: var(--color-primary);
	border: none;
	color: #fff;
	font-weight: 600;
}
.search-input .el-input-group__append:hover {
	background: var(--color-primary-deep);
}
.sort-label {
	color: #999;
	font-size: 13px;
	margin-right: 4px;
}

/* ── 分类标签 ── */
.category-bar {
	display: flex;
	justify-content: center;
	padding: 18px 0 0 0;
}
.category-bar-inner {
	display: flex;
	gap: 10px;
	flex-wrap: wrap;
	justify-content: center;
	max-width: 1200px;
	padding: 0 20px;
}
.category-tag {
	display: inline-block;
	padding: 8px 20px;
	border-radius: 22px;
	font-size: 13px;
	color: #666;
	background: #f0f0f5;
	cursor: pointer;
	transition: all 0.25s ease;
	user-select: none;
	font-weight: 500;
}
.category-tag:hover {
	color: var(--color-primary);
	background: var(--color-primary-light);
	transform: translateY(-2px);
	box-shadow: 0 4px 12px var(--color-primary-shadow);
}
.category-tag--active {
	color: #fff;
	background: var(--color-primary);
	box-shadow: 0 4px 15px var(--color-primary-shadow);
}
.category-tag--active:hover {
	color: #fff;
	background: var(--color-primary-deep);
}

/* ── 商品区域 ── */
.goods-section {
	background: transparent;
	margin-top: 24px;
	padding: 36px 0 52px 0;
	min-height: 400px;
}
.goods-section-inner {
	max-width: 1240px;
	margin: 0 auto;
	padding: 0 20px;
}
.goods-header {
	padding: 0 0 24px 0;
}
.goods-count {
	color: #999;
	font-size: 14px;
}
.goods-count b {
	color: var(--color-primary);
	font-size: 18px;
}

/* ── 商品卡片 ── */
.goods-col {
	margin-bottom: 22px;
	animation: fadeInUp 0.5s ease both;
}
.goods-col:nth-child(1) { animation-delay: 0.05s; }
.goods-col:nth-child(2) { animation-delay: 0.1s; }
.goods-col:nth-child(3) { animation-delay: 0.15s; }
.goods-col:nth-child(4) { animation-delay: 0.2s; }
.goods-col:nth-child(5) { animation-delay: 0.25s; }
.goods-col:nth-child(6) { animation-delay: 0.3s; }
@keyframes fadeInUp {
	from { opacity: 0; transform: translateY(20px); }
	to { opacity: 1; transform: translateY(0); }
}
.goods-card {
	background: #fff;
	border-radius: 16px;
	overflow: hidden;
	cursor: pointer;
	transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
	box-shadow: none;
	border: 1px solid var(--color-divider);
}
.goods-card:hover {
	transform: translateY(-6px);
	box-shadow: var(--shadow-card-hover);
	border-color: var(--color-primary);
}
.goods-card-img {
	position: relative;
	width: 100%;
	padding-top: 100%;
	overflow: hidden;
	background: linear-gradient(135deg, #e0f5f5, #e8f5f0);
}
.goods-card:hover .goods-img {
	transform: scale(1.06);
}
.goods-img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	transition: transform 0.4s ease;
}
.goods-img-fallback img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
.goods-card-body {
	padding: 14px 16px 16px 16px;
}
	.goods-category-row {
		margin-bottom: 6px;
	}
	.goods-category-tag {
		display: inline-block;
		padding: 2px 8px;
		border-radius: 10px;
		font-size: 11px;
		color: #fff;
		font-weight: 500;
	}
.goods-name {
	font-size: 14px;
	font-weight: 600;
	color: #2c3e50;
	margin: 0 0 8px 0;
	line-height: 1.5;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
	overflow: hidden;
	min-height: 20px;
}
.goods-meta {
	margin-bottom: 10px;
}
.goods-seller {
	font-size: 12px;
	color: #888;
	background: var(--color-primary-light);
	padding: 3px 10px;
	border-radius: 10px;
}
.goods-seller i {
	margin-right: 3px;
	color: var(--color-primary);
}
.goods-price-row {
	display: flex;
	align-items: baseline;
	justify-content: space-between;
}
.goods-price {
	font-size: 22px;
	font-weight: 800;
	color: var(--color-accent);
}
.goods-price::before {
	content: '¥';
	font-size: 14px;
	font-weight: 600;
	margin-right: 2px;
}

/* ── 分页 ── */
.pagination-bar {
	display: flex;
	justify-content: center;
	padding: 24px 0 40px 0;
	background: transparent;
}

/* ── 过渡动画 ── */
.fade-enter-active, .fade-leave-active {
	transition: opacity .3s;
}
.fade-enter, .fade-leave-to {
	opacity: 0;
}

/* ── 响应式 ── */
/* 4K+ */
@media (min-width: 1600px) {
	.goods-section-inner { max-width: 1600px; }
}
/* 平板横屏 / 小桌面 */
@media (max-width: 1200px) {
	.campus-banner-inner { padding: 36px 32px; }
	.campus-banner-text h1 { font-size: 28px; }
	.banner-stat { padding: 16px 24px; }
	.banner-stat-num { font-size: 32px; }
	.search-bar-inner { max-width: 100%; padding: 0 16px; }
	.search-input { max-width: 360px; }
}
/* 平板竖屏 */
@media (max-width: 992px) {
	.campus-banner-inner { flex-direction: column; text-align: center; padding: 28px 20px; }
	.campus-banner-text h1 { font-size: 24px; }
	.banner-stat { margin-top: 16px; }
	.search-bar-inner { flex-direction: column; gap: 12px; }
	.search-input { max-width: 100%; }
	.goods-section-inner { padding: 0 12px; }
}
/* 手机横屏 */
@media (max-width: 768px) {
	.campus-banner { border-radius: 12px; }
	.campus-banner-inner { padding: 24px 16px; }
	.banner-float { display: none; }
	.campus-banner-text p { font-size: 14px; }
	.banner-tags { flex-wrap: wrap; gap: 6px; }
	.banner-tag { font-size: 11px; padding: 4px 10px; }
	.category-bar-inner { gap: 6px; }
	.category-tag { padding: 6px 14px; font-size: 12px; }
	.goods-col { margin-bottom: 12px; }
	.goods-card-body { padding: 10px 12px 12px; }
	.goods-name { font-size: 13px; min-height: 18px; }
	.goods-price { font-size: 18px; }
	.pagination-bar { padding: 16px 0 24px; }
}
/* 手机竖屏 */
@media (max-width: 576px) {
	.campus-banner { border-radius: 0; }
	.banner-stat { padding: 12px 20px; }
	.banner-stat-num { font-size: 28px; }
	.banner-stat-label { font-size: 12px; }
	.search-input { max-width: 100%; }
	.goods-section { margin-top: 16px; padding: 20px 0 32px; }
	.goods-col { margin-bottom: 10px; }
	.goods-price { font-size: 16px; }
}
</style>
