<template>
  <div class="skeleton-card">
    <div class="skeleton-img shimmer"></div>
    <div class="skeleton-body">
      <div class="skeleton-tag shimmer"></div>
      <div class="skeleton-line shimmer" style="width: 80%;"></div>
      <div class="skeleton-line shimmer" style="width: 50%; margin-top: 8px;"></div>
      <div class="skeleton-line shimmer" style="width: 35%; margin-top: 6px; height: 22px;"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SkeletonCard',
}
</script>

<style scoped>
.skeleton-card {
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  border: 1px solid var(--color-divider);
}
.skeleton-img {
  width: 100%;
  padding-top: 100%;
  background: #e8edf2;
}
.skeleton-body {
  padding: 14px 16px 16px 16px;
}
.skeleton-tag {
  width: 56px;
  height: 16px;
  border-radius: 10px;
  background: #e8edf2;
  margin-bottom: 8px;
}
.skeleton-line {
  height: 14px;
  border-radius: 4px;
  background: #e8edf2;
}

/* ── Shimmer 扫光动画 ── */
.shimmer {
  position: relative;
  overflow: hidden;
}
.shimmer::after {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent 0%,
    rgba(255, 255, 255, 0.5) 50%,
    transparent 100%
  );
  animation: shimmer 1.6s infinite;
}
@keyframes shimmer {
  0%   { left: -100%; }
  100% { left: 100%; }
}
</style>
