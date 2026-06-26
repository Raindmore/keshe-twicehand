<template>
  <div class="product-card" @click="$emit('click', goods.gid)">
    <div class="product-card-img">
      <el-image :src="goods.gphoto" fit="cover" class="product-img">
        <div slot="error" class="product-img-fallback">
          <img src="../assets/none.jpg" />
        </div>
      </el-image>
    </div>
    <div class="product-card-body">
      <div class="product-category-row">
        <span
          v-if="goods.gcategory && categoryMap[goods.gcategory]"
          class="product-category-tag"
          :style="{ background: categoryMap[goods.gcategory].color }"
        >
          {{ categoryMap[goods.gcategory].label }}
        </span>
      </div>
      <h3 class="product-name">{{ goods.gname | ellipsis }}</h3>
      <div class="product-meta">
        <span class="product-seller">
          <i class="el-icon-user-solid"></i>
          {{ goods.sellusername }}
        </span>
      </div>
      <div class="product-price-row">
        <span class="product-price">{{ goods.gsellprice }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProductCard',
  props: {
    goods: {
      type: Object,
      required: true,
      // { gid, gphoto, gcategory, gname, sellusername, gsellprice }
    },
  },
  data() {
    return {
      categoryMap: {
        '教材': { label: '教辅教材', color: '#667eea' },
        '电子': { label: '电子产品', color: '#e6a23c' },
        '生活': { label: '生活用品', color: '#67c23a' },
        '服饰': { label: '服饰鞋包', color: '#f56c6c' },
        '运动': { label: '运动户外', color: '#409eff' },
        '其他': { label: '其他好物', color: '#909399' },
      },
    }
  },
  filters: {
    ellipsis(value) {
      if (!value) return ''
      value = value.replace(/^【[^】]+】/, '')
      if (value.length > 16) {
        return value.slice(0, 16) + '...'
      }
      return value
    },
  },
}
</script>

<style scoped>
/* ── 商品卡片 ── */
.product-card {
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  box-shadow: none;
  border: 1px solid var(--color-divider);
}
.product-card:hover {
  transform: translateY(-6px);
  box-shadow: var(--shadow-card-hover);
  border-color: var(--color-primary);
}
.product-card-img {
  position: relative;
  width: 100%;
  padding-top: 100%;
  overflow: hidden;
  background: linear-gradient(135deg, #e0f5f5, #e8f5f0);
}
.product-card:hover .product-img {
  transform: scale(1.06);
}
.product-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: transform 0.4s ease;
}
.product-img-fallback img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.product-card-body {
  padding: 14px 16px 16px 16px;
}
.product-category-row {
  margin-bottom: 6px;
}
.product-category-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 11px;
  color: #fff;
  font-weight: 500;
}
.product-name {
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
.product-meta {
  margin-bottom: 10px;
}
.product-seller {
  font-size: 12px;
  color: #888;
  background: var(--color-primary-light);
  padding: 3px 10px;
  border-radius: 10px;
}
.product-seller i {
  margin-right: 3px;
  color: var(--color-primary);
}
.product-price-row {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}
.product-price {
  font-size: 22px;
  font-weight: 800;
  color: var(--color-accent);
}
.product-price::before {
  content: '¥';
  font-size: 14px;
  font-weight: 600;
  margin-right: 2px;
}

/* ── 响应式 ── */
@media (max-width: 768px) {
  .product-card-body { padding: 10px 12px 12px; }
  .product-name { font-size: 13px; min-height: 18px; }
  .product-price { font-size: 18px; }
}
@media (max-width: 576px) {
  .product-price { font-size: 16px; }
}
</style>
