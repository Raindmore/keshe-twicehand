<template>
  <div class="empty-state">
    <el-empty :image-size="imageSize" :description="description">
      <slot name="action"></slot>
    </el-empty>
  </div>
</template>

<script>
export default {
  name: 'EmptyState',
  props: {
    description: {
      type: String,
      default: '暂无数据',
    },
    imageSize: {
      type: Number,
      default: 200,
    },
  },
}
</script>

<style scoped>
.empty-state {
  padding: 20px 0;
}
</style>
