<template>
  <div class="page-header-bar">
    <div class="page-header-inner">
      <el-button
        icon="el-icon-arrow-left"
        size="small"
        @click="goBack"
        class="back-btn"
      >
        返回{{ backLabel }}
      </el-button>
      <span class="page-title">{{ title }}</span>
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PageHeader',
  props: {
    title: {
      type: String,
      default: '',
    },
    backRoute: {
      type: String,
      default: '/shop',
    },
    backLabel: {
      type: String,
      default: '商城',
    },
  },
  methods: {
    goBack() {
      this.$emit('back')
      if (this.$listeners && this.$listeners.back) {
        // parent handles it via @back
      } else {
        this.$router.push(this.backRoute)
      }
    },
  },
}
</script>

<style scoped>
.page-header-bar {
  height: 50px;
  background-color: #f2f2f2;
  width: 100%;
}
.page-header-inner {
  height: 100%;
  display: flex;
  align-items: center;
  margin: 0 auto;
  max-width: 1200px;
  width: 100%;
  padding: 0 16px;
}
.back-btn {
  border-radius: 20px;
}
.page-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
  margin-left: 16px;
}
</style>
